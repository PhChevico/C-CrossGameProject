package be.kdg.ccross.view.boardscreen;

import be.kdg.ccross.model.Board;
import be.kdg.ccross.model.Square;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;


public class BoardView extends GridPane {

    ImageView img = new ImageView(new Image("images/squareTest.jpg"));

    public BoardView(){
        this.initialiseNodes();
        this.layoutNodes();


    }

    private void initialiseNodes(){
    }


    private void layoutNodes() {

    }

    public void boardMaker(List<String> boardAsArray) {

        for (int i = 0; i < boardAsArray.size(); i++) {
                if (boardAsArray.get(i) != null) {
                    String[] parts = boardAsArray.get(i).split("-");
                    ImageView img = new ImageView(new Image("images/squareTest.jpg"));
                    img.setFitWidth(85);
                    img.setFitHeight(85);
                    this.add(img, Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
                } else {
                    // Handle the case when the string is null
                    System.out.println("crash");
                }
            }
        }



//        for(int i = 'A'; i <= 'P'; i ++) {
//            List<Square> list = board.getZone((char) i).getSquareOfZone();
//
//            for (Square square : list) {
//                ImageView img = new ImageView(new Image("images/squareTest.jpg"));
//                img.setFitWidth(85);
//                img.setFitHeight(85);
//                this.add(img, square.getColumn(), square.getRow());
//            }
//        }




    }
