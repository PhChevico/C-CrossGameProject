package be.kdg.ccross.view.boardscreen;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Shape3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BoardView extends GridPane {

    static final String IMAGES_SQUARE_TEST_JPG = "images/squareTest.jpg";
    private final int SQUARE_SIZE = 85;
    private HashMap<String, ImageView> boardImages = new HashMap<String, ImageView>();
    Circle pawn;



    public BoardView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
        pawn = new Circle();
    }


    private void layoutNodes() {
        //board layout
        this.setAlignment(Pos.CENTER);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("images/bg1.jpg", false),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));


        //pawn layout
        pawn.setRadius(10);
    }


    public HashMap<String, ImageView> getBoardImages() {
        return boardImages;
    }

    public void boardMaker(List<String> boardAsArray) {

        System.out.println(boardAsArray);
        for (String boardAsList : boardAsArray) {
            if (boardAsList != null) {
                //splitting the list in by "-"
                String[] parts = boardAsList.split("-");

                //creating the image and setting the size of the images
                ImageView img = new ImageView(IMAGES_SQUARE_TEST_JPG);
                img.setFitWidth(SQUARE_SIZE);
                img.setFitHeight(SQUARE_SIZE);
                //adding the image to the view
                add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                getBoardImages().put(boardAsList, img);

            }
        }
    }

}






