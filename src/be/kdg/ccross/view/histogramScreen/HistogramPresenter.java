package be.kdg.ccross.view.histogramScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.model.Move;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

public class HistogramPresenter {
    private HistogramView view;
    private GameSession session;

    public HistogramPresenter(GameSession session, HistogramView view) {
        this.session = session;
        this.view = view;
        addEventHandlers();
        updateView(session.getDatabase().getGameId()); // Call updateView to initially populate the histogram
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

        // Prepare data for histogram
        ObservableList<XYChart.Series<String, Number>> histogramData = FXCollections.observableArrayList();
        Map<String, String> moveColorMap = new HashMap<>();
        List<String> outlierMoves = new ArrayList<>();

        // Create series for each move
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Map<Integer, Long> moveDurations = new HashMap<>();

        // Count the frequency of each move duration
        for (Move move : moves) {
            int moveId = move.getMoveId();
            long moveTime = move.getMoveTime();

            moveDurations.put(moveId, moveTime);
        }

        // Prepare data for histogram and identify outliers
        List<Long> durations = new ArrayList<>(moveDurations.values());
        double Q1 = quartile(durations, 0.25);
        double Q3 = quartile(durations, 0.75);
        double IQR = Q3 - Q1;
        double upperBound = Q3 + 1.5 * IQR;
        double lowerBound = Q1 - 1.5 * IQR;

        for (Move move : moves) {
            int moveId = move.getMoveId();
            long duration = move.getMoveTime();

            series.getData().add(new XYChart.Data<>(String.valueOf(moveId), duration));
            if (duration > upperBound || duration < lowerBound) {
                outlierMoves.add(String.valueOf(moveId));
            }
        }
        histogramData.add(series);

        // Update the view with the new data and color mapping
        view.updateHistogramData(histogramData, moveColorMap, String.valueOf(outlierMoves));

        // Add outliers to the legend
        for (String moveName : outlierMoves) {
            view.addLegendItem(moveName);
        }
    }

    private double quartile(List<Long> data, double percentile) {
        Collections.sort(data);
        int n = data.size();
        double position = percentile * (n + 1);
        int posInteger = (int) position;
        double fraction = position - posInteger;
        if (posInteger == 0) {
            return data.get(0);
        } else if (posInteger >= n) {
            return data.get(n - 1);
        } else {
            double lower = data.get(posInteger - 1);
            double upper = data.get(posInteger);
            return lower + fraction * (upper - lower);
        }
    }


}
