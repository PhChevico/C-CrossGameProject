package be.kdg.ccross.view.selectModeScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.selectlevelScreen.SelectLevelPresenter;
import be.kdg.ccross.view.selectlevelScreen.SelectLevelView;
import be.kdg.ccross.view.multiplayerscreen.MultiPlayerPresenter;
import be.kdg.ccross.view.multiplayerscreen.MultiPlayerView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SelectModePresenter {
    private GameSession model;
    private SelectModeView view;

    public SelectModePresenter(GameSession model, SelectModeView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getSinglePlayer().setOnAction(actionEvent -> setSelectLevelView());
        view.getMultiPlayer().setOnAction(actionEvent -> setMultiPlayerView());
        view.getGoBack().setOnAction(actionEvent -> setHomeScreenView());

        view.getSinglePlayer().setOnMouseEntered(e -> view.getSinglePlayer().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getSinglePlayer().setOnMouseExited(e -> view.getSinglePlayer().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getMultiPlayer().setOnMouseEntered(e -> view.getMultiPlayer().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getMultiPlayer().setOnMouseExited(e -> view.getMultiPlayer().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getGoBack().setOnMouseEntered(e -> view.getGoBack().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getGoBack().setOnMouseExited(e -> view.getGoBack().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getExit().setOnMouseEntered(e -> view.getExit().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getExit().setOnMouseExited(e -> view.getExit().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));

        view.getExit().setOnAction(this::closeApplication);
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
    }

    private void setSelectLevelView() {
        SelectLevelView selectLevelView = new SelectLevelView();
        view.getScene().setRoot(selectLevelView);
        new SelectLevelPresenter(model, selectLevelView);
        selectLevelView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - selectLevelView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - selectLevelView.getScene().getWindow().getHeight() / 2;
        selectLevelView.getScene().getWindow().setX(centerX);
        selectLevelView.getScene().getWindow().setY(centerY);
    }

    private void setMultiPlayerView() {
        MultiPlayerView multiPlayerView = new MultiPlayerView();
        view.getScene().setRoot(multiPlayerView);
        new MultiPlayerPresenter(model, multiPlayerView);
        multiPlayerView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - multiPlayerView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - multiPlayerView.getScene().getWindow().getHeight() / 2;
        multiPlayerView.getScene().getWindow().setX(centerX);
        multiPlayerView.getScene().getWindow().setY(centerY);
    }

    private void setHomeScreenView() {
        HomeScreenView homeScreenView = new HomeScreenView();
        view.getScene().setRoot(homeScreenView);
        new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - homeScreenView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - homeScreenView.getScene().getWindow().getHeight() / 2;
        homeScreenView.getScene().getWindow().setX(centerX);
        homeScreenView.getScene().getWindow().setY(centerY);
    }

    private void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) view.getScene().getWindow();
                stage.close();
            } else {
                event.consume();
            }
        });
    }
}
