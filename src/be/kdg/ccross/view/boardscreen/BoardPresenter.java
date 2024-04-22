package be.kdg.ccross.view.boardscreen;
import be.kdg.ccross.model.GameSession;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;


public class BoardPresenter {
    private GameSession session; //model
    private BoardView view; //view


    public BoardPresenter(GameSession session, BoardView view){
        this.session = session;
        this.view = view;

        // Show initial board
        this.view.boardMaker(session.getSquaresAsList());//call the method boardMaker to create the board passing the List of squares
                                                         //(see in Board class)
        this.addEventHandlers();

    }


    void addEventHandlers() {


        //return the coordinates of the square.
        for (Map.Entry<String, ImageView> entry : view.getBoardImages().entrySet()) {
            ImageView squareImg = entry.getValue();
            squareImg.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println(entry.getKey());
                checkingActionToSquare(new Pair<>(entry.getKey(), entry.getValue()));
                mouseEvent.consume();
            });
        }
        view.getNextRound().setOnAction(actionEvent -> { //when nextRound is pressed
            session.updateCounter(1);//we update the counter of pawns placed as if 2 pawns were places already even if only 1 was placed
            session.setLastMove(null);
            session.setRound(session.getRound()+1);//we pass to the other round by adding 1 to the number of rounds
        });

        // Add event listener
        view.setHandlerOnPawnCreated((Pair<String, ImageView> pair) -> {
            pair.getValue().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                checkingActionToSquare(pair);
            });
        });





        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);




    }

    private void checkingActionToSquare(Pair<String, ImageView> pair) {
        //if zone not dominated
        if (!session.getBoard().getSquare(pair.getKey()).isStatus() && session.validMove(pair.getKey())) {

            //System.out.println(session.validMove(pair.getKey()));
            //here we put a pawn pair the square
            session.getBoard().getSquare(pair.getKey()).setStatus(true);

            String[] parts = pair.getKey().split("-");
            if(session.getRound()%2==0) {
                view.add(view.createPawn1(pair.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

            }else {
                view.add(view.createPawn2(pair.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }

            System.out.println(session.getBoard().getSquare(pair.getKey()).isStatus());

            session.setLastMove(pair.getKey());
            session.updateCounter(1);

            if(session.getCounter() % 2 == 0){
                session.setLastMove(null);
                session.setRound(session.getRound()+1);

            }
        } else {
            //if zone dominated
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Impossible to put a pawn in that square");
            alert.showAndWait();
        }
    }


    void closeApplication(Event event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png")); // To add an icon
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
