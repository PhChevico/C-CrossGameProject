package be.kdg.ccross.model;

import java.sql.Timestamp;

public class GameTime {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        if (startTime == 0) {
            System.out.println("Time not started yet.");
            return 0;
        }
        if (endTime == 0) {
            System.out.println("Time not stopped yet.");
            return 0;
        }
        return endTime - startTime;
    }

    public Timestamp getStartTime() {
        long startTimeMillis = this.startTime;
        java.sql.Timestamp moveTime = new java.sql.Timestamp(System.currentTimeMillis() - startTimeMillis);
        return moveTime;
    }
}


