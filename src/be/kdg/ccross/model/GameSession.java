package be.kdg.ccross.model;//In this implementation we will use hard-code to show how the actual game logic will look like since
//we didn't start implementing javaFX

import be.kdg.ccross.model.rulebasedsystem.InferenceEngine;

import java.util.ArrayList;
import java.util.List;
;
public class GameSession {
    private List<Zone> winningPath;
    private Authentication authentication;
    private Board board;
    private Database database;
    private Player player1;
    private Player player2;
    private Player playerAI;
    private GameTime gameTime;
    private EndGame endGame;
    private String lastMove;
    private int counter = 0;
    private int round = 0;
    private InferenceEngine engine;
    private Move move;

    public GameSession() {
        authentication = new Authentication();
        board = new Board();
        database = new Database();
        player1 = new Player();
        player1.setName(database.getLastAccessedPlayer());
        player2 = new Player();
        playerAI = new Player();
        gameTime = new GameTime();
        endGame = new EndGame();
        engine = new InferenceEngine();
        move = new Move();
    }

    public List<String> getSquaresAsList() {
        return board.getSquaresAsList();
    }

    //Rules for putting pawns in the board. Almost everything working, just trying to reset the setLastMove after 2 moves.
    public boolean validMove(String coordinates) {
        // If it's the first move, return true
        if (getLastMove() == null) {
            return true;
        } else {
            // If the move is in the same zone, return false
            if (getBoard().getSquareZone(getLastMove()) == getBoard().getSquareZone(coordinates)) {
                return false;
            }

            // If the move is not around the first move, return false
            List<String> aroundSquares = aroundSquares(getLastMove());
            String[] parts = coordinates.split("-");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            if (!aroundSquares.contains(x + "-" + y)) {
                return false;
            }
        }

        // Check if the square at the specified coordinates is not already occupied
        return !getBoard().getSquare(coordinates).isStatus();
    }

    public List<String> aroundSquares(String mainSquare) {
        List<String> aroundSquares = new ArrayList<>();
        String[] parts = mainSquare.split("-");

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        // Add adjacent squares`s coordinates
        int xRight = x + 1;
        int xLeft = x - 1;
        int up = y - 1;
        int down = y + 1;
        aroundSquares.add(xRight + "-" + y);
        aroundSquares.add(xLeft + "-" + y);
        aroundSquares.add(x + "-" + up);
        aroundSquares.add(x + "-" + down);

        return aroundSquares;
    }
    public void dominateZones() {//check if player owns at least 3 squares in the same zone
        for (int i = 65; i <= 80; i++) {//loop through all the coordinates
            List<Square> checkingSquares = board.getZone((char) i).getSquareOfZone();

            int countp1 = 0;
            int countp2 = 0;
            for (Square c : checkingSquares) {
                if (c.isStatus()) {
                    if (c.getOwnership() == getPlayer1()) {
                        countp1 += 1;
                    } else if (c.getOwnership() == getPlayer2()) {
                        countp2 += 1;
                    }
                }
            }
            if (!(board.getZone((char) i).getOwner() == getPlayer1() || board.getZone((char) i).getOwner() == getPlayer2())) {
                if (countp1 == 3) {
                    board.getZone((char) i).setOwner(getPlayer1());
                    getPlayer1().addPawnNumber(3);
                    System.out.println("Player1");
                    switch (countp2) {
                        case 1:
                            if (getPlayer2().getPawnNumber() <= 11) {
                                getPlayer2().addPawnNumber(1);
                            }
                            break;
                        case 2:
                            if (getPlayer2().getPawnNumber() <= 10) {
                                getPlayer2().addPawnNumber(2);
                            }
                            break;
                    }
                } else if (countp2 == 3) {
                    board.getZone((char) i).setOwner(getPlayer2());
                    getPlayer2().addPawnNumber(3);
                    System.out.println("Player2");
                    switch (countp1) {
                        case 1:
                            if (getPlayer1().getPawnNumber() < 12) {
                                getPlayer1().addPawnNumber(1);
                            }
                            break;
                        case 2:
                            if (getPlayer1().getPawnNumber() < 11) {
                                getPlayer1().addPawnNumber(2);
                            }
                            break;
                    }
                }
            }
        }
        ;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public String getUsername() {
        return player1.getName();
    }

    public void setUsername(String username) {
        player1.setName(username);
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return playerAI;
    }

    public Board getBoard() {
        return board;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public String getLastMove() {
        return lastMove;
    }


    public int getCounter() {
        return counter;
    }

    public void updateCounter(int i) {
        this.counter = counter + i;
    }

    public EndGame getEndGame() {
        return endGame;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public InferenceEngine getEngine() {
        return engine;
    }

    public Move getMove() {
        return move;
    }

    public List<Zone> getWinningPath() {
        return winningPath;
    }

    public void setWinningPath(List<Zone> winningPath) {
        this.winningPath = winningPath;
    }

    public Database getDatabase() {
        return database;
    }


}




