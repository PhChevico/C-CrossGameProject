package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.CONTINUE_PATH;

public class RuleContinuePath extends Rule{
    private int i =0;
    private int j= 0;
    public RuleContinuePath() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        return facts.factAvailable(CONTINUE_PATH);
    }
    @Override
    public boolean actionRule(GameSession session, Move move) {
        System.out.println("action on CONTINUE");

        int i = getI();
        int j = getJ();

        // Ensure i is within the bounds of session.getWinningPath()
        i = Math.min(i, session.getWinningPath().size() - 1);
        i = Math.max(i, 0);

        // Ensure j is within the bounds of session.getWinningPath().get(i).getSquareOfZone()
        if (i >= 0 && i < session.getWinningPath().size()) {
            j = Math.min(j, session.getWinningPath().get(i).getSquareOfZone().size() - 1);
            j = Math.max(j, 0);
        }

        while (true) {
            // Check if the current winning path has the AI player as its owner
            if (session.getWinningPath().get(i).getOwner() == session.getPlayer2()) {
                // Move to the next winning path if available, otherwise go to the first one
                i = (i + 1) % session.getWinningPath().size();
            }

            // Check if the current square of the zone is in a certain status
            if (session.getWinningPath().get(i).getSquareOfZone().get(j).isStatus()) {
                // Move to the next square of the zone if available, otherwise go to the first one
                j = (j + 1) % session.getWinningPath().get(i).getSquareOfZone().size();
            } else {
                // If conditions are met, break out of the loop
                break;
            }
        }

        // Update the values of i and j after the loop terminates
        setI(i);
        setJ(j);

        move.setCoordinates(session.getWinningPath().get(i).getSquareOfZone().get(j).getCoordinates());
        move.setContinuePathMove(true);

        return true; // returns true if the new move was determined, returns false if only the facts have been modified
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