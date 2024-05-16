package be.kdg.ccross.model;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    public Database() {
        connect();
    }

    public void connect() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:postgresql://10.134.178.21:5432/postgres", "postgres", "gabi123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PlayerStatistics> getPlayerStatistics() {
        List<PlayerStatistics> playerStatsList = new ArrayList<>();

        try {
            // Query to fetch player statistics
            String query = "SELECT p.username AS player_name, " +
                    "COUNT(g.game_id) AS games_played, " +
                    "SUM(CASE WHEN g.winner_username = p.username THEN 1 ELSE 0 END) AS wins, " +
                    "COUNT(g.game_id) - SUM(CASE WHEN g.winner_username = p.username THEN 1 ELSE 0 END) AS losses, " +
                    "CASE WHEN COUNT(g.game_id) > 0 THEN " +
                    "    (SUM(CASE WHEN g.winner_username = p.username THEN 1 ELSE 0 END) / NULLIF(COUNT(g.game_id), 0) * 100) " +
                    "ELSE " +
                    "    0 " +
                    "END AS win_percentage, " +
                    "AVG((SELECT COUNT(*) FROM Moves m WHERE m.game_id = g.game_id)) AS avg_moves, " +
                    "AVG(EXTRACT(EPOCH FROM (g.end_time - g.start_time)) / NULLIF((SELECT COUNT(*) FROM Moves m WHERE m.game_id = g.game_id), 0.0)) AS avg_duration " +
                    "FROM Players p " +
                    "LEFT JOIN Games g ON g.winner_username = p.username OR g.game_id IN (SELECT m.game_id FROM Moves m WHERE m.username = p.username) " +
                    "GROUP BY p.username";

            // Create statement and execute query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process result set
            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int gamesPlayed = resultSet.getInt("games_played");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                double winPercentage = resultSet.getDouble("win_percentage");
                double avgMoves = resultSet.getDouble("avg_moves");
                double avgDuration = resultSet.getDouble("avg_duration");

                // Create PlayerStatistics object and add to list
                PlayerStatistics playerStats = new PlayerStatistics(playerName, gamesPlayed, wins, losses, winPercentage, avgMoves, avgDuration);
                playerStatsList.add(playerStats);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerStatsList;
    }
    public int generateGameId() {
        int gameId = 0;
        try {
            String query = "SELECT game_id FROM Games ORDER BY game_id desc LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                gameId = rs.getInt(1) + 1; // Increment the maximum game_id by 1
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameId;
    }
    public int getGameId() {
        int gameId = 0;
        try {
            String query = "SELECT game_id FROM Games ORDER BY game_id desc LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                gameId = rs.getInt(1); // Increment the maximum game_id by 1
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameId;
    }
    public void insertNewGame(int gameId, Timestamp startTime) {
        try {
            // Reset the move_id sequence to start from 1 for each new game
            String resetSequenceQuery = "ALTER SEQUENCE moves_move_id_seq RESTART WITH 1";
            PreparedStatement resetSequenceStmt = connection.prepareStatement(resetSequenceQuery);
            resetSequenceStmt.executeUpdate();
            resetSequenceStmt.close();

            // Insert new game into the Games table
            String insertGameQuery = "INSERT INTO Games (game_id, start_time) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertGameQuery);
            pstmt.setInt(1, gameId);
            pstmt.setTimestamp(2, startTime);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void storeMoveData(int gameId, String playerName, GameSession session) {
        try {
            String query = "INSERT INTO Moves (game_id, username, move_time) VALUES (?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, getGameId());
            pstmt.setString(2, playerName);
            long elapsedTimeMillis = session.getMove().getGameTime().getElapsedTime();

            pstmt.setLong(3, elapsedTimeMillis);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Move> getMovesByGameId(int gameId) {
        List<Move> moves = new ArrayList<>();

        try {
            String query = "SELECT * FROM Moves WHERE game_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, gameId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int moveId = resultSet.getInt("move_id");
                int gameIdFromDB = resultSet.getInt("game_id");
                String username = resultSet.getString("username");
                long moveTime = resultSet.getLong("move_time");

                // Create a Move object and add it to the list
                Move move = new Move(moveId, gameIdFromDB, username, moveTime);
                moves.add(move);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moves;
    }
    public Connection getConnection() {
        return connection;
    }

    public void updateGameStats(int gameId,String winner, GameTime time) {//used to insert the game statistic at the end of each game
        try {
            String updateGameQuery = "UPDATE Games SET end_time=?, winner_username=?, total_play_time=? WHERE game_id=?";
            PreparedStatement pstmt = connection.prepareStatement(updateGameQuery);
            pstmt.setTimestamp(1, time.getEndTime());
            pstmt.setString(2, winner);
            pstmt.setLong(3, time.getElapsedTime());
            pstmt.setInt(4, gameId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateLastAccessTime(String username) {
        String sql = "UPDATE players SET last_access_time = ? WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve the last player who accessed the game
    public String getLastAccessedPlayer() {
        String sql = "SELECT username FROM players ORDER BY last_access_time DESC LIMIT 1";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<PlayerStatistics> playerStatisticsList;
}