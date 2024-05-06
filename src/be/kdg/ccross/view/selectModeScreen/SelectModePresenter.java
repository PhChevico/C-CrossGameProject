package be.kdg.ccross.view.selectModeScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.singleplayerscreen.SinglePlayerPresenter;
import be.kdg.ccross.view.singleplayerscreen.SinglePlayerView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import be.kdg.ccross.view.multiplayerscreen.MultiPlayerPresenter;
import be.kdg.ccross.view.multiplayerscreen.MultiPlayerView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SelectModePresenter {

    private GameSession model;
    private SelectModeView view;


    public SelectModePresenter(GameSession model, SelectModeView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    void addEventHandlers() {
        view.getSinglePlayer().setOnAction(actionEvent -> setSinglePlayerView());

        view.getMultiPlayer().setOnAction(actionEvent -> setMultiPlayerView());

        view.getGoBack().setOnAction(actionEvent -> setHomeScreenView());

        view.getExit().setOnAction(this::closeApplication);
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);

    }

    void setSinglePlayerView(){

        SinglePlayerView singlePlayerView = new SinglePlayerView();
        view.getScene().setRoot(singlePlayerView);
        SinglePlayerPresenter singlePlayerPresenter = new SinglePlayerPresenter(model, singlePlayerView);
        singlePlayerView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - singlePlayerView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - singlePlayerView.getScene().getWindow().getHeight() / 2;
        singlePlayerView.getScene().getWindow().setX(centerX);
        singlePlayerView.getScene().getWindow().setY(centerY);

    }
    void setMultiPlayerView(){

        MultiPlayerView multiPlayerView = new MultiPlayerView();
        view.getScene().setRoot(multiPlayerView);
        MultiPlayerPresenter multiPlayerPresenter = new MultiPlayerPresenter(model, multiPlayerView);
        multiPlayerView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - multiPlayerView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - multiPlayerView.getScene().getWindow().getHeight() / 2;
        multiPlayerView.getScene().getWindow().setX(centerX);
        multiPlayerView.getScene().getWindow().setY(centerY);

    }

    private void setHomeScreenView(){

        HomeScreenView homeScreenView = new HomeScreenView();
        Scene scene = view.getScene();
        scene.setRoot(homeScreenView);
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(true);
        stage.setTitle("C-Cross");
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter(model, homeScreenView);
        homeScreenView.getScene().getWindow().sizeToScene();

    }

    void closeApplication(Event event) {
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
            ((Stage)view.getScene().getWindow()).close();
        } else {
            event.consume();
        }
    }



}
