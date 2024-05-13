package be.kdg.ccross.view.histogramScreen;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HistogramView extends BorderPane {
    private Button goBackButton;
    private BarChart<String, Number> barChart;
    private Set<String> addedLegendItems = new HashSet<>();

    public HistogramView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        goBackButton = new Button("Go back to Main Menu");
        barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
    }

    private void layoutNodes() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        goBackButton.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #d38103; -fx-font-weight: bold");
        this.setBottom(goBackButton);
        setAlignment(goBackButton, Pos.CENTER);
        setCenter(barChart);

        // Adjust the scale of the X-axis
        CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
        xAxis.setLabel("Move Number");

        // Adjust the scale of the Y-axis
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setLabel("Move Time");
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public void updateHistogramData(ObservableList<XYChart.Series<String, Number>> data, Map<String, String> moveColorMap, String outlierMoveName) {
        barChart.getData().clear();
        barChart.getData().addAll(data);

        // Set color for each series (move)
        int i = 0;
        for (XYChart.Series<String, Number> series : data) {
            String moveName = series.getName();
            String color = moveColorMap.get(moveName);
            if (moveName != null && moveName.equals(outlierMoveName)) {
                color = "green"; // Green color for outliers
            }
            if (series.getNode() != null) {
                series.getNode().setStyle("-fx-bar-fill: " + color + ";");
            }
            i++;
        }
    }

    public void addLegendItem(String label) {
        // Check if the legend item is already added
        if (!addedLegendItems.contains(label)) {
            // Add the legend item below the chart
            Label outlierLabel = new Label("Outlier--> Move number: " + label);
            if (getBottom() instanceof VBox) {
                VBox bottomVBox = (VBox) getBottom();
                bottomVBox.getChildren().add(outlierLabel);
            } else {
                VBox newBottomVBox = new VBox();
                setBottom(newBottomVBox);
                newBottomVBox.getChildren().add(outlierLabel);
                newBottomVBox.setAlignment(Pos.CENTER);
            }
            outlierLabel.setStyle("-fx-text-fill: #1d7903; -fx-font-weight: bold");
            addedLegendItems.add(label);
        }
    }


}
