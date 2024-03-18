package be.kdg.ccross.model;//In this implementation we will use hard-code to show how the actual game logic will look like since
//we didn't start implementing javaFX

import java.util.ArrayList;
import java.util.List;
;
public class GameSession {
    private String time;
    private Authentication authentication = new Authentication();
    private Board board = new Board();
    private Database database = new Database();
    private Player player = new Player();
    private Screen screen = new Screen();
    private Pawn pawn = new Pawn();
    private GameTime gameTime = new GameTime();
    private String lastMove;
    private int counter = 0;


    private boolean isFinished; //boolean used in the while to check if the user exits the game
    public void start(){
        initgame(); //once the authentication part is done we start with the actual loop og the game
        while (isFinished) {
            game();//the game logic
        }
    }
    public void initgame(){
        /*if(screen.LoginScreen()==2){ //the screen.LoginScreen is just a hashcode of how the actual JavaFX screen would look like.
        authentication.registerUser();} //user enter name and password
        else{
            authentication.isLoginCorrect(); //check if the login is correct
        }*/
    }
    public void game(){
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
        isFinished= screen.askToExit(); //we set the boolean is Finished to the choice of the player(exit the game or continue in the loop)
    }

    public List<String> getSquaresAsList(){
        return board.getSquaresAsList();
    }

    public boolean registerUser(String userName, String password,String confirmPasswd){
        try {
            //authentication.registerUser(userName, password);
            return true;
        } catch (Exception e){
            System.out.println("Not possible to register: " + e.getMessage());
            return false;
        }
    }

    public Board getBoard(){
        return board;
    }

    public void setLastMove(String lastMove){
        this.lastMove = lastMove;
    }

    public String getLastMove(){
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


    public String getUsername(){
        return player.getName();
    }

    public void setUsername(String username){
        player.setName(username);
    }



}

