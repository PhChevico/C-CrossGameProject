package be.kdg.ccross.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final int sizeRows_board = 9;
    private final int sizeCols_board = 14;
    private Square[][] board = new Square[sizeCols_board][sizeRows_board];
    private Zone[] zones;
    private List<Square> squaresOfTheBoard;
    public Board() {
        squaresOfTheBoard = new ArrayList<>();
        for (int i = 0; i < sizeCols_board; i++) {
            for (int j = 0; j < sizeRows_board; j++) {
                board[i][j] = new Square(i, j);; // Initialize Square objects here
            }
        }

        zones = new Zone[]{
                //that's how we define the zones in the board
                new Zone('A', List.of(board[0][4], board[0][3], board[1][3], board[2][3], board[2][4])),
                new Zone('B', List.of(board[1][4], board[1][5], board[2][5], board[3][5], board[3][4])),
                new Zone('C', List.of(board[1][7], board[1][6], board[2][6], board[3][6], board[3][7])),
                new Zone('D', List.of(board[2][7], board[2][8], board[3][8], board[4][8], board[4][7])),
                new Zone('E', List.of(board[3][3], board[3][2], board[4][2], board[5][2], board[5][3])),
                new Zone('F', List.of(board[4][3], board[4][4], board[5][4], board[6][4], board[6][3])),
                new Zone('G', List.of(board[4][6], board[4][5], board[5][5], board[6][5], board[6][6])),
                new Zone('H', List.of(board[5][6], board[5][7], board[6][7], board[7][7], board[7][6])),
                new Zone('I', List.of(board[6][2], board[6][1], board[7][1], board[8][1], board[8][2])),
                new Zone('J', List.of(board[7][2], board[7][3], board[8][3], board[9][3], board[9][2])),
                new Zone('K', List.of(board[7][5], board[7][4], board[8][4], board[9][4], board[9][5])),
                new Zone('L', List.of(board[8][5], board[8][6], board[9][6], board[10][6], board[10][5])),
                new Zone('M', List.of(board[9][1], board[9][0], board[10][0], board[11][0], board[11][1])),
                new Zone('N', List.of(board[10][1], board[10][2], board[11][2], board[12][2], board[12][1])),
                new Zone('O', List.of(board[10][4], board[10][3], board[11][3], board[12][3], board[12][4])),
                new Zone('P', List.of(board[11][4], board[11][5], board[12][5], board[13][5], board[13][4]))

        };
    }


    public Square getSquare(String coordinates){
        String[] parts = coordinates.split("-");
        return board [Integer.parseInt(parts[0])] [Integer.parseInt(parts[1])];
    }

    public Zone getZone(char selectedZone){

        int charAsInt = (int) selectedZone;

        if(charAsInt < 65 || charAsInt > 80)
            return null;

        charAsInt -= 65;

        return zones[charAsInt];
    }



    public List<String> getSquaresAsList(){

        // Get all squares
        List<Square> squares = getAllSquaresFromZones();


        // Initialize the ArrayList
        List<String> result = new ArrayList<>();

        // Check if the size of the squares list is compatible with the array dimensions
            try{
                for (int i = 0; i < sizeRows_board; i++) {
                    for (int j = 0; j < sizeCols_board; j++) {
                        int index = i * sizeCols_board + j;
                        Square square = squares.get(index);
                        result.add(square.toString());
                    }
                }
            } catch (Exception e){
                System.out.println("error: " + e.getMessage());
            }


        return result;
    }



    private List<Square> getAllSquaresFromZones(){
        List<Square> squares = new ArrayList<>();

        for (Zone zone : zones)
            squares.addAll(zone.getSquareOfZone());

        return squares;
    }


    //find out which zone this square is.
    public char getSquareZone(String coordinates) {
        // Iterate over each zone
        for (Zone zone : zones) {
            // Check if the coordinates belong to the current zone
            for (Square square : zone.getSquareOfZone()) {
                if (square.getCoordinates().equals(coordinates)) {
                    return zone.getZoneID();
                }
            }
        }
        // Return space if the square doesn't belong to any zone
        return ' ';
    }

    //to the continued


}
