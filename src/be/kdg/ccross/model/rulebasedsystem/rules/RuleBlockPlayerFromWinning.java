package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import java.util.Random;

import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.BLOCK_PLAYER_FROM_WINNING;

public class RuleBlockPlayerFromWinning extends Rule{
    public RuleBlockPlayerFromWinning() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        // Test code - to be removed!
        return (facts.factAvailable(BLOCK_PLAYER_FROM_WINNING));
    }
    @Override
    public boolean actionRule(GameSession session, Move move) {

        return true;    // returns true if the new move was determined, returns false if only the facts have been modified
    }
}
