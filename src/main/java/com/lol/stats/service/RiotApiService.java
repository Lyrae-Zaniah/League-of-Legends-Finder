package com.lol.stats.service;

import com.lol.stats.config.RiotApiConfig;
import com.lol.stats.dto.AccountDto;
import com.lol.stats.dto.MatchDto;
import com.lol.stats.dto.SummonerDto;
import com.lol.stats.model.ChampionStats;
import com.lol.stats.model.FriendStats;
import com.lol.stats.model.MatchInfo;
import com.lol.stats.model.PlayerStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RiotApiService {

    private static final Logger log = LoggerFactory.getLogger(RiotApiService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RiotApiConfig apiConfig;

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiConfig.getApiKey());
        return headers;
    }

    public AccountDto getAccountByRiotId(String gameName, String tagLine) {
        String url = String.format("%s/riot/account/v1/accounts/by-riot-id/%s/%s",
                apiConfig.getAmericasUrl(), gameName, tagLine);
        
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<AccountDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, AccountDto.class);
        
        return response.getBody();
    }

    public SummonerDto getSummonerByPuuid(String puuid) {
        String url = String.format("%s/lol/summoner/v4/summoners/by-puuid/%s",
                apiConfig.getBaseUrl(), puuid);
        
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<SummonerDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, SummonerDto.class);
        
        return response.getBody();
    }

    public List<String> getMatchIdsByPuuid(String puuid, int count) {
        String url = String.format("%s/lol/match/v5/matches/by-puuid/%s/ids?start=0&count=%d",
                apiConfig.getAmericasUrl(), puuid, count);
        
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<String[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String[].class);
        
        return Arrays.asList(response.getBody());
    }

    public MatchDto getMatchById(String matchId) {
        String url = String.format("%s/lol/match/v5/matches/%s",
                apiConfig.getAmericasUrl(), matchId);
        
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<MatchDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, MatchDto.class);
        
        return response.getBody();
    }

    public PlayerStats getPlayerStats(String gameName, String tagLine, int matchCount) {
        try {
            // Get account info
            AccountDto account = getAccountByRiotId(gameName, tagLine);
            String puuid = account.getPuuid();

            // Get summoner info
            SummonerDto summoner = getSummonerByPuuid(puuid);

            // Get match history
            List<String> matchIds = getMatchIdsByPuuid(puuid, matchCount);
            
            // Analyze matches
            int wins = 0;
            int losses = 0;
            Map<String, ChampionData> championMap = new HashMap<>();
            Map<String, FriendData> friendMap = new HashMap<>();
            List<MatchInfo> recentMatches = new ArrayList<>();

            for (String matchId : matchIds) {
                try {
                    MatchDto match = getMatchById(matchId);
                    
                    // Find player's data in match
                    MatchDto.ParticipantDto playerData = match.getInfo().getParticipants()
                            .stream()
                            .filter(p -> p.getPuuid().equals(puuid))
                            .findFirst()
                            .orElse(null);

                    if (playerData != null) {
                        // Count wins/losses
                        if (playerData.isWin()) {
                            wins++;
                        } else {
                            losses++;
                        }

                        // Track champion stats
                        String championName = playerData.getChampionName();
                        ChampionData championData = championMap.getOrDefault(
                                championName, new ChampionData(championName));
                        championData.addGame(playerData.isWin(), playerData.getKills(),
                                playerData.getDeaths(), playerData.getAssists());
                        championMap.put(championName, championData);

                        // Store match info
                        double kda = playerData.getDeaths() > 0 ?
                                (playerData.getKills() + playerData.getAssists()) / (double) playerData.getDeaths() :
                                (playerData.getKills() + playerData.getAssists());
                        
                        MatchInfo matchInfo = new MatchInfo(
                                matchId,
                                playerData.getChampionName(),
                                playerData.isWin(),
                                playerData.getKills(),
                                playerData.getDeaths(),
                                playerData.getAssists(),
                                kda,
                                playerData.getChampLevel(),
                                playerData.getTotalMinionsKilled(),
                                playerData.getGoldEarned(),
                                match.getInfo().getGameDuration(),
                                match.getInfo().getGameMode()
                        );
                        recentMatches.add(matchInfo);

                        // Track friends (teammates)
                        List<String> teammatesPuuids = match.getInfo().getParticipants()
                                .stream()
                                .filter(p -> !p.getPuuid().equals(puuid))
                                .filter(p -> isSameTeam(match, puuid, p.getPuuid()))
                                .map(MatchDto.ParticipantDto::getPuuid)
                                .collect(Collectors.toList());

                        for (MatchDto.ParticipantDto teammate : match.getInfo().getParticipants()) {
                            if (!teammate.getPuuid().equals(puuid) && 
                                    teammatesPuuids.contains(teammate.getPuuid())) {
                                String friendName = teammate.getRiotIdGameName() != null ? 
                                        teammate.getRiotIdGameName() : teammate.getSummonerName();
                                FriendData friendData = friendMap.getOrDefault(
                                        friendName, new FriendData(friendName));
                                friendData.addGame(playerData.isWin());
                                friendMap.put(friendName, friendData);
                            }
                        }
                    }
                    
                    // Avoid rate limiting
                    Thread.sleep(100);
                } catch (Exception e) {
                    log.error("Error processing match {}: {}", matchId, e.getMessage());
                }
            }

            // Calculate win rate
            int totalGames = wins + losses;
            double winRate = totalGames > 0 ? (wins * 100.0 / totalGames) : 0.0;

            // Get top champions
            List<ChampionStats> topChampions = championMap.values().stream()
                    .map(ChampionData::toChampionStats)
                    .sorted((a, b) -> Integer.compare(b.getGamesPlayed(), a.getGamesPlayed()))
                    .limit(5)
                    .collect(Collectors.toList());

            // Get friends with most games (only players who played 2+ games together)
            List<FriendStats> friends = friendMap.values().stream()
                    .filter(friendData -> friendData.games >= 2) // Only friends with 2+ games together
                    .map(FriendData::toFriendStats)
                    .sorted((a, b) -> Integer.compare(b.getGamesPlayedTogether(), a.getGamesPlayedTogether()))
                    .limit(10)
                    .collect(Collectors.toList());

            return new PlayerStats(
                    account.getGameName(),
                    account.getTagLine(),
                    summoner.getProfileIconId(),
                    summoner.getSummonerLevel(),
                    totalGames,
                    wins,
                    losses,
                    winRate,
                    topChampions,
                    friends,
                    recentMatches
            );

        } catch (Exception e) {
            log.error("Error getting player stats: {}", e.getMessage());
            throw new RuntimeException("Failed to get player stats: " + e.getMessage());
        }
    }

    private boolean isSameTeam(MatchDto match, String puuid1, String puuid2) {
        MatchDto.ParticipantDto p1 = match.getInfo().getParticipants().stream()
                .filter(p -> p.getPuuid().equals(puuid1))
                .findFirst()
                .orElse(null);
        MatchDto.ParticipantDto p2 = match.getInfo().getParticipants().stream()
                .filter(p -> p.getPuuid().equals(puuid2))
                .findFirst()
                .orElse(null);
        
        if (p1 == null || p2 == null) return false;
        
        return p1.isWin() == p2.isWin();
    }

    // Helper classes for tracking stats
    private static class ChampionData {
        String name;
        int games = 0;
        int wins = 0;
        int totalKills = 0;
        int totalDeaths = 0;
        int totalAssists = 0;

        ChampionData(String name) {
            this.name = name;
        }

        void addGame(boolean win, int kills, int deaths, int assists) {
            games++;
            if (win) wins++;
            totalKills += kills;
            totalDeaths += deaths;
            totalAssists += assists;
        }

        ChampionStats toChampionStats() {
            int losses = games - wins;
            double winRate = games > 0 ? (wins * 100.0 / games) : 0.0;
            double avgKills = games > 0 ? (totalKills * 1.0 / games) : 0.0;
            double avgDeaths = games > 0 ? (totalDeaths * 1.0 / games) : 0.0;
            double avgAssists = games > 0 ? (totalAssists * 1.0 / games) : 0.0;
            
            return new ChampionStats(name, games, wins, losses, winRate,
                    totalKills, totalDeaths, totalAssists, avgKills, avgDeaths, avgAssists);
        }
    }

    private static class FriendData {
        String name;
        int games = 0;
        int wins = 0;

        FriendData(String name) {
            this.name = name;
        }

        void addGame(boolean win) {
            games++;
            if (win) wins++;
        }

        FriendStats toFriendStats() {
            int losses = games - wins;
            double winRate = games > 0 ? (wins * 100.0 / games) : 0.0;
            return new FriendStats(name, games, wins, losses, winRate);
        }
    }
}
