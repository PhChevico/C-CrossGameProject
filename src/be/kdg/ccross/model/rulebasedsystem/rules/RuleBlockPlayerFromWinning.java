package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import java.util.Random;

import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.BLOCK_PLAYER_FROM_WINNING;

public class RuleBlockPlayerFromWinning extends Rule{
    private int square=0;
    public RuleBlockPlayerFromWinning() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        // Test code - to be removed!
        return (facts.factAvailable(BLOCK_PLAYER_FROM_WINNING));
    }
    @Override
    public boolean actionRule(GameSession session, Move move) {
        System.out.println("action on DEFEND");
        Zone zone = session.getBoard().getZone(session.getBoard().getZoneToDefend());
        while (session.getBoard().getZone(session.getBoard().getZoneToDefend()).getSquareOfZone().get(getSquare()).isStatus()){
            square ++;
        }
        move.setCoordinates(session.getBoard().getZone(session.getBoard().getZoneToDefend()).getSquareOfZone().get(getSquare()).getCoordinates());
        setSquare(square+1);
        move.setBlockPlayerFromWinningMove(true);
        return true;   // returns true if the new move was determined, returns false if only the facts have been modified
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
