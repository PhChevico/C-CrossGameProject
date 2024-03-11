package be.kdg.ccross;

import be.kdg.ccross.model.Board;
import be.kdg.ccross.model.GameRunner;
import be.kdg.ccross.model.Square;
import be.kdg.ccross.view.boardscreen.BoardPresenter;
import be.kdg.ccross.view.boardscreen.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class ccross extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        GameRunner game = new GameRunner();
        BoardView boardView = new BoardView();
        primaryStage.setScene(new Scene(boardView));
        new BoardPresenter(game, boardView);

        primaryStage.show();
    }
}
