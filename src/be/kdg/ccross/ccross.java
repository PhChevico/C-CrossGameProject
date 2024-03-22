package be.kdg.ccross;

import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.authenticationscreens.AuthenticationPresenter;
import be.kdg.ccross.view.authenticationscreens.AuthenticationView;
import be.kdg.ccross.view.registerscreens.RegisterPresenter;
import be.kdg.ccross.view.registerscreens.RegisterView;
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

        GameSession gameSession = new GameSession();
        AuthenticationView authenticationView = new AuthenticationView();
        primaryStage.setScene(new Scene(authenticationView));
        primaryStage.getIcons().add(new Image("images/C-Cross.png"));
        primaryStage.setTitle("C-Cross");
        new AuthenticationPresenter(gameSession, authenticationView);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
