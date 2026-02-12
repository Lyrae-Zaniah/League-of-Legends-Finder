package com.lol.stats.model;

public class ChampionStats {
    private String championName;
    private int gamesPlayed;
    private int wins;
    private int losses;
    private double winRate;
    private int totalKills;
    private int totalDeaths;
    private int totalAssists;
    private double avgKills;
    private double avgDeaths;
    private double avgAssists;

    public ChampionStats() {
    }

    public ChampionStats(String championName, int gamesPlayed, int wins, int losses, double winRate,
                         int totalKills, int totalDeaths, int totalAssists,
                         double avgKills, double avgDeaths, double avgAssists) {
        this.championName = championName;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.totalKills = totalKills;
        this.totalDeaths = totalDeaths;
        this.totalAssists = totalAssists;
        this.avgKills = avgKills;
        this.avgDeaths = avgDeaths;
        this.avgAssists = avgAssists;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
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

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    public double getAvgKills() {
        return avgKills;
    }

    public void setAvgKills(double avgKills) {
        this.avgKills = avgKills;
    }

    public double getAvgDeaths() {
        return avgDeaths;
    }

    public void setAvgDeaths(double avgDeaths) {
        this.avgDeaths = avgDeaths;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(double avgAssists) {
        this.avgAssists = avgAssists;
    }
}
