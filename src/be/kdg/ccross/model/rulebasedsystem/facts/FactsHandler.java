package be.kdg.ccross.model.rulebasedsystem.facts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FactsHandler {

    private List<FactValues> facts = new ArrayList<>();//list of facts that happened
    private boolean factsEvolved = false;//facts changed
    public FactsHandler () {
    }
    public void addFact (FactValues fact) {
        facts.add (fact);//add a factValue to the list of facts happening --> see FactsValues class for all the possible facts happening
        factsEvolved = true;//facts happening
    }
    public void removeFact (FactValues fact) {
        facts.remove(fact);//a fact is no more happening (for example the player claimed a zone of our Winning Path and we cannot go with the fact value of WINNINGPOSITIONAI)
        factsEvolved = true;//facts changed
    }
    public boolean factsObserved () {//check if there are facts happening
        return !facts.isEmpty();
    }
    public boolean factAvailable (FactValues fact) {//see if a fact is happening
        return facts.contains(fact);
    }
    public void resetFacts() {
        Iterator<FactValues> it = facts.iterator();
        while (it.hasNext()) {
            FactValues fact = it.next(); // Get the next element before removing
            it.remove(); // Removes the current element from the underlying collection
        }
        factsEvolved = true;
    }
    public boolean factsChanged () {//did something new happen??(that what it returns)
        return factsEvolved;
    }
    public void setFactsEvolved (Boolean newValue) {//set if facts are changed or not
        factsEvolved = newValue;
    }

    @Override
    public String toString() {
        return "FactsHandler{" +
                "facts=" + facts +
                '}';
    }
}
