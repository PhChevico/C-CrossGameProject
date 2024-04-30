package be.kdg.ccross.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EndGame {
    WinningZones winningListZones = new WinningZones();


    public int Checkpawns(Player p1,Player p2){//used to check a players pawns number. return 1 if p1 finished the pawns
        if(p1.getPawnNumber()==0){             //return 2 if p2 finished the pawns
            return 1;
        } else if(p2.getPawnNumber()==0){
            return 2;
        }else return 0;
    }
    public boolean CheckZones(Player p,Board board){//used to check if a player owned a correct combination of zones to win
        boolean playerWon = false;
        for(List<Zone> listOfZone : winningListZones.getWinningZones(board)){//go through all the possible winning combinations
            if(board.dominatedZonesAsList(p).containsAll(listOfZone)){//see if the list contains the combination
                playerWon = true;
            };
        }
        return playerWon;
    }

    public WinningZones getWinningListZones() {
        return winningListZones;
    }
}

