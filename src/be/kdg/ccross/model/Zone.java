package be.kdg.ccross.model;

import java.util.ArrayList;
import java.util.List;

public class Zone {

    private Player owner;
    private char zoneID;
    private List<Square> squareOfZone;
    private boolean rotate = false;



    //zone has and id + its owned squares.
    public Zone(char zoneId, List<Square> squareOfZone,boolean rotate){
        this.zoneID = zoneId;
        this.squareOfZone = squareOfZone;
        this.rotate= rotate;
    }

    public Zone(Player owner, char zoneID, List<Square> squareOfZone) {
        this.owner = owner;
        this.zoneID = zoneID;
        this.squareOfZone = squareOfZone;
    }


    public Player getOwner() {
        return owner;
    }

    public char getZoneID() {
        return zoneID;
    }

    public List<Square> getSquareOfZone() {
        return squareOfZone;
    }



    //receives a zone as argument and iterate through the squares checking if they're occupied or not.
    // if all squares dominated (true) return true (zoneIsDominated)
    public boolean isZoneDominated(Zone zone){
        for(Square square : squareOfZone){
            if(square.isStatus())
                return true;
        }
        return false;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

}
