package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.BLOCK_PLAYER_FROM_WINNING;
import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.CONTINUE_PATH;

public class RuleContinuePath extends Rule{
    private int i =0;
    private int j= 1;
    public RuleContinuePath() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        return facts.factAvailable(CONTINUE_PATH);
    }
    @Override
    public boolean actionRule(GameSession session,Move move) {
        System.out.println("action on CONTINUE");
        if (session.getWinningPath().get(getI()).getOwner()== session.getPlayerAI()){
            setI(getI()+1);
            setJ(0);
        }
        while((session.getWinningPath().get(getI()).getSquareOfZone().get(getJ()).isStatus())){
            if(getJ()<5){
                setI(getI()+1);
                setJ(0);
            }else{
            setJ(getJ()+1);}
        }
        move.setCoordinates(session.getWinningPath().get(getI()).getSquareOfZone().get(getJ()).getCoordinates());
        System.out.println(session.getWinningPath().get(j).getSquareOfZone().get(i).getCoordinates());
        move.setContinuePathMove(true);
        setJ(getJ()+1);
        if (getJ()>4){
            setI(getI()+1);
            setJ(0);

        }
        return true;// returns true if the new move was determined, returns false if only the facts have been modified
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}