package be.kdg.ccross.model;

public class EndGame {
    public int Checkpawns(Player p1,Player p2){
        if(p1.getPawnNumber()==0){
            return 1;
        } else if(p2.getPawnNumber()==0){
            return 2;
        }else return 0;
    }
}
