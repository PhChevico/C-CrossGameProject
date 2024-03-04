package be.kdg.ccross.model;

public class Square {
    private boolean status;
    private Player ownership;
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Player getOwnership() {
        return ownership;
    }

    public void setOwnership(Player ownership) {
        this.ownership = ownership;
    }
}
