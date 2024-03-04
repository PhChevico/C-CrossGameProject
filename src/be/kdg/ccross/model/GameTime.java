package be.kdg.ccross.model;

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
}


