package be.kdg.ccross.model;//In this implementation we will use hard-code to show how the actual game logic will look like since
//we didn't start implementing javaFX

import be.kdg.ccross.model.rulebasedsystem.InferenceEngine;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
;
public class GameSession {
    private List<Zone> winningPath;
    private String time;
    private Authentication authentication = new Authentication();
    private Board board = new Board();
    private Database database = new Database();
    private Player player1 = new Player();
    private Player playerAI = new Player();
    private Screen screen = new Screen();
    private Pawn pawn = new Pawn();
    private GameTime gameTime = new GameTime();
    private EndGame endGame = new EndGame();
    private String lastMove;
    private int counter = 0;
    private int round = 0;
    InferenceEngine engine = new InferenceEngine();
    Move move = new Move();



    private boolean isFinished; //boolean used in the while to check if the user exits the game

    public void start() {
        initgame(); //once the authentication part is done we start with the actual loop og the game
        while (isFinished) {
            game();//the game logic
        }
    }

    public void initgame() {
        /*if(screen.LoginScreen()==2){ //the screen.LoginScreen is just a hashcode of how the actual JavaFX screen would look like.
        authentication.registerUser();} //user enter name and password
        else{
            authentication.isLoginCorrect(); //check if the login is correct
        }*/
    }

    public void game() {
        //display the screen with javaFX
        String color = pawn.pawnColor();//we ask the user for a pawn color(we still didn't handle the input of it since we still have to work with javaFX
        //so this is just an example of how it should work USING ENUMS
        //display the board with JavaFX;
        gameTime.start();
        //ask where the user wants to put the pawn with a method stored in ??? that we will implement once we will start working with javaFX
        //gameLogic that keeps the game going on and check everytime if somebody won with a method
        gameTime.stop();
        //store the game in the database calling a method from database manager in which we pass the player name,the time(with getElapsedTime method)
        //and if he won or lost.
        //we call this method 2 TIMES, one for the PLAYER1 and one for the PLAYER2 ;
        isFinished = screen.askToExit(); //we set the boolean is Finished to the choice of the player(exit the game or continue in the loop)
    }

    public List<String> getSquaresAsList() {
        return board.getSquaresAsList();
    }

    public boolean registerUser(String userName, String password, String confirmPasswd) {
        try {
            authentication.registerUser(userName, password);
            return true;
        } catch (Exception e) {
            System.out.println("Not possible to register: " + e.getMessage());
            return false;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public String getLastMove() {
        return lastMove;
    }


    public int getCounter() {
        return counter;
    }

    public void updateCounter(int i) {
        this.counter = counter + i;
    }

    //Rules for putting pawns in the board. Almost everything working, just trying to reset the setLastMove after 2 moves.
    public boolean validMove(String coordinates) {
        // If it's the first move, return true
        if (getLastMove() == null) {
            return true;
        } else {
            // If the move is in the same zone, return false
            if (getBoard().getSquareZone(getLastMove()) == getBoard().getSquareZone(coordinates)) {
                return false;
            }

            // If the move is not around the first move, return false
            List<String> aroundSquares = aroundSquares(getLastMove());
            String[] parts = coordinates.split("-");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            if (!aroundSquares.contains(x + "-" + y)) {
                return false;
            }
        }

        // Check if the square at the specified coordinates is not already occupied
        return !getBoard().getSquare(coordinates).isStatus();
    }

    public List<String> aroundSquares(String mainSquare) {
        List<String> aroundSquares = new ArrayList<>();
        String[] parts = mainSquare.split("-");

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        // Add adjacent squares`s coordinates
        int xRight = x + 1;
        int xLeft = x - 1;
        int up = y - 1;
        int down = y + 1;
        aroundSquares.add(xRight + "-" + y);
        aroundSquares.add(xLeft + "-" + y);
        aroundSquares.add(x + "-" + up);
        aroundSquares.add(x + "-" + down);

        return aroundSquares;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public String getUsername() {
        return player1.getName();
    }

    public void setUsername(String username) {
        player1.setName(username);
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayerAI() {
        return playerAI;
    }

    public void dominateZones() {//check if player owns at least 3 squares in the same zone
        for (int i = 65; i <= 80; i++) {//loop through all the coordinates
            List<Square> checkingSquares = board.getZone((char) i).getSquareOfZone();

            int countp1 = 0;
            int countp2 = 0;
            for (Square c : checkingSquares) {
                if (c.isStatus()) {
                    if (c.getOwnership() == getPlayer1()) {
                        countp1 += 1;
                    } else if (c.getOwnership() == getPlayerAI()) {
                        countp2 += 1;
                    }
                }
            }
            if (!(board.getZone((char) i).getOwner() == getPlayer1() || board.getZone((char) i).getOwner() == getPlayerAI())) {
                if (countp1 == 3) {
                    board.getZone((char) i).setOwner(getPlayer1());
                    getPlayer1().addPawnNumber(3);
                    System.out.println("Player1");
                    switch (countp2) {
                        case 1:
                            if (getPlayerAI().getPawnNumber() <= 11) {
                                getPlayerAI().addPawnNumber(1);
                            }
                            break;
                        case 2:
                            if (getPlayerAI().getPawnNumber() <= 10) {
                                getPlayerAI().addPawnNumber(2);
                            }
                            break;
                    }
                } else if (countp2 == 3) {
                    board.getZone((char) i).setOwner(getPlayerAI());
                    getPlayerAI().addPawnNumber(3);
                    System.out.println("Player2");
                    switch (countp1) {
                        case 1:
                            if (getPlayer1().getPawnNumber() < 12) {
                                getPlayer1().addPawnNumber(1);
                            }
                            break;
                        case 2:
                            if (getPlayer1().getPawnNumber() < 11) {
                                getPlayer1().addPawnNumber(2);
                            }
                            break;
                    }
                }
            }
        }
        ;
    }


    public EndGame getEndGame() {
        return endGame;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public InferenceEngine getEngine() {
        return engine;
    }

    public Move getMove() {
        return move;
    }

    public List<Zone> getWinningPath() {
        return winningPath;
    }

    public void setWinningPath(List<Zone> winningPath) {
        this.winningPath = winningPath;
    }
}




