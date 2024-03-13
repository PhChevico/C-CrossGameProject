package be.kdg.ccross.model;//In this implementation we will use hard-code to show how the actual game logic will look like since
//we didn't start implementing javaFX
//import java.awt.font.GlyphMetrics;

import java.util.List;

public class GameSession {
    private String time;
    Authentication authentication = new Authentication();
    Board board = new Board();
    Database database = new Database();
    Player player = new Player();
    Screen screen = new Screen();
    Pawn pawn = new Pawn();
    GameTime gameTime = new GameTime();
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

    public boolean registerUser(String userName, String password){
        try {
            //authentication.registerUser(userName, password);
            return true;
        } catch (Exception e){
            System.out.println("Not possible to register: " + e.getMessage());
            return false;
        }
    }


    public void HandleClickBoard(){

    }

    // Z1 Z1 Z1
    // Z1 Z2 Z1


}
