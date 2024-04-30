package be.kdg.ccross.view.visualPresetationScreen;

import be.kdg.ccross.model.GameSession;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class VisualPresentation extends Application {

    private GameSession session;

    public VisualPresentation(GameSession session) {
        this.session = session;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create X and Y axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Turn/Move");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Duration (seconds)");

        // Create LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Duration per Turn/Move");

        // Add data series for each player
        //addDataSeries(lineChart, session.getPlayer1().getName(), session.getPlayer1().);
        //addDataSeries(lineChart, "AI", session.getPlayerAI().getTurnDurations());

        // Create scene
        Scene scene = new Scene(lineChart, 800, 600);

        // Set stage properties
        primaryStage.setTitle("Visual Presentation");
        primaryStage.getIcons().add(new Image("images/C-Cross.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addDataSeries(LineChart<Number, Number> lineChart, String playerName, List<Long> durations) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(playerName);

        // Add data points to the series
        for (int i = 0; i < durations.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, durations.get(i) / 1000)); // Convert milliseconds to seconds
        }

        // Add series to the chart
        lineChart.getData().add(series);
    }

    public void displayVisualPresentation() {
        // Launch JavaFX application
        launch();
    }
}

