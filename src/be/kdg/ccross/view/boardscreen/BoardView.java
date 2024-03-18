package be.kdg.ccross.view.boardscreen;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.List;


public class BoardView extends GridPane {

    static final String IMAGES_SQUARE_TEST_JPG = "images/squareTest.jpg";
    private final int SQUARE_SIZE = 85;
    private final int PAWN_SIZE = SQUARE_SIZE / 2;
    private HashMap<String, ImageView> boardImages = new HashMap<String, ImageView>();
    private HashMap<String, ImageView> pawnImages = new HashMap<String, ImageView>();




    public BoardView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
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

    }


    HashMap<String, ImageView> getBoardImages() {
        return boardImages;
    }

    void boardMaker(List<String> boardAsArray) {

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

    ImageView createPawn(String coordinates){
        ImageView pawn = new ImageView(new Image("images/pawn.png"));

        pawn.setFitHeight(PAWN_SIZE);
        pawn.setFitWidth(PAWN_SIZE);
        pawn.setTranslateX(Pos.CENTER.ordinal());
        pawn.setTranslateY(Pos.CENTER.ordinal());
        pawnImages.put(coordinates, pawn);
        return pawn;
    }
}






