package be.kdg.ccross.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    public Database() {
        connect();
    }

    private void connect() {
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
            String query = "SELECT p.player_id, p.player_name, COUNT(g.game_id) AS games_played, " +
                    "SUM(CASE WHEN g.winner_id = p.player_id THEN 1 ELSE 0 END) AS wins, " +
                    "SUM(CASE WHEN g.winner_id != p.player_id THEN 1 ELSE 0 END) AS losses, " +
                    "(SUM(CASE WHEN g.winner_id = p.player_id THEN 1 ELSE 0 END) / COUNT(g.game_id) * 100) AS win_percentage, " +
                    "AVG((SELECT COUNT(*) FROM Moves m WHERE m.game_id = g.game_id)) AS avg_moves, " +
                    "AVG(EXTRACT(EPOCH FROM (g.end_time - g.start_time)) / (SELECT COUNT(*) FROM Moves m WHERE m.game_id = g.game_id)) AS avg_duration " +
                    "FROM Players p " +
                    "LEFT JOIN Games g ON g.winner_id = p.player_id OR g.game_id IN (SELECT m.game_id FROM Moves m WHERE m.player_id = p.player_id) " +
                    "GROUP BY p.player_id";

            // Create statement and execute query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process result set
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                String playerName = resultSet.getString("player_name");
                int gamesPlayed = resultSet.getInt("games_played");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                double winPercentage = resultSet.getDouble("win_percentage");
                double avgMoves = resultSet.getDouble("avg_moves");
                double avgDuration = resultSet.getDouble("avg_duration");

                // Create PlayerStatistics object and add to list
                PlayerStatistics playerStats = new PlayerStatistics(playerId, playerName, gamesPlayed, wins, losses, winPercentage, avgMoves, avgDuration);
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
}