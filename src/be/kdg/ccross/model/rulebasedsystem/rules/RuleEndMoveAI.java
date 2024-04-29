package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

public class RuleEndMoveAI extends Rule{
    public RuleEndMoveAI() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        // Test code - to be removed!
        System.out.println("Condition RuleEndMoveAI executed");
        return !facts.factAvailable(FactValues.OTHERFACT)
                || facts.factAvailable(FactValues.ENDMOVEAI);
    }

    @Override
    public boolean actionRule(FactsHandler facts, Board board, Move move) {
        // Test code - to be removed!
        System.out.println("Action RuleEndMoveAI executed");
        // Code to be added is this rule could initiate a new fact
        // example:
        if (!facts.factAvailable(FactValues.OTHERFACT)) {
            facts.addFact(FactValues.OTHERFACT);
            return false; // returns true if the new move was determined, returns false if only the facts have been modified
        }
        //board.determineEndMove(move);
        return true;     // returns true if the new move was determined, returns false if only the facts have been modified
    }
}

