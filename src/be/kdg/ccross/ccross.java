package be.kdg.ccross;

import be.kdg.ccross.model.GameRunner;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ccross extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameSession session = new GameSession();
        HomeScreenView homeScreenView = new HomeScreenView();
        primaryStage.setScene(new Scene(homeScreenView));
        primaryStage.getIcons().add(new Image("images/Instagram_icon.png"));
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(600);
        new HomeScreenPresenter(session, homeScreenView);
        primaryStage.show();


    }
}
