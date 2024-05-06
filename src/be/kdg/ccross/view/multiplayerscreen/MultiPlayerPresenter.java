package be.kdg.ccross.view.multiplayerscreen;

import be.kdg.ccross.model.*;
import be.kdg.ccross.view.pieChartScreen.PieChartPresenter;
import be.kdg.ccross.view.pieChartScreen.PieChartView;
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
        session.getGameTime().start();
        session.getDatabase().insertNewGame(session.getDatabase().generateGameId(),session.getGameTime().getStartTime());
        session.getPlayer1().getMove().getGameTime().start();
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
            if(session.getRound()%2==0){//decrease player Pawns by 1
                session.getPlayer1().getMove().getGameTime().stop();
                session.getPlayer2().getMove().getGameTime().start();
            }else {
                session.getPlayer2().getMove().getGameTime().stop();
                session.getPlayer1().getMove().getGameTime().start();
            }

            session.getDatabase().storeMoveData(1,session.getPlayer1().getName(),session);


            session.dominateZones();
            CheckZonesClaimed();//check if player dominated the zone
            CheckPlayerWon();//check if player won


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
                    session.getPlayer1().getMove().getGameTime().stop();
                    session.getPlayer2().getMove().getGameTime().start();
                }else {
                    session.getPlayer2().getMove().getGameTime().stop();
                    session.getPlayer1().getMove().getGameTime().start();
                }

                if(session.getRound()%2==0){//decrease player Pawns by 2
                    session.getDatabase().storeMoveData(session.getDatabase().getGameId(),session.getPlayer1().getName(),session);
                }else {
                    session.getDatabase().storeMoveData(session.getDatabase().getGameId(),session.getPlayerAI().getName(),session);
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

    private void CheckZonesClaimed(){//used to check the zones claimed.We use this method each time a pawn is placed
        for (int i = 65; i <= 80; i++) {//loop through all the zones (from A to P in integer)
            List<Square> checkingSquares = session.getBoard().getZone((char)i).getSquareOfZone();//pass the list of squares of a single zone to a list
            if(session.getBoard().getZone((char)i).getOwner()==session.getPlayer1()){//if the zone is owned by player 1
                view.addZonePlayer1(checkingSquares,session.getBoard().getZone((char)i).isRotate());//add the zone visually by calling the method


            };
            if (session.getBoard().getZone((char)i).getOwner()==session.getPlayer2()){//same as previous step
                view.addZonePlayer2(checkingSquares,session.getBoard().getZone((char)i).isRotate());


            }
        }
    }

    public void CheckPlayerWon(){//used method to check if the player has either connected the zones or opponent's pawns==0;

        if(session.getEndGame().Checkpawns(session.getPlayer1(), session.getPlayerAI())==2|| //if player 1 won
                session.getEndGame().CheckZones(session.getPlayer1(),session.getBoard())){
            session.getGameTime().stop();//we stop the timer
            session.getDatabase().updateGameStats(session.getDatabase().getGameId(),session.getPlayer1(),session.getGameTime());//send data to the database
            displaySummary(true);//display the game summery
            reset();//reset the game variables

        }else if(session.getEndGame().Checkpawns(session.getPlayer1(), session.getPlayerAI())==1 || //if AI won
                session.getEndGame().CheckZones(session.getPlayerAI(),session.getBoard())){
            session.getGameTime().stop();
            session.getDatabase().updateGameStats(session.getDatabase().getGameId(),session.getPlayerAI(),session.getGameTime());
            displaySummary(false);
            reset();
        }
    }

    public void displaySummary(boolean winner) {
        long totalPlayTimeMillis = session.getGameTime().getElapsedTime();
        String playerWinner;
        if (winner) {
            playerWinner = session.getPlayer1().getName();
        } else {
            playerWinner = session.getPlayer2().getName();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of Game Summary");
        alert.setHeaderText(null);
        alert.setContentText("Winner: " + (winner ? session.getPlayer1().getName() : session.getPlayer2().getName()) + "\nTotal Play Time: " +
                ((double) (totalPlayTimeMillis)) / 1000 + " seconds\n" +
                "Total Rounds " + (winner ? session.getPlayer1().getName() : session.getPlayer2().getName()) + ": " + session.getRound() / 2 + " Rounds\n" +
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
                // Create PieChartView and Presenter
                PieChartView pieChartView = new PieChartView();
                PieChartPresenter pieChartPresenter = new PieChartPresenter(session, pieChartView);

                // Update view with move data
                pieChartPresenter.updateView(session.getDatabase().getGameId());

                // Show PieChartView in a new window
                Scene scene = new Scene(pieChartView);
                Stage pieChartStage = new Stage();
                pieChartStage.setScene(scene);
                pieChartStage.setTitle("Move Duration per Player");
                pieChartStage.show();
                ((Stage)view.getScene().getWindow()).close();
            } else if (buttonType == exitButton) {
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

    public void reset(){//used to reset all variable
        session = new GameSession();
        view = new MultiPlayerView();
    }


}

