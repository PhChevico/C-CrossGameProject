package be.kdg.ccross.view.piechartscreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class PieChartView extends VBox {
    private PieChart pieChart;
    private Button goBack;

    public PieChartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        pieChart = new PieChart();
        goBack = new Button("Go back");
        goBack.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px;");
    }

    private void layoutNodes() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.png", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.setBackground(new Background(backgroundImage));

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));

        goBack.setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold");

        this.getChildren().addAll(pieChart, goBack);
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public Button getGoBack() {
        return goBack;
    }
}
