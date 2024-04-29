package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

public class RuleGoodMove extends Rule{
    public RuleGoodMove() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        // Test code - to be removed!
        System.out.println("Condition RuleGoodMove executed");
        return false;
    }

    @Override
    public boolean actionRule(FactsHandler facts, Board board, Move move) {
        // Test code - to be removed!
        System.out.println("Action RuleGoodMove executed");
        // Code to be added is this rule could initiate a new fact

        //board.determineGoodMove(move);
        return true;     // returns true if the new move was determined, returns false if only the facts have been modified
    }
}
