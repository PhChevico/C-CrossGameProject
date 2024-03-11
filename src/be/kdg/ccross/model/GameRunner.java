package be.kdg.ccross.model;

import java.util.List;

public class GameRunner {
    public static void main(String[] args) {
        //GameSession gameSession = new GameSession();
        //gameSession.start();


        Board board = new Board();



        for(int i = 'A'; i <= 'P'; i ++) {
            List<Square> list = board.getZone((char) i).getSquareOfZone();

            for (Square square : list) {
                System.out.print(square.getRow() + " " + square.getColumn() + " ");
                //gridPane.add(img, );
            }
            System.out.println();
        }

    }

}
