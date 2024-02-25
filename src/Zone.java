import java.util.List;

public class Zone {

    //I don't think we need "limitSquares"
    //private int limitSquares;
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
}
