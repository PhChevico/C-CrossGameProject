package be.kdg.ccross.view.boardscreen;
import be.kdg.ccross.model.GameSession;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Iterator;
import java.util.Map;


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



        for (ImageView img : view.getBoardImages().keySet()) {
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("javafx is cool");
                System.out.println(view.getBoardImages().get(img));
                mouseEvent.consume();
            });
        }

        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);

    }



    private void closeApplication(Event event) {
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
