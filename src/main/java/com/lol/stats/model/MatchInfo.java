package com.lol.stats.model;

public class MatchInfo {
    private String matchId;
    private String championName;
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
    private double kda;
    private int champLevel;
    private int minionsKilled;
    private int goldEarned;
    private long gameDuration;
    private String gameMode;

    public MatchInfo() {
    }

    public MatchInfo(String matchId, String championName, boolean win, int kills, int deaths, int assists,
                     double kda, int champLevel, int minionsKilled, int goldEarned, long gameDuration, String gameMode) {
        this.matchId = matchId;
        this.championName = championName;
        this.win = win;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.kda = kda;
        this.champLevel = champLevel;
        this.minionsKilled = minionsKilled;
        this.goldEarned = goldEarned;
        this.gameDuration = gameDuration;
        this.gameMode = gameMode;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
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

    public double getKda() {
        return kda;
    }

    public void setKda(double kda) {
        this.kda = kda;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
