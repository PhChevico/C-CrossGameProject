package be.kdg.ccross.view.leaderboardScreen;

import be.kdg.ccross.model.PlayerStatistics;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderboardView extends VBox {
    private Button goBack;

    public LeaderboardView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        goBack = new Button("Go back");
        goBack.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px;");
    }

    private void layoutNodes() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        GridPane headerPane = new GridPane();
        headerPane.setHgap(10);
        headerPane.setPadding(new Insets(10));
        headerPane.setAlignment(Pos.CENTER);

        headerPane.add(new Label("Rank"), 0, 0);
        headerPane.add(new Label("Player Name"), 1, 0);
        headerPane.add(new Label("Games Played"), 2, 0);
        headerPane.add(new Label("Wins"), 3, 0);
        headerPane.add(new Label("Losses"), 4, 0);
        headerPane.add(new Label("% Games Won"), 5, 0);
        headerPane.add(new Label("Avg Moves"), 6, 0);
        headerPane.add(new Label("Avg Duration"), 7, 0);

        GridPane dataPane = new GridPane();
        dataPane.setHgap(10);
        dataPane.setVgap(5);
        dataPane.setPadding(new Insets(10));
        dataPane.setAlignment(Pos.CENTER);

        // Add the "Go back" button spanning all columns
        HBox buttonPane = new HBox(goBack);
        buttonPane.setAlignment(Pos.CENTER);

        this.getChildren().addAll(headerPane, dataPane, buttonPane);
    }

    // Method to update the leaderboard with the fetched player statistics
    public void updateLeaderboard(ObservableList<PlayerStatistics> playerStatisticsList) {
        GridPane dataPane = (GridPane) this.getChildren().get(1);
        dataPane.getChildren().clear(); // Clear previous data

        int rowIndex = 1; // Start from the second row after header
        for (PlayerStatistics stats : playerStatisticsList) {
            dataPane.add(new Label(stats.getPlayerName()), 1, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getGamesPlayed())), 2, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getWins())), 3, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getLosses())), 4, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getWinPercentage())), 5, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getAvgMoves())), 6, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getAvgDuration())), 7, rowIndex);
            rowIndex++; // Move to the next row
        }
    }
}