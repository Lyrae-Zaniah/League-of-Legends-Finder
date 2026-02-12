package com.lol.stats.model;

import java.util.List;

public class PlayerStats {
    private String gameName;
    private String tagLine;
    private int profileIconId;
    private long summonerLevel;
    private int totalGames;
    private int wins;
    private int losses;
    private double winRate;
    private List<ChampionStats> topChampions;
    private List<FriendStats> friends;
    private List<MatchInfo> recentMatches;

    public PlayerStats() {
    }

    public PlayerStats(String gameName, String tagLine, int profileIconId, long summonerLevel,
                       int totalGames, int wins, int losses, double winRate,
                       List<ChampionStats> topChampions, List<FriendStats> friends, List<MatchInfo> recentMatches) {
        this.gameName = gameName;
        this.tagLine = tagLine;
        this.profileIconId = profileIconId;
        this.summonerLevel = summonerLevel;
        this.totalGames = totalGames;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.topChampions = topChampions;
        this.friends = friends;
        this.recentMatches = recentMatches;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public List<ChampionStats> getTopChampions() {
        return topChampions;
    }

    public void setTopChampions(List<ChampionStats> topChampions) {
        this.topChampions = topChampions;
    }

    public List<FriendStats> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendStats> friends) {
        this.friends = friends;
    }

    public List<MatchInfo> getRecentMatches() {
        return recentMatches;
    }

    public void setRecentMatches(List<MatchInfo> recentMatches) {
        this.recentMatches = recentMatches;
    }
}
