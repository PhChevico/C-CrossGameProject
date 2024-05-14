package be.kdg.ccross.model;

public class PlayerStatistics {
    private int playerId;
    private String playerName;
    private int gamesPlayed;
    private int wins;
    private int losses;
    private double winPercentage;
    private double avgMoves;
    private double avgDuration;

    public PlayerStatistics(String playerName, int gamesPlayed, int wins, int losses, double winPercentage, double avgMoves, double avgDuration) {
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.winPercentage = winPercentage;
        this.avgMoves = avgMoves;
        this.avgDuration = avgDuration;
    }

    // Getters and setters for all attributes
    // You can generate these using your IDE or write them manually
    // Here's an example of how you might define them manually:

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    public double getAvgMoves() {
        return avgMoves;
    }

    public void setAvgMoves(double avgMoves) {
        this.avgMoves = avgMoves;
    }

    public double getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(double avgDuration) {
        this.avgDuration = avgDuration;
    }


}