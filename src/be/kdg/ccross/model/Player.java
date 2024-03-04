package be.kdg.ccross.model;

public class Player {
    private String name;
    private int pawnNumber; //current player's pawn number
    private int nOfWins; //get from database
    private final int MAX_NUMB_PAWNS = 25; //each player has a total of 25 pawns


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getPawnNumber() {
        return pawnNumber;
    }

    public void addPawnNumber(int receivedPawnNumber) {
        this.pawnNumber += receivedPawnNumber;
    }

    public void decreasePawnNumber(int insertedPawnNumber) {
        this.pawnNumber -= insertedPawnNumber;
    }


    //CHANGE IT TO A TRY AND CATCH --- you parse in the player and the square, it checks it the square is occupied
    // if not, it assigns ownership for the square and makes the squares status occupied
    public void insertPawnInCell (Player player, Square square){
        if(!square.isStatus()){
            square.setOwnership(player);
            square.setStatus(true);
        }
    }






    //methods when conquering zones...


}
