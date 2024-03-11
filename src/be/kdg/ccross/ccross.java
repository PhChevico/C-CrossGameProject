package be.kdg.ccross;

import be.kdg.ccross.model.GameRunner;
import be.kdg.ccross.model.GameSession;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ccross extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameSession session = new GameSession();
        BoardView boardView = new BoardView();
        primaryStage.setScene(new Scene(boardView));
        new BoardPresenter(session, boardView);

        primaryStage.show();
    }
}
