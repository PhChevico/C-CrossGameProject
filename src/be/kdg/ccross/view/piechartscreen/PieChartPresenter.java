package be.kdg.ccross.view.piechartscreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.leaderboardScreen.LeaderboardPresenter;
import be.kdg.ccross.view.leaderboardScreen.LeaderboardView;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PieChartPresenter {
    private GameSession model;
    private PieChartView view;

    public PieChartPresenter(GameSession model, PieChartView view, String playerName, int wins, int losses) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updatePieChart(playerName, wins, losses);
    }

    private void addEventHandlers() {
        view.getGoBack().setOnAction(actionEvent -> goBackToLeaderboard());
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);

        view.getGoBack().setOnMouseEntered(e -> view.getGoBack().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getGoBack().setOnMouseExited(e -> view.getGoBack().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

    }

    private void updatePieChart(String playerName, int wins, int losses) {
        PieChart.Data slice1 = new PieChart.Data("Wins", wins);
        PieChart.Data slice2 = new PieChart.Data("Losses", losses);
        view.getPieChart().setData(FXCollections.observableArrayList(slice1, slice2));
    }

    private void goBackToLeaderboard() {
        LeaderboardView leaderboardView = new LeaderboardView();
        view.getScene().setRoot(leaderboardView);
        LeaderboardPresenter leaderboardPresenter = new LeaderboardPresenter(model, leaderboardView);
        leaderboardView.getScene().getWindow().sizeToScene();
    }

    private void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png")); // To add an icon
        alert.setHeaderText("You are about to quit the game!");
        alert.setContentText("Do you really want to leave?");
        alert.setTitle("Bye ;-; !");
        alert.getButtonTypes().clear();
        ButtonType yes = new ButtonType("YES");
        ButtonType no = new ButtonType("NO");
        alert.getButtonTypes().addAll(yes, no);
        alert.showAndWait();
        if (alert.getResult().equals(yes)) {
            ((Stage) view.getScene().getWindow()).close();
        } else {
            event.consume();
        }
    }
}
