package be.kdg.ccross.view.homeScreen;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeScreenPresenter {

    private GameSession model;
    private HomeScreenView view;


    public HomeScreenPresenter(GameSession model, HomeScreenView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    void addEventHandlers() {
        view.getPlay().setOnAction(actionEvent -> setGameView());

        view.getExit().setOnAction(this::closeApplication);
        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);
    }

    void setGameView(){

        BoardView boardView = new BoardView();
        view.getScene().setRoot(boardView);
        BoardPresenter boardPresenter = new BoardPresenter(model, boardView);
        boardView.getScene().getWindow().sizeToScene();
        double centerX = Screen.getPrimary().getVisualBounds().getWidth() / 2 - boardView.getScene().getWindow().getWidth() / 2;
        double centerY = Screen.getPrimary().getVisualBounds().getHeight() / 2 - boardView.getScene().getWindow().getHeight() / 2;
        boardView.getScene().getWindow().setX(centerX);
        boardView.getScene().getWindow().setY(centerY);

    }

    void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
