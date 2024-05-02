package be.kdg.ccross.view.pieChartScreen;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class PieChartView extends BorderPane {
    private Button goBackButton;
    private PieChart pieChart;

    public PieChartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        goBackButton = new Button("Go back to Main Menu");
        pieChart = new PieChart();
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
        setCenter(pieChart);
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public void updatePieChartData(ObservableList<PieChart.Data> data) {
        pieChart.setData(data);
    }
}
