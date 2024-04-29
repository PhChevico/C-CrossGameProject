package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

public abstract class Rule {
    public abstract boolean conditionRule(FactsHandler facts);
    public abstract boolean  actionRule(FactsHandler facts, Board board, Move move);  // returns true if the new move was determined, returns false if only the facts have been modified
}

