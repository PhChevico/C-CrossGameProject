import java.util.List;

public class Zone {

    private Player owner;
    private char zoneID;
    private List<Square> squareOfZone;



    //zone has and id + its owned squares.
    public Zone(char zoneId, List<Square> squareOfZone){
        this.zoneID = zoneId;
        this.squareOfZone = squareOfZone;
    }


    public Zone(Player owner, char zoneID, List<Square> squareOfZone) {
        this.owner = owner;
        this.zoneID = zoneID;
        this.squareOfZone = squareOfZone;
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







}
