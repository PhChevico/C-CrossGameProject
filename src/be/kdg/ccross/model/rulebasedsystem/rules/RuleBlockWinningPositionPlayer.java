package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

public class RuleBlockWinningPositionPlayer extends Rule{
    public RuleBlockWinningPositionPlayer() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        // Test code - to be removed!
        System.out.println("Condition RuleBlockWinningPositionPlayer executed");
        return facts.factAvailable(FactValues.WINNINGPOSITIONPLAYER);
    }
    @Override
    public boolean actionRule(FactsHandler facts, Board board, Move move) {
        // Test code - to be removed!
        System.out.println("Action RuleBlockWinningPositionPlayer executed");
        // Code to be added is this rule could initiate a new fact
        //board.determineBlockWinningPositionMove(move);
        return true;     // returns true if the new move was determined, returns false if only the facts have been modified
    }
}
