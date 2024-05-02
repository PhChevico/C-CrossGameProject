package be.kdg.ccross.view.boardscreen;
import be.kdg.ccross.model.Player;
import be.kdg.ccross.model.Square;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;


public class BoardView extends GridPane {

    //static final String IMAGES_SQUARE_TEST_JPG = "images/squareTest.jpg";
    private final int SQUARE_SIZE = 85;//size of the square(same as the pawn)
    private final int PAWN_SIZE = 85;
    private HashMap<String, ImageView> boardImages;//?
    private HashMap<String, ImageView> pawnImages;//?

    private Consumer<Pair<String, ImageView>> onPawnCreated;//?

    private Button nextRound; //button used to place only one pawn nad pass the round to the opponent
    private Label player1pawns; //label used to indicate how many pawns are left for player1
    private Label playerAIpawns; //same but for player 2


    public BoardView(){

        boardImages = new HashMap<String, ImageView>();
        pawnImages = new HashMap<String, ImageView>();
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
        nextRound = new Button("Next Round");
        player1pawns = new Label("= 12");
        playerAIpawns = new Label("= 12");
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(56, 36, 1, 1), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setHandlerOnPawnCreated(Consumer<Pair<String, ImageView>> event){
        onPawnCreated = event;//?
    }

    private void layoutNodes() {
        //board layout

        add(nextRound,1,0);//adding nextRound button to the view
        nextRound.setStyle("-fx-background-color: rgba(65,255,12,0.27); -fx-text-fill: #d38103; -fx-font-weight: bold");
        nextRound.setVisible(false);

        ImageView pawn1 = new ImageView(new Image("images/PinoVerde.png"));

        pawn1.setFitHeight(PAWN_SIZE);
        pawn1.setFitWidth(PAWN_SIZE);
        add(pawn1,0,1);
        add(player1pawns,1,1);
        player1pawns.setStyle("-fx-font-weight: bold; -fx-text-fill: #37ff00");

        ImageView pawn2 = new ImageView(new Image("images/PinoLaranja.png"));

        pawn2.setFitHeight(PAWN_SIZE);
        pawn2.setFitWidth(PAWN_SIZE);
        add(pawn2,0,2);
        add(playerAIpawns,1,2);
        playerAIpawns.setStyle("-fx-font-weight: bold; -fx-text-fill: #d38103");

    }


    HashMap<String, ImageView> getBoardImages() {
        return boardImages;
    }

    void boardMaker(List<String> boardAsArray) {

        System.out.println(boardAsArray);

        int counter = 1; //counter used to choose the right pic for the right angle of the square(see images "Fundo..." in resources)
        int counterForFlip = 0; //used to understand if the C shape is either reversed or normal
        int countingWhenFlip = 1;
        for (String boardAsList : boardAsArray) {
            if (boardAsList != null) {//check everytime if the square is actually used(part of the board) or not
                //splitting the list in by "-"

                String[] parts = boardAsList.split("-");//filling an array of string with the squares
                // Creating the image and setting the size of the images
                String filename = "images/Fundo_Liso_0" + counter + ".png";//choose the right image for the right spot
                Image image = new Image(filename);//passing the image inside an image variable
                ImageView img = new ImageView(image);//to then pass it in the imageView
                img.setFitWidth(SQUARE_SIZE);
                img.setFitHeight(SQUARE_SIZE);//setting the image size

                //defines the NEXT image that's gonna be selected
                counter = (counter % 5) + 1;

                if (counterForFlip % 5 == 0 && counterForFlip != 0) {//image is gonna rotate so for example from the second zone we gonna rotate everything
                    // Flip the image
                    countingWhenFlip++;
                }
                counterForFlip++;
                if(countingWhenFlip % 2 == 0){//one rotate,one not,one rotate,one not...(check if it's divisible for 2)
                    img.setRotate(180);
                }

                // Adding the image to the view
                add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));/*since the coordinates are split
                (so the parts[] array looks like parts = {12-3}) we transform part[0](which is the string "12") into a integer(12) and use that
                to position the image on the board. -->GridPane
                each for loop the parts[] array is gonna get OVERWRITTEN with the new coordinates of the next
                square in the BoardAsArray*/

                // Putting the image into the map
                getBoardImages().put(boardAsList, img);//we update the HashMap with the BoardImage for
                                                       //for game logic(see in BoardPresenter)
            }
        }


    }


    ImageView createPawn1(String coordinates){//create pawn for player 1
        ImageView pawn = new ImageView(new Image("images/PinoVerde.png"));

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
    ImageView createPawn2(String coordinates){//create pawn for player 2
        ImageView pawn = new ImageView(new Image("images/PinoLaranja.png"));

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
    public void addZonePlayer1(List<Square> zoneAsList,boolean rotate) {
        int counter = 1;

        for (Square s : zoneAsList) {
            String[] parts = s.getCoordinates().split("-");
            String filename = "images/Verde0"+counter+".png";
            Image image = new Image(filename);
            ImageView img = new ImageView(image);
            img.setFitWidth(SQUARE_SIZE);
            img.setFitHeight(SQUARE_SIZE);
            counter = (counter % 5) + 1;


            if (rotate) {
                img.setRotate(180);
            }
            add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

    }
    public void addZonePlayerAI(List<Square> zoneAsList,boolean rotate){
        int counter = 1;
        int counterForFlip=0;
        int countingWhenFlip = 1;

        for (Square s : zoneAsList) {
            String[] parts = s.getCoordinates().split("-");
            String filename = "images/Laranja0"+counter+".png";
            Image image = new Image(filename);
            ImageView img = new ImageView(image);
            img.setFitWidth(SQUARE_SIZE);
            img.setFitHeight(SQUARE_SIZE);
            counter = (counter % 5) + 1;

            if (rotate) {
                img.setRotate(180);
            }
            add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
    }

    public Button getNextRound() { //used for eventHandler
        return nextRound;
    }

    public Label getPlayer1pawns() {
        return player1pawns;
    }

    public Label getPlayerAIpawns() {
        return playerAIpawns;
    }
}






