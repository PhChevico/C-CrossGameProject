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
    private GameSession session; //model
    private BoardView view; //view

    public BoardPresenter(GameSession session, BoardView view){
        this.session = session;
        this.view = view;

        // Show initial board
        this.view.boardMaker(session.getSquaresAsList());
        this.addEventHandlers();
    }


    void addEventHandlers(){


        //return the coordinates of the square.
        for (Map.Entry<String, ImageView> entry : view.getBoardImages().entrySet()) {
            ImageView img = entry.getValue();
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                System.out.println(entry.getKey());

                //if zone not dominated
                if(!session.getBoard().getSquare(entry.getKey()).isStatus()){
                    session.getBoard().getSquare(entry.getKey()).setStatus(true);

                    //here we put a pawn into the square
                    String[] parts = entry.getKey().split("-");
                    view.add(view.createPawn(entry.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                    System.out.println(session.getBoard().getSquare(entry.getKey()).isStatus());
                } else {
                    //if zone dominated
                    System.out.println("Impossible to put a pawn in that square");
                }


                // gives the key corresponding to the clicked image
                //session.handleClickBoard(entry.getKey());

                mouseEvent.consume();
            });
        }


        //put a pawn into the square


        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);

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
