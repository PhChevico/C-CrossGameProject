package be.kdg.ccross.view.leaderboardScreen;

import be.kdg.ccross.model.Database;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.model.PlayerStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaderboardPresenter {
    private GameSession model;
    private LeaderboardView view;
    private Database database;

    public LeaderboardPresenter(GameSession model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        this.database = new Database(); // Create an instance of the Database class
        updateView(); // Call updateView to initially populate the leaderboard
    }

    public void updateView() {
        // Fetch player statistics from the database
        ObservableList<PlayerStatistics> playerStatisticsList = FXCollections.observableArrayList(database.getPlayerStatistics());

        // Update the view with the fetched player statistics
        view.updateLeaderboard(playerStatisticsList);
    }
}