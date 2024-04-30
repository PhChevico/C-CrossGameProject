package be.kdg.ccross.model;

public class Move {
    private boolean StartNewPathMove;
    private boolean BlockPlayerFromWinningMove;
    private boolean ContinuePathMove;
    private String coordinates;

    public Move() {
        this.StartNewPathMove = false;
        this.ContinuePathMove = false;
        this.BlockPlayerFromWinningMove = false;
        this.coordinates = null;
    }

    public boolean isStartNewPathMove() {
        return StartNewPathMove;
    }

    public boolean isBlockPlayerFromWinningMove() {
        return BlockPlayerFromWinningMove;
    }

    public boolean isContinuePathMove() {
        return ContinuePathMove;
    }

    public void setStartNewPathMove(boolean startNewPathMove) {
        StartNewPathMove = startNewPathMove;
        System.out.println("setting Start move as true");
    }

    public void setBlockPlayerFromWinningMove(boolean blockPlayerFromWinningMove) {
        BlockPlayerFromWinningMove = blockPlayerFromWinningMove;
    }

    public void setContinuePathMove(boolean continuePathMove) {
        ContinuePathMove = continuePathMove;
        System.out.println("Setting Continue move as true");
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
