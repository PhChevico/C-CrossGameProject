package be.kdg.ccross.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Board {
    private final int sizeRows_board = 9;
    private final int sizeCols_board = 14;
    private Square[][] board;
    private Zone[] zones;
    private List<Square> squaresOfTheBoard;
    private boolean continuePath;
    private char zoneToDefend;



    public Board() {
        board = new Square[sizeCols_board][sizeRows_board];
        squaresOfTheBoard = new ArrayList<>();
        for (int i = 0; i < sizeCols_board; i++) {
            for (int j = 0; j < sizeRows_board; j++) {
                board[i][j] = new Square(i, j);; // Initialize Square objects here
            }
        }

        zones = new Zone[]{
                //that's how we define the zones in the board
                new Zone('A', List.of(board[0][4], board[0][3], board[1][3], board[2][3], board[2][4]),false),
                new Zone('B', List.of(board[1][4], board[3][5], board[2][5], board[1][5], board[3][4]),true),
                new Zone('C', List.of(board[1][7], board[1][6], board[2][6], board[3][6], board[3][7]),false),
                new Zone('D', List.of(board[2][7], board[4][8], board[3][8], board[2][8], board[4][7]),true),
                new Zone('E', List.of(board[3][3], board[3][2], board[4][2], board[5][2], board[5][3]),false),
                new Zone('F', List.of(board[4][3], board[6][4], board[5][4], board[4][4], board[6][3]),true),
                new Zone('G', List.of(board[4][6], board[4][5], board[5][5], board[6][5], board[6][6]),false),
                new Zone('H', List.of(board[5][6], board[7][7], board[6][7], board[5][7], board[7][6]),true),
                new Zone('I', List.of(board[6][2], board[6][1], board[7][1], board[8][1], board[8][2]),false),
                new Zone('J', List.of(board[7][2], board[9][3], board[8][3], board[7][3], board[9][2]),true),
                new Zone('K', List.of(board[7][5], board[7][4], board[8][4], board[9][4], board[9][5]),false),
                new Zone('L', List.of(board[8][5], board[10][6], board[9][6], board[8][6], board[10][5]),true),
                new Zone('M', List.of(board[9][1], board[9][0], board[10][0], board[11][0], board[11][1]),false),
                new Zone('N', List.of(board[10][1], board[12][2], board[11][2], board[10][2], board[12][1]),true),
                new Zone('O', List.of(board[10][4], board[10][3], board[11][3], board[12][3], board[12][4]),false),
                new Zone('P', List.of(board[11][4], board[13][5], board[12][5], board[11][5], board[13][4]),true)

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

    public List<Zone> dominatedZonesAsList(Player p1){//we go through all the zones and fill a list(dominatedZones) of a player by checking
        List<Zone> dominatedZones = new ArrayList<>();//whether the zone is owned by that player or not.
        for(Zone z : zones){
            if(z.getOwner()==p1){
                dominatedZones.add(z);
            }
        }
        return dominatedZones;
    }
    public boolean endStartWinningPathPossible(GameSession session){//check if we have to start on a new winning path;
        if(session.getRound()<2 || !continuePath){//we check by seeing if we are at our first round or if the player can continue on the previous created path
            continuePath = true;//if we will start a new path we set continuePath as true to let know that we can continue in our path
            return true;//and return true
        }else return false;
    }

    public boolean endContinuePath(GameSession session) {//method used to check if the player can continue the path

        for (int i = 0; i < session.getBoard().dominatedZonesAsList(session.getPlayer1()).size(); i++) {//we loop through the player dominated zones
            if (!(session.getWinningPath().contains(session.getBoard().dominatedZonesAsList(session.getPlayer1()).get(i)))) {//and check if he does not own a zone in our winning path
                continuePath = true;//if so return true
            } else {
                continuePath = false;//else we can't continue our path
                break;
            }

        }
        return continuePath;
    }

    public boolean endBlockPlayerFromWinning(GameSession session){//we block the player from winning
        boolean playerIsWinning = false;
        List<Zone> checkList = session.getBoard().dominatedZonesAsList(session.getPlayer1()); //we retrieve the zones dominated by the player
        for(int i = 65; i <= 80; i++) {//we go through all the zones
            if(!(checkList.contains(getZone((char)(i))))){//we first check if the zone is not already a dominated zone by the player
                checkList.add(getZone((char) i));//if so we add the zone to the new List
                for(List<Zone> listOfZone : session.getEndGame().getWinningListZones().getWinningZones(session.getBoard())){//go through all the possible winning combinations
                    if(checkList.containsAll(listOfZone)){//see if the list contains the combination
                        if(!(getZone((char)(i)).getOwner()== session.getPlayerAI())) {//and see if the zone is not already owned by the ai
                            setZoneToDefend((char) (i));//if so we set the zone to defend(later on this will be retrieved to continue defend
                            playerIsWinning = true;//and set is as true

                        }
                    }
                }
                checkList.remove(getZone((char) i));//at the end of the loop we delete the added zone so that we can add the next zone
            }
        }
        return playerIsWinning;//we return if we either found a zone to defend or not
    }
    public char getZoneToDefend() {
        return zoneToDefend;
    }

    public void setZoneToDefend(char zoneToDefend) {
        this.zoneToDefend = zoneToDefend;
    }
    //to the continued


}
