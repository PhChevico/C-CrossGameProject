package be.kdg.ccross.view.boardscreen;
import be.kdg.ccross.model.GameSession;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class BoardPresenter {
    GameSession session; //model
    BoardView view; //view

    public BoardPresenter(GameSession session, BoardView view){
        this.session = session;
        this.view = view;

        // Show initial board
        this.view.boardMaker(session.getSquaresAsList());
        this.addEventHandlers();
    }


    private void addEventHandlers(){

        for (ImageView img : view.getBoardImages()) {
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No HI");
                System.out.println("javafx is cool");
                System.out.println(img.getLayoutX());
                System.out.println(img.getLayoutY());
                mouseEvent.consume();
            });
        }
    }

}
