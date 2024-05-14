package be.kdg.ccross.view.leaderboardScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.List;
import java.util.function.Consumer;

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

    // Method to update the leaderboard with the player statistics
    public void updateLeaderboard(List<String[]> playerData, Consumer<String[]> playerClickHandler) {
        GridPane dataPane = (GridPane) this.getChildren().get(1);
        dataPane.getChildren().clear(); // Clear previous data

        int rowIndex = 1; // Start from the second row after header
        for (String[] data : playerData) {
            dataPane.add(new Label(data[0]), 0, rowIndex); // Rank

            Label playerNameLabel = new Label(data[1]); // Player Name
            playerNameLabel.setOnMouseClicked(event -> playerClickHandler.accept(data));
            dataPane.add(playerNameLabel, 1, rowIndex);

            dataPane.add(new Label(data[2]), 2, rowIndex); // Games Played
            dataPane.add(new Label(data[3]), 3, rowIndex); // Wins
            dataPane.add(new Label(data[4]), 4, rowIndex); // Losses
            dataPane.add(new Label(data[5]), 5, rowIndex); // Win Percentage
            dataPane.add(new Label(data[6]), 6, rowIndex); // Avg Moves
            dataPane.add(new Label(data[7]), 7, rowIndex); // Avg Duration

            rowIndex++; // Move to the next row
        }
    }

    public Button getGoBack() {
        return goBack;
    }

    public Label getRank() {
        return rank;
    }

    public Label getGamesPlayed() {
        return gamesPlayed;
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
