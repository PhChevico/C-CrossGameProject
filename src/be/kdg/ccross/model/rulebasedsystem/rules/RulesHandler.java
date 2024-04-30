package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import java.util.ArrayList;
import java.util.List;

public class RulesHandler {

    private List<Rule> rules = new ArrayList<>(); //list of rules that the AI should follow

    /**
     * Order of the rules:
     * 1. if the AI can make a move to start a new Winning Path and it doesn't already have one
     *    -> the AI makes this move.
     *
     * 2. if the AI can make a move to continue on the Winning Path
     *    -> the AI makes this move.
     *
     * 3. if the player could obtain in his/her next move a winning position
     *    -> the AI makes this move
     *
     */
    public RulesHandler (){
        rules.add (0, new RuleStartNewPath());
        rules.add (1, new RuleContinuePath());
        rules.add(2,new RuleBlockPlayerFromWinning());
    }
    public boolean checkConditionRule (int index, FactsHandler facts) {//check if the condition of the Fact is okay to see if we can use
        return rules.get(index).conditionRule(facts);
    }
    public boolean fireActionRule (int index, GameSession session, Move move) {
        return rules.get(index).actionRule(session,move);
    }
    public int numberOfRules () {
        return rules.size();
    }
}
