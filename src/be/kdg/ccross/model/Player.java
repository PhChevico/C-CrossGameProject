package be.kdg.ccross.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Move move = new Move();
    private LocalDateTime lastAccessTime;


    private String name;
    private int nOfWins; //get from database
    private final int MAX_NUMB_PAWNS = 12; //each player has a total of 12 pawns
    private int pawnNumber = MAX_NUMB_PAWNS; //current player's pawn number

    private List<Square> dominatedSquare;

    public Player() {
        name = new String();
        dominatedSquare = new ArrayList<>();
    }

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
    public Move getMove() {
        return move;
    }
    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void updateLastAccessTime() {
        this.lastAccessTime = LocalDateTime.now();
    }


}