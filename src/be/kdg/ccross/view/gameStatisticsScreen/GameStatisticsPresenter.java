package be.kdg.ccross.view.gameStatisticsScreen;

import be.kdg.ccross.model.GameSession;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameStatisticsPresenter {
    private GameSession model;
    private GameStatisticsView view;
    private Stage stage;

    public GameStatisticsPresenter(GameSession model) {
        this.model = model;
        view = new GameStatisticsView();
        stage = new Stage();
        stage.setScene(new Scene(view));
        stage.setTitle("Game Statistics");
    }
    public void showStatistics(String winner, String playTime, int totalMoves, double avgDuration) {
        view.getWinnerLabel().setText("Winner: " + winner);
        view.getPlayTimeLabel().setText("Total Play Time: " + playTime);
        view.getMovesLabel().setText("Total Moves: " + totalMoves);
        view.getAvgDurationLabel().setText("Average Duration per Move: " + avgDuration + " seconds");
        stage.show();
    }
}