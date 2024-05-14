package be.kdg.ccross.view.multiplayerscreen;

import be.kdg.ccross.model.*;
import be.kdg.ccross.view.histogramScreen.*;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;


public class MultiPlayerPresenter {
    private GameSession session; //model
    private MultiPlayerView view; //view


    public MultiPlayerPresenter(GameSession session, MultiPlayerView view){
        this.session = session;
        this.view = view;
        session.getGameTime().start();//start the game time
        session.getDatabase().insertNewGame(session.getDatabase().generateGameId(),session.getGameTime().getStartTime());//create a new game in the database
        session.getMove().getGameTime().start();//start the move time for the first move
        // Show initial board
        this.view.boardMaker(session.getSquaresAsList());//call the method boardMaker to create the board passing the List of squares
        //(see in Board class)
        this.addEventHandlers();

    }

    void addEventHandlers() {

        view.getNextRound().setOnMouseEntered(e -> view.getNextRound().setStyle("-fx-background-color: orange; -fx-text-fill: #4f2e00; -fx-font-weight: bold"));
        view.getNextRound().setOnMouseExited(e -> view.getNextRound().setStyle("-fx-background-color: rgba(55,255,0,0.27); -fx-text-fill: #4f2e00; -fx-font-weight: bold"));



        //return the coordinates of the square.
        for (Map.Entry<String, ImageView> entry : view.getBoardImages().entrySet()) {
            ImageView squareImg = entry.getValue();
            squareImg.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                checkingActionToSquare(new Pair<>(entry.getKey(), entry.getValue()));
                mouseEvent.consume();
            });

        }
        view.getNextRound().setOnAction(actionEvent -> { //when nextRound is pressed
            goNextRound();//we call the method to go to the next round
        });

        // Add event listener
        view.setHandlerOnPawnCreated((Pair<String, ImageView> pair) -> {
            pair.getValue().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                checkingActionToSquare(pair);
            });
        });


        view.getScene().getWindow().setOnCloseRequest(this::closeApplication);//close the app




    }

    private void checkingActionToSquare(Pair<String, ImageView> pair) {

        if (!session.getBoard().getSquare(pair.getKey()).isStatus() && session.validMove(pair.getKey())){


            //System.out.println(session.validMove(pair.getKey()));
            //here we put a pawn pair the square
            session.getBoard().getSquare(pair.getKey()).setStatus(true);

            String[] parts = pair.getKey().split("-");
            if(session.getRound()%2==0) {
                view.add(view.createPawn1(pair.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));//create and place the pawn on the board
                session.getBoard().getSquare(pair.getKey()).setOwnership(session.getPlayer1());//we retrieve the square with coordinate and we pass the ownership of
                // who clicked on that square
            }else {
                view.add(view.createPawn2(pair.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                session.getBoard().getSquare(pair.getKey()).setOwnership(session.getPlayer2());//we do the same but for player 2

            }
            System.out.println(session.getBoard().getSquare(pair.getKey()).isStatus());

            session.setLastMove(pair.getKey());
            session.updateCounter(1);
            view.getNextRound().setVisible(true);

            if(session.getRound()%2==0){//decrease player Pawns by 2
                session.getPlayer1().decreasePawnNumber(1);
            }else {
                session.getPlayer2().decreasePawnNumber(1);
            }

            if(session.getCounter() % 2 == 0){
                if(session.getRound()%2==0){//decrease player Pawns by 1
                    session.getMove().getGameTime().stop();
                    session.getDatabase().storeMoveData(session.getDatabase().getGameId(),session.getPlayer1().getName(),session);

                }else {
                    session.getMove().getGameTime().start();
                }


                session.setLastMove(null);
                session.setRound(session.getRound()+1);
                view.getNextRound().setVisible(false);
            }
        } else {
            //if zone dominated
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Impossible to put a pawn in that square");
            alert.showAndWait();
        }
        session.dominateZones();
        CheckZonesClaimed();//check if player dominated the zone
        CheckPlayerWon();//check if player won

        view.getPlayer1pawns().setText("= " + String.valueOf(session.getPlayer1().getPawnNumber()));//update the label of player1 for the number of Pawns left
        view.getPlayer2pawns().setText("= " + String.valueOf(session.getPlayer2().getPawnNumber()));//update the label of player1 for the number of Pawns left

    }
    public void addZonePlayer1(List<Square> zoneAsList,boolean rotate) {
        int counter = 1;

        for (Square s : zoneAsList) {
            String[] parts = s.getCoordinates().split("-");
            String filename = "images/Verde0"+counter+".png";
            Image image = new Image(filename);
            ImageView img = new ImageView(image);
            img.setFitWidth(view.getSQUARE_SIZE());
            img.setFitHeight(view.getSQUARE_SIZE());
            counter = (counter % 5) + 1;


            if (rotate) {
                img.setRotate(180);
            }
            view.add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

    }
    public void addZonePlayer2(List<Square> zoneAsList,boolean rotate){
        int counter = 1;
        int counterForFlip=0;
        int countingWhenFlip = 1;

        for (Square s : zoneAsList) {
            String[] parts = s.getCoordinates().split("-");
            String filename = "images/Laranja0"+counter+".png";
            Image image = new Image(filename);
            ImageView img = new ImageView(image);
            img.setFitWidth(view.getSQUARE_SIZE());
            img.setFitHeight(view.getSQUARE_SIZE());
            counter = (counter % 5) + 1;

            if (rotate) {
                img.setRotate(180);
            }
            view.add(img, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
    }
    public void goNextRound(){


        if(session.getRound()%2==0){//decrease player Pawns by 1
            session.getPlayer1().decreasePawnNumber(1);
        }else {
            session.getPlayer2().decreasePawnNumber(1);
        }
        session.updateCounter(1);//we update the counter of pawns placed as if 2 pawns were places already even if only 1 was placed
        if(session.getRound()%2==0){//decrease player Pawns by 1
            session.getPlayer1().addPawnNumber(1);
        }else {
            session.getPlayer2().addPawnNumber(1);
        }
        session.setLastMove(null);
        session.setRound(session.getRound()+1);//we go to the other round by adding 1 to the number of rounds

        view.getNextRound().setVisible(false);//we make the nextRound button non-visible
        if(session.getRound()%2==0){//stop the time and start the time for the other player
            session.getMove().getGameTime().stop();
            session.getDatabase().storeMoveData(session.getDatabase().getGameId(),session.getPlayer1().getName(),session);
        }else {
            session.getMove().getGameTime().start();
        }




        session.dominateZones();
        CheckZonesClaimed();//check if player dominated the zone
        CheckPlayerWon();//check if player won


    }

    private void CheckZonesClaimed(){//used to check the zones claimed.We use this method each time a pawn is placed
        for (int i = 65; i <= 80; i++) {//loop through all the zones (from A to P in integer)
            List<Square> checkingSquares = session.getBoard().getZone((char)i).getSquareOfZone();//pass the list of squares of a single zone to a list
            if(session.getBoard().getZone((char)i).getOwner()==session.getPlayer1()){//if the zone is owned by player 1
                addZonePlayer1(checkingSquares,session.getBoard().getZone((char)i).isRotate());//add the zone visually by calling the method


            };
            if (session.getBoard().getZone((char)i).getOwner()==session.getPlayer2()){//same as previous step
                addZonePlayer2(checkingSquares,session.getBoard().getZone((char)i).isRotate());


            }
        }
    }

    public void CheckPlayerWon(){//used method to check if the player has either connected the zones or opponent's pawns==0;

        if(session.getEndGame().Checkpawns(session.getPlayer1(), session.getPlayer2())==2|| //if player 1 won
                session.getEndGame().CheckZones(session.getPlayer1(),session.getBoard())){
            session.getGameTime().stop();//we stop the timer
            session.getDatabase().updateGameStats(session.getDatabase().getGameId(),session.getPlayer1(),session.getGameTime());//send data to the database
            displaySummary(true);//display the game summery



        }else if(session.getEndGame().Checkpawns(session.getPlayer1(), session.getPlayer2())==1 || //if AI won
                session.getEndGame().CheckZones(session.getPlayer2(),session.getBoard())){
            session.getGameTime().stop();
            session.getDatabase().updateGameStats(session.getDatabase().getGameId(),session.getPlayer2(),session.getGameTime());
            displaySummary(false);


        }
    }

    public void displaySummary(boolean winner) {
        long totalPlayTimeMillis = this.session.getGameTime().getElapsedTime();
        String playerWinner;
        if (winner) {
            playerWinner = this.session.getPlayer1().getName();
        } else {
            playerWinner = "AI";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of Game Summary");
        alert.setHeaderText(null);
        alert.setContentText("Winner: " + (winner ? session.getPlayer1().getName() : "AI") + "\nTotal Play Time: " +
                ((double) (totalPlayTimeMillis)) / 1000 + " seconds\n" +
                "Total Rounds " + session.getPlayer1().getName() + ": " + session.getRound() / 2 + " Rounds\n" +
                "Average Duration per Move: " + ((double) (totalPlayTimeMillis / (session.getRound() / 2))) / 1000 + " seconds"
        );

        ButtonType closeButton = new ButtonType("Visual Presentation");
        ButtonType exitButton = new ButtonType("Exit");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(closeButton, exitButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/C-Cross.png"));

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == closeButton) {
                reset();
                // Create PieChartView and Presenter
                HistogramView histogramView = new HistogramView();
                HistogramPresenter histogramPresenter = new HistogramPresenter(session,histogramView);

                // Update view with move data
                histogramPresenter.updateView(session.getDatabase().getGameId());
                // Show PieChartView in a new window
                Scene scene = new Scene(histogramView);
                Stage pieChartStage = new Stage();
                pieChartStage.setScene(scene);
                pieChartStage.setTitle("Move Duration per Player");
                pieChartStage.show();
                ((Stage)view.getScene().getWindow()).close();
            } else if (buttonType == exitButton) {
                reset();
                // Exit the application
                System.exit(0);
            }
        });
    }

    void closeApplication(Event event) {//alert to in case of end application
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

    public void reset(){//used to reset all variables
        session = new GameSession();
        // Update the UI with the new session and view
    }


}

