package be.kdg.ccross.model;

import java.util.List;

public class Player {
    private String name;
    private int nOfWins; //get from database
    private final int MAX_NUMB_PAWNS = 12; //each player has a total of 12 pawns
    private int pawnNumber = MAX_NUMB_PAWNS; //current player's pawn number

    private List<Square> dominatedSquare;

    private List<Zone> dominatedZones;

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

    public void setPawnNumber(int pawnNumber) {
        this.pawnNumber = pawnNumber;
    }

    public List<Square> getDominatedSquare() {
        return dominatedSquare;
    }

    public void setDominatedSquare(List<Square> dominatedSquare) {
        this.dominatedSquare = dominatedSquare;
    }



    //methods when conquering zones...




}