package be.kdg.ccross.view.boardscreen;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;


public class BoardView extends GridPane {

    static final String IMAGES_SQUARE_TEST_JPG = "images/squareTest.jpg";
    private final int SQUARE_SIZE = 85;
    private final int PAWN_SIZE = SQUARE_SIZE / 2;
    private HashMap<String, ImageView> boardImages = new HashMap<String, ImageView>();
    private HashMap<String, ImageView> pawnImages = new HashMap<String, ImageView>();

    private Consumer<Pair<String, ImageView>> onPawnCreated;


    public BoardView(){

        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
    }

    public void setHandlerOnPawnCreated(Consumer<Pair<String, ImageView>> event){
        onPawnCreated = event;
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

        // Calculate the translation to center the pawn within the square
        double translationX = (SQUARE_SIZE - PAWN_SIZE) / 2;

        pawn.setTranslateX(translationX);

        pawnImages.put(coordinates, pawn);

        // Inform listeners
        onPawnCreated.accept(new Pair<>(coordinates, pawn));

        return pawn;
    }
}






