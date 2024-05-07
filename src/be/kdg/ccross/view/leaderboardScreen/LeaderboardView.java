package be.kdg.ccross.view.leaderboardScreen;

import be.kdg.ccross.model.PlayerStatistics;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Comparator;

public class LeaderboardView extends VBox {
    private Button goBack;
    private Label rank;
    private Label gamesPlayed;
    private Label playerName;
    private Label wins;
    private Label losses;
    private Label percentageWon;
    private Label avgMoves;
    private Label avgDuration;

    public LeaderboardView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        goBack = new Button("Go back");
        rank = new Label("Rank");
        gamesPlayed = new Label("Games Played");
        playerName = new Label("Player Name");
        wins = new Label("Wins");
        losses = new Label("Losses");
        percentageWon = new Label("% Games Won");
        avgMoves = new Label("Avg Moves");
        avgDuration = new Label("Avg Duration");

        goBack.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px;");
    }

    private void layoutNodes() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        GridPane headerPane = new GridPane();
        headerPane.setHgap(10);
        headerPane.setPadding(new Insets(10));
        headerPane.setAlignment(Pos.CENTER);

        headerPane.add(rank, 0, 0);
        headerPane.add(playerName, 1, 0);
        headerPane.add(gamesPlayed, 2, 0);
        headerPane.add(wins, 3, 0);
        headerPane.add(losses, 4, 0);
        headerPane.add(percentageWon, 5, 0);
        headerPane.add(avgMoves, 6, 0);
        headerPane.add(avgDuration, 7, 0);

        GridPane dataPane = new GridPane();
        dataPane.setHgap(10);
        dataPane.setVgap(5);
        dataPane.setPadding(new Insets(10));
        dataPane.setAlignment(Pos.CENTER);

        // Add the "Go back" button spanning all columns
        HBox buttonPane = new HBox(goBack);
        buttonPane.setAlignment(Pos.CENTER);

        goBack.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");

        this.getChildren().addAll(headerPane, dataPane, buttonPane);
    }

    // Method to update the leaderboard with the fetched player statistics
    public void updateLeaderboard(ObservableList<PlayerStatistics> playerStatisticsList, String sortBy, boolean ascending) {
        GridPane dataPane = (GridPane) this.getChildren().get(1);
        dataPane.getChildren().clear(); // Clear previous data

        // Sort the player statistics list based on the selected variable
        playerStatisticsList.sort(getComparator(sortBy, ascending));

        int rowIndex = 1; // Start from the second row after header
        int rank = 1;
        for (PlayerStatistics stats : playerStatisticsList) {
            dataPane.add(new Label(String.valueOf(rank)), 0, rowIndex);
            dataPane.add(new Label(stats.getPlayerName()), 1, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getGamesPlayed())), 2, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getWins())), 3, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getLosses())), 4, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getWinPercentage())), 5, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getAvgMoves())), 6, rowIndex);
            dataPane.add(new Label(String.valueOf(stats.getAvgDuration())), 7, rowIndex);
            rowIndex++; // Move to the next row
            rank++;
        }
    }

    // Helper method to get the comparator based on the selected variable and sorting order
    private Comparator<PlayerStatistics> getComparator(String sortBy, boolean ascending) {
        Comparator<PlayerStatistics> comparator = null;

        switch (sortBy) {
            case "Player Name":
                comparator = Comparator.comparing(PlayerStatistics::getPlayerName);
                break;
            case "Games Played":
                comparator = Comparator.comparing(PlayerStatistics::getGamesPlayed);
                break;
            case "Wins":
                comparator = Comparator.comparing(PlayerStatistics::getWins);
                break;
            case "Losses":
                comparator = Comparator.comparing(PlayerStatistics::getLosses);
                break;
            case "% Games Won":
                comparator = Comparator.comparing(PlayerStatistics::getWinPercentage);
                break;
            case "Avg Moves":
                comparator = Comparator.comparing(PlayerStatistics::getAvgMoves);
                break;
            case "Avg Duration":
                comparator = Comparator.comparing(PlayerStatistics::getAvgDuration);
                break;
            default:
                // Default to sorting by player name if the selected variable is not recognized
                comparator = Comparator.comparing(PlayerStatistics::getPlayerName);
                break;
        }

        // Apply sorting order
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    public Label getRank() {
        return rank;
    }

    public Label getGamesPlayed() {
        return gamesPlayed;
    }

    public Button getGoBack() {
        return goBack;
    }

    public Label getPlayerName() {
        return playerName;
    }

    public Label getWins() {
        return wins;
    }

    public Label getLosses() {
        return losses;
    }

    public Label getPercentageWon() {
        return percentageWon;
    }

    public Label getAvgMoves() {
        return avgMoves;
    }

    public Label getAvgDuration() {
        return avgDuration;
    }
}
