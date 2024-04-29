package be.kdg.ccross.view.leaderboardScreen;

import be.kdg.ccross.model.Database;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.model.PlayerStatistics;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LeaderboardPresenter {
    private String sortBy;
    private GameSession model;
    private LeaderboardView view;
    private Database database;

    public LeaderboardPresenter(GameSession model, LeaderboardView view) {
        this.model = model;
        this.view = view;
        this.database = new Database(); // Create an instance of the Database class
        sortBy = "Win";
        addEventHandlers();
        updateView(); // Call updateView to initially populate the leaderboard
    }
    private void setHomeScreenView(){

        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();

    }
    private void addEventHandlers() {

        view.getGoBack().setOnAction(actionEvent -> setHomeScreenView());
        view.getRank().setOnMouseClicked(mouseEvent -> {
            sortBy = "Rank";
            updateView();
        });

        view.getGamesPlayed().setOnMouseClicked(mouseEvent -> {
            sortBy = "Games Played";
            updateView();
        });

        view.getPlayerName().setOnMouseClicked(mouseEvent -> {
            sortBy = "Player Name";
            updateView();
        });

        view.getWins().setOnMouseClicked(mouseEvent -> {
            sortBy = "Wins";
            updateView();
        });

        view.getLosses().setOnMouseClicked(mouseEvent -> {
            sortBy = "Losses";
            updateView();
        });

        view.getPercentageWon().setOnMouseClicked(mouseEvent -> {
            sortBy = "% Games Won";
            updateView();
        });

        view.getAvgMoves().setOnMouseClicked(mouseEvent -> {
            sortBy = "Avg Moves";
            updateView();
        });

        view.getAvgDuration().setOnMouseClicked(mouseEvent -> {
            sortBy = "Avg Duration";
            updateView();
        });

    }

    public void updateView() {
        // Fetch player statistics from the database
        ObservableList<PlayerStatistics> playerStatisticsList = FXCollections.observableArrayList(database.getPlayerStatistics());
// Assuming sortBy and ascending are determined based on user input or some other logic
        boolean ascending = false;

        view.updateLeaderboard(playerStatisticsList, sortBy, ascending);
    }
}