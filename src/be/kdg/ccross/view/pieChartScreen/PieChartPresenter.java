package be.kdg.ccross.view.pieChartScreen;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.model.Move;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieChartPresenter {
    PieChartView view;
    GameSession session;

    public PieChartPresenter(GameSession session, PieChartView view) {
        this.session = session;
        this.view = view;
        addEventHandlers();
        updateView(session.getDatabase().getGameId()); // Call updateView to initially populate the leaderboard
    }

    private void setHomeScreenView() {

        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("C-Cross");
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(session, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();

    }

    private void addEventHandlers() {

        view.getGoBackButton().setOnAction(actionEvent -> setHomeScreenView());

    }

    public void updateView(int gameId) {
        List<Move> moves = session.getDatabase().getMovesByGameId(gameId);
        Map<Integer, Long> moveDurations = new HashMap<>();

        // Calculate total duration for each move
        for (Move move : moves) {
            int moveId = move.getMoveId();
            long moveTime = move.getMoveTime();

            moveDurations.put(moveId, moveDurations.getOrDefault(moveId, 0L) + moveTime);
        }

        // Prepare data for pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Long> entry : moveDurations.entrySet()) {
            int moveId = entry.getKey();
            long duration = entry.getValue();

            pieChartData.add(new PieChart.Data("Move " + moveId, duration));
        }

        // Update the view with the new data
        view.updatePieChartData(pieChartData);
    }
}

