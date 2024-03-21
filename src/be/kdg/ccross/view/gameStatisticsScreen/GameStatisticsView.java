package be.kdg.ccross.view.gameStatisticsScreen;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameStatisticsView extends VBox {
    private Label winnerLabel;
    private Label playTimeLabel;
    private Label movesLabel;
    private Label avgDurationLabel;

    public GameStatisticsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        winnerLabel = new Label("Winner: ");
        playTimeLabel = new Label("Total Play Time: ");
        movesLabel = new Label("Total Moves: ");
        avgDurationLabel = new Label("Average Duration per Move: ");
    }

    private void layoutNodes() {
        getChildren().addAll(winnerLabel, playTimeLabel, movesLabel, avgDurationLabel);
    }

    public Label getWinnerLabel() {
        return winnerLabel;
    }

    public Label getPlayTimeLabel() {
        return playTimeLabel;
    }

    public Label getMovesLabel() {
        return movesLabel;
    }

    public Label getAvgDurationLabel() {
        return avgDurationLabel;
    }
}
