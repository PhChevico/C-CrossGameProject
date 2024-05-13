package be.kdg.ccross.model.rulebasedsystem;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;
import be.kdg.ccross.model.rulebasedsystem.rules.RulesHandler;


public class InferenceEngine {
    private FactsHandler currentFacts;//what are the things happening (a list of things happened)
    private RulesHandler currentRules;//current rule we are following
    private boolean ruleFired; //is rule fired-->Move set on action based on that rule
    public InferenceEngine() { //initialise it at the beginning of the game
        currentFacts = new FactsHandler(); //new list of facts
        currentRules = new RulesHandler(); //new list of rules
        ruleFired = false;//rule is not fire --> no move made yet-->initialise with no ruleFired
    }
    public void determineFacts (GameSession session) {//determine what's happening on the board and derive from it the Facts happening
        currentFacts.resetFacts();//we reset the facts going on every time we call this method
        currentFacts.setFactsEvolved(false);//no new facts added to the list of what is happening
        // Determine which FactValues are currently present on the given board
    if (session.getBoard().endBlockPlayerFromWinning(session)) {//check if a possible "BLOCK_PLAYER_FROM_WINNING" situation is on
        currentFacts.addFact(FactValues.BLOCK_PLAYER_FROM_WINNING);//if so add to the current facts
    } else if (session.getBoard().endContinuePath(session)) {//check if a possible "CONTINUE_PATH" situation is on
        currentFacts.addFact(FactValues.CONTINUE_PATH);//if so add to the current facts
    } else if(session.getBoard().endStartWinningPathPossible(session)) {//check if a possible "START_NEW_WINNING_PATH" situation is on
        currentFacts.addFact(FactValues.START_NEW_WINNING_PATH);//if so add to the currentFacts(facts happening on the board) the fact value of "START..."
    }

    }
    /**
     *   rules are ordered - stops when a rule has been fired and starts re-evaluating the rules when the facts have been changed.
     */
    public void applyRules (GameSession session, Move move) {//apply rule by moving the pawn
        if (currentFacts.factsObserved()) {//if there is any Facts in current fact(so a fact happening)
            ruleFired = false;//rule not yet fired
            int i;
            do {
                currentFacts.setFactsEvolved(false);//we say facts did not evolve
                i = 0;
                while (i < currentRules.numberOfRules() && !ruleFired && !currentFacts.factsChanged()) {//as long as the Number of rules is less than i
                    //,rule is not fired and facts did not evolve
                    if (currentRules.checkConditionRule(i, currentFacts)) {//check if the rule condition matches the current facts happening by going through all the rules
                        ruleFired = currentRules.fireActionRule(i, session, move);//if they do, we make the AI move
                        //we return then to ruleFired the boolean true to ruleFired to say "okay we have applied the rule" so that we can go out of the loop
                    }
                    i++;//go to next rule to check if next rule matches the fact
                }
            } while (i < currentRules.numberOfRules() && !ruleFired);
        }
    }

}
