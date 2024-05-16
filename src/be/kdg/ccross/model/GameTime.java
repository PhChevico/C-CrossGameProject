package be.kdg.ccross.model;

import java.sql.Timestamp;

public class GameTime {
    private long startTime;
    private long endTime;
    private Timestamp timeStart;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        if (endTime == 0) {
            System.out.println("Time not stopped yet.");
            return 0;
        }
        return endTime - startTime;
    }

    public Timestamp getStartTime() {
        return new Timestamp(startTime);
    }


    public Timestamp getEndTime() {
        return new Timestamp(endTime);
    }
}


