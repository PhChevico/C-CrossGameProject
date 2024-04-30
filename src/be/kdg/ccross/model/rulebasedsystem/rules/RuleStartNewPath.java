package be.kdg.ccross.model.rulebasedsystem.rules;

import be.kdg.ccross.model.*;
import be.kdg.ccross.model.rulebasedsystem.facts.FactValues;
import be.kdg.ccross.model.rulebasedsystem.facts.FactsHandler;

import java.util.Random;

import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.BLOCK_PLAYER_FROM_WINNING;
import static be.kdg.ccross.model.rulebasedsystem.facts.FactValues.START_NEW_WINNING_PATH;

public class RuleStartNewPath extends Rule{
    public RuleStartNewPath() {
    }

    @Override
    public boolean conditionRule(FactsHandler facts) {
        return (facts.factAvailable(START_NEW_WINNING_PATH));
    }
    @Override
    public boolean actionRule(GameSession session, Move move) {
        System.out.println("action on START NEW PATH");
        move.setStartNewPathMove(true);
        session.setWinningPath(null);
        Random r = new Random();
        boolean freeWinningPath =false;

        session.setWinningPath(session.getEndGame().getWinningListZones().getWinningZones(session.getBoard()).get(r.nextInt(34)));
        for (int i = 0; i < session.getBoard().dominatedZonesAsList(session.getPlayer1()).size(); i++) {

            if (!(session.getWinningPath().contains(session.getBoard().dominatedZonesAsList(session.getPlayer1()).get(i)))) {
                    freeWinningPath = true;
                    break;
            }else {
                session.setWinningPath(session.getEndGame().getWinningListZones().getWinningZones(session.getBoard()).get(r.nextInt(34)));
            }

        }
        move.setCoordinates(session.getWinningPath().get(0).getSquareOfZone().get(0).getCoordinates());
        return true;     // returns true if the new move was determined, returns false if only the facts have been modified
    }
}