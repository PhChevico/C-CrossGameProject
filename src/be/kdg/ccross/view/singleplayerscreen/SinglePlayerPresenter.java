package be.kdg.ccross.view.singleplayerscreen;
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


public class SinglePlayerPresenter {
    private GameSession session; //model
    private SinglePlayerView view; //view


    public SinglePlayerPresenter(GameSession session, SinglePlayerView view){
        this.session = session;
        this.view = view;
        session.getGameTime().start();
        session.getDatabase().insertNewGame(session.getDatabase().generateGameId(),session.getGameTime().getStartTime());
        session.getMove().getGameTime().start();
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
                System.out.println(entry.getKey());
                checkingActionToSquare(new Pair<>(entry.getKey(), entry.getValue()));
                mouseEvent.consume();
            });

        }
        view.getNextRound().setOnAction(actionEvent -> { //when nextRound is pressed


            session.getPlayer1().decreasePawnNumber(1);

            session.updateCounter(1);//we update the counter of pawns placed as if 2 pawns were places already even if only 1 was placed

            session.getPlayer1().addPawnNumber(1);

            session.setLastMove(null);
            session.setRound(session.getRound()+1);//we go to the other round by adding 1 to the number of rounds

            view.getNextRound().setVisible(false);//we make the nextRound button non-visible
            session.getMove().getGameTime().stop();
            session.getDatabase().storeMoveData(1,session.getPlayer1().getName(),session);
            session.getMove().getGameTime().start();
            if(!(session.getRound()%2==0)) {
                session.getEngine().determineFacts(session);
                session.getEngine().applyRules(session, session.getMove());
                aiTurn();

                session.dominateZones();
                CheckZonesClaimed();//check if player dominated the zone
                CheckPlayerWon();//check if player won

            }
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
            view.add(view.createPawn1(pair.getKey()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));//create and place the pawn on the board
            session.getBoard().getSquare(pair.getKey()).setOwnership(session.getPlayer1());//we retrieve the square with coordinate and we pass the ownership of
                // who clicked on that square

            System.out.println(session.getBoard().getSquare(pair.getKey()).isStatus());

            session.setLastMove(pair.getKey());
            session.updateCounter(1);
            view.getNextRound().setVisible(true);

            session.getPlayer1().decreasePawnNumber(1);

            if(session.getCounter() % 2 == 0){
                session.getMove().getGameTime().stop();
                session.getDatabase().storeMoveData(1,session.getPlayer1().getName(),session);
                session.getMove().getGameTime().start();

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
        if(!(session.getRound()%2==0)) {
            aiTurn();
            CheckZonesClaimed();//check if player dominated the zone
            CheckPlayerWon();//check if player won
        }

        view.getPlayer1pawns().setText("= " + String.valueOf(session.getPlayer1().getPawnNumber()));//update the label of player1 for the number of Pawns left

    }

    private void CheckZonesClaimed(){//used to check the zones claimed.We use this method each time a pawn is placed
        for (int i = 65; i <= 80; i++) {//loop through all the zones (from A to P in integer)
            List<Square> checkingSquares = session.getBoard().getZone((char)i).getSquareOfZone();//pass the list of squares of a single zone to a list
            if(session.getBoard().getZone((char)i).getOwner()==session.getPlayer1()){//if the zone is owned by player 1
                view.addZonePlayer1(checkingSquares,session.getBoard().getZone((char)i).isRotate());//add the zone visually by calling the method


            }
            if (session.getBoard().getZone((char)i).getOwner()==session.getPlayer2()){//same as previous step
                view.addZonePlayerAI(checkingSquares,session.getBoard().getZone((char)i).isRotate());


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
                reset();
                // Exit the application
                System.exit(0);
            }
        });
    }

    public void aiTurn(){
        System.out.println("AI TURN");

        session.getEngine().determineFacts(session);//determine the facts happening
        session.getEngine().applyRules(session, session.getMove());//apply the rules

        if(session.getMove().isBlockPlayerFromWinningMove()){//check if the move isBlockPlayerFromWinning is true
            String[] parts = session.getMove().getCoordinates().split("-"); //retrieve the coordinates
            session.getBoard().getSquare(session.getMove().getCoordinates()).setStatus(true);//set the status of the square as true
            view.add(view.createPawn2(session.getMove().getCoordinates()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));//add a pawn on those coordinates
            session.getBoard().getSquare(session.getMove().getCoordinates()).setOwnership(session.getPlayer2());//we set the ownership of the square by the AI
            session.getMove().setBlockPlayerFromWinningMove(false);//we reset the move
            session.setRound(session.getRound()+1);//and we go to the next round
            session.dominateZones();//we check if by this move the AI can retrieve a Zone

        }else if (session.getMove().isStartNewPathMove()) {//repeat but with a different move.
            String[] parts = session.getMove().getCoordinates().split("-");
            session.getBoard().getSquare(session.getMove().getCoordinates()).setStatus(true);
            view.add(view.createPawn2(session.getMove().getCoordinates()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            session.getBoard().getSquare(session.getMove().getCoordinates()).setOwnership(session.getPlayer2());//we do the same but for player 2
            session.getMove().setStartNewPathMove(false);
            session.setRound(session.getRound()+1);
            session.dominateZones();
        }else if(session.getMove().isContinuePathMove()){
            String[] parts = session.getMove().getCoordinates().split("-");
            session.getBoard().getSquare(session.getMove().getCoordinates()).setStatus(true);
            view.add(view.createPawn2(session.getMove().getCoordinates()), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            session.getBoard().getSquare(session.getMove().getCoordinates()).setOwnership(session.getPlayer2());//we do the same but for player 2
            session.setRound(session.getRound()+1);
            session.getMove().setContinuePathMove(false);
            session.dominateZones();

        }
        session.getPlayer2().decreasePawnNumber(1);
        view.getPlayerAIpawns().setText("= " + String.valueOf(session.getPlayer2().getPawnNumber()));//update the label of player2 for the number of Pawns left
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
    }


}
