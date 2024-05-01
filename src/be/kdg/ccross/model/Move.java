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
    public void storeMoveData(int gameId, String playerName, GameSession session) {
        try {
            String query = "INSERT INTO Moves (game_id, username, move_time) VALUES (?, ?, ?)";
            PreparedStatement pstmt = session.getDatabase().getConnection().prepareStatement(query);
            pstmt.setInt(1, session.getDatabase().getGameId());
            pstmt.setString(2, playerName);
            long elapsedTimeMillis = gameTime.getElapsedTime();

            pstmt.setLong(3, elapsedTimeMillis);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMoveId() {
        return moveId;
    }

    public int getGameIdFromDB() {
        return gameIdFromDB;
    }

    public String getUsername() {
        return username;
    }

    public long getMoveTime() {
        return moveTime;
    }
}

