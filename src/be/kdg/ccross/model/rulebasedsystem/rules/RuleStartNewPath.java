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
        boolean pathNotFound=true;
        while(pathNotFound) {
            session.setWinningPath(session.getEndGame().getWinningListZones().getWinningZones(session.getBoard()).get(r.nextInt(34)));
            boolean contains = false;
            for (int i = 0;i<session.getBoard().dominatedZonesAsList(session.getPlayer1()).size();i++) {
                if ((session.getWinningPath().contains(session.getBoard().dominatedZonesAsList(session.getPlayer1()).get(i)))) {
                    contains = true;
                }
            }
            if(!contains){
                pathNotFound = false;
            }
        }
        boolean squareNotFound=true;
        for(int j=0; j<4 && squareNotFound;j++) {
            for (int i = 0; i < 4; i++) {
                if (!(session.getWinningPath().get(j).getSquareOfZone().get(i).isStatus())) {
                    move.setCoordinates(session.getWinningPath().get(j).getSquareOfZone().get(i).getCoordinates());
                    System.out.println(session.getWinningPath().get(j).getSquareOfZone().get(i).getCoordinates());
                    squareNotFound=false;
                    break;
                }
            }
        }

        return true;     // returns true if the new move was determined, returns false if only the facts have been modified
    }
}