package com.lol.stats.model;

public class FriendStats {
    private String friendName;
    private int gamesPlayedTogether;
    private int winsWithFriend;
    private int lossesWithFriend;
    private double winRateWithFriend;

    public FriendStats() {
    }

    public FriendStats(String friendName, int gamesPlayedTogether, int winsWithFriend,
                       int lossesWithFriend, double winRateWithFriend) {
        this.friendName = friendName;
        this.gamesPlayedTogether = gamesPlayedTogether;
        this.winsWithFriend = winsWithFriend;
        this.lossesWithFriend = lossesWithFriend;
        this.winRateWithFriend = winRateWithFriend;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public int getGamesPlayedTogether() {
        return gamesPlayedTogether;
    }

    public void setGamesPlayedTogether(int gamesPlayedTogether) {
        this.gamesPlayedTogether = gamesPlayedTogether;
    }

    public int getWinsWithFriend() {
        return winsWithFriend;
    }

    public void setWinsWithFriend(int winsWithFriend) {
        this.winsWithFriend = winsWithFriend;
    }

    public int getLossesWithFriend() {
        return lossesWithFriend;
    }

    public void setLossesWithFriend(int lossesWithFriend) {
        this.lossesWithFriend = lossesWithFriend;
    }

    public double getWinRateWithFriend() {
        return winRateWithFriend;
    }

    public void setWinRateWithFriend(double winRateWithFriend) {
        this.winRateWithFriend = winRateWithFriend;
    }
}
