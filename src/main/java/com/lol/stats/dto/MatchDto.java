package com.lol.stats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto {
    private MetadataDto metadata;
    private InfoDto info;

    public MetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(InfoDto info) {
        this.info = info;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetadataDto {
        private String matchId;
        private List<String> participants;

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public List<String> getParticipants() {
            return participants;
        }

        public void setParticipants(List<String> participants) {
            this.participants = participants;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InfoDto {
        private long gameCreation;
        private long gameDuration;
        private long gameEndTimestamp;
        private String gameMode;
        private String gameType;
        private List<ParticipantDto> participants;

        public long getGameCreation() {
            return gameCreation;
        }

        public void setGameCreation(long gameCreation) {
            this.gameCreation = gameCreation;
        }

        public long getGameDuration() {
            return gameDuration;
        }

        public void setGameDuration(long gameDuration) {
            this.gameDuration = gameDuration;
        }

        public long getGameEndTimestamp() {
            return gameEndTimestamp;
        }

        public void setGameEndTimestamp(long gameEndTimestamp) {
            this.gameEndTimestamp = gameEndTimestamp;
        }

        public String getGameMode() {
            return gameMode;
        }

        public void setGameMode(String gameMode) {
            this.gameMode = gameMode;
        }

        public String getGameType() {
            return gameType;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public List<ParticipantDto> getParticipants() {
            return participants;
        }

        public void setParticipants(List<ParticipantDto> participants) {
            this.participants = participants;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ParticipantDto {
        private String puuid;
        private String summonerName;
        private String riotIdGameName;
        private String riotIdTagline;
        private int championId;
        private String championName;
        private int kills;
        private int deaths;
        private int assists;
        private boolean win;
        private int champLevel;
        private int totalMinionsKilled;
        private int goldEarned;

        public String getPuuid() {
            return puuid;
        }

        public void setPuuid(String puuid) {
            this.puuid = puuid;
        }

        public String getSummonerName() {
            return summonerName;
        }

        public void setSummonerName(String summonerName) {
            this.summonerName = summonerName;
        }

        public String getRiotIdGameName() {
            return riotIdGameName;
        }

        public void setRiotIdGameName(String riotIdGameName) {
            this.riotIdGameName = riotIdGameName;
        }

        public String getRiotIdTagline() {
            return riotIdTagline;
        }

        public void setRiotIdTagline(String riotIdTagline) {
            this.riotIdTagline = riotIdTagline;
        }

        public int getChampionId() {
            return championId;
        }

        public void setChampionId(int championId) {
            this.championId = championId;
        }

        public String getChampionName() {
            return championName;
        }

        public void setChampionName(String championName) {
            this.championName = championName;
        }

        public int getKills() {
            return kills;
        }

        public void setKills(int kills) {
            this.kills = kills;
        }

        public int getDeaths() {
            return deaths;
        }

        public void setDeaths(int deaths) {
            this.deaths = deaths;
        }

        public int getAssists() {
            return assists;
        }

        public void setAssists(int assists) {
            this.assists = assists;
        }

        public boolean isWin() {
            return win;
        }

        public void setWin(boolean win) {
            this.win = win;
        }

        public int getChampLevel() {
            return champLevel;
        }

        public void setChampLevel(int champLevel) {
            this.champLevel = champLevel;
        }

        public int getTotalMinionsKilled() {
            return totalMinionsKilled;
        }

        public void setTotalMinionsKilled(int totalMinionsKilled) {
            this.totalMinionsKilled = totalMinionsKilled;
        }

        public int getGoldEarned() {
            return goldEarned;
        }

        public void setGoldEarned(int goldEarned) {
            this.goldEarned = goldEarned;
        }
    }
}
