package be.kdg.ccross.view.boardscreen;

import be.kdg.ccross.model.Board;
import be.kdg.ccross.model.GameRunner;
import be.kdg.ccross.model.Square;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;

public class BoardPresenter {
    GameRunner gameRunner;
    Board board = new Board();
    BoardView view;

    public BoardPresenter(GameRunner gameRunner, BoardView view){
        this.gameRunner = gameRunner;
        this.view = view;
        this.boardMaker();
        this.addEventHandlers();
    }


    private void addEventHandlers(){
        view.img.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("javafx sucks");
            mouseEvent.consume();
        });
    }


    public void boardMaker() {

        for(int i = 'A'; i <= 'P'; i ++) {
            List<Square> list = board.getZone((char) i).getSquareOfZone();

            for (Square square : list) {
                ImageView img = new ImageView(new Image("images/squareTest.jpg"));
                img.setFitWidth(70);
                img.setFitHeight(70);
                view.add(img, square.getColumn(), square.getRow());
            }
        }


    }



}
