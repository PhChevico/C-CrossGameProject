package be.kdg.ccross;

import be.kdg.ccross.model.GameSession;
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
        RegisterView registerView = new RegisterView();
        primaryStage.setScene(new Scene(registerView));
        primaryStage.getIcons().add(new Image("images/C-Cross.png"));
        primaryStage.setTitle("C-Cross");
        new RegisterPresenter(gameSession, registerView);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(400);
        primaryStage.show();

//        GameSession gameSession = new GameSession();
//        RegisterView authenticationView = new RegisterView();
//        primaryStage.setScene(new Scene(authenticationView));
//        new RegisterPresenter(gameSession, authenticationView);
//        primaryStage.setMinHeight(500);
//        primaryStage.setMinWidth(400);
//        primaryStage.show();




    }
}
