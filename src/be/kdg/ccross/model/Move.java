package be.kdg.ccross.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Move {
    private boolean StartNewPathMove;
    private boolean BlockPlayerFromWinningMove;
    private boolean ContinuePathMove;
    private String coordinates;
    private GameTime gameTime;
    private int moveId;
    private int gameIdFromDB;
    private String username;
    private long moveTime;

    public Move(int moveId, int gameIdFromDB, String username, long moveTime) {
        this.moveId = moveId;
        this.gameIdFromDB = gameIdFromDB;
        this.username = username;
        this.moveTime = moveTime;
    }

    public Move() {
        this.StartNewPathMove = false;
        this.ContinuePathMove = false;
        this.BlockPlayerFromWinningMove = false;
        this.coordinates = null;
        this.gameTime = new GameTime();
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

    }

    public void setBlockPlayerFromWinningMove(boolean blockPlayerFromWinningMove) {
        BlockPlayerFromWinningMove = blockPlayerFromWinningMove;
    }

    public void setContinuePathMove(boolean continuePathMove) {ContinuePathMove = continuePathMove;}

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;

    }

    public GameTime getGameTime() {
        return gameTime;
    }


    public int getMoveId() {
        return moveId;
    }

    public long getMoveTime() {
        return moveTime;
    }
}

