package be.kdg.ccross.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EndGame {
    WinningZones winningZones = new WinningZones();


    public int Checkpawns(Player p1,Player p2){
        if(p1.getPawnNumber()==0){
            return 1;
        } else if(p2.getPawnNumber()==0){
            return 2;
        }else return 0;
    }
    public boolean CheckZones(Player p,Board board){
        System.out.println("Checking who won");

        boolean playerWon = false;
        for(List<Zone> listOfZone : winningZones.getWinningZones(board)){
            if(board.dominatedZonesAsList(p).containsAll(listOfZone)){
                System.out.println("Game won");
                playerWon = true;
            };
        }
        return playerWon;
    }
}

