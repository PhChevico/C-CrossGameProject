package be.kdg.ccross.view.pieChartScreen;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

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
        this.setBottom(goBackButton);
        setCenter(pieChart);
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public void updatePieChartData(ObservableList<PieChart.Data> data) {
        pieChart.setData(data);
    }
}
