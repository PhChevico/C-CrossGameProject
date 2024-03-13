package be.kdg.ccross;

import be.kdg.ccross.model.GameRunner;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import be.kdg.ccross.view.homeScreen.HomeScreenPresenter;
import be.kdg.ccross.view.homeScreen.HomeScreenView;
import be.kdg.ccross.view.registerscreen.RegisterPresenter;
import be.kdg.ccross.view.registerscreen.RegisterView;
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
//        GameSession session = new GameSession();
//        HomeScreenView homeScreenView = new HomeScreenView();
//        primaryStage.setScene(new Scene(homeScreenView));
//        primaryStage.getIcons().add(new Image("images/Instagram_icon.png"));
//        primaryStage.setMinWidth(300);
//        primaryStage.setMinHeight(600);
//        new HomeScreenPresenter(session, homeScreenView);
//        primaryStage.show();
        GameSession gameSession = new GameSession();
        RegisterView registerView = new RegisterView();
        primaryStage.setScene(new Scene(registerView));
        new RegisterPresenter(gameSession, registerView);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(400);
        primaryStage.show();


    }
}
