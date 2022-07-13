package Utils;

public class Stopwatch {
    private long startMs;
    private long endMs;
    private long lastRecordMs;

    public Stopwatch() {

    }

    public long start(){
        startMs = System.currentTimeMillis();
        lastRecordMs = startMs;

        return startMs;
    }

    public long getElapsedSinceStart() {
        long now = System.currentTimeMillis();

        return now - startMs;
    }

    public long record(){
        lastRecordMs = System.currentTimeMillis();

        return lastRecordMs;
    }

    public long getElapsedSinceLastRecord(){
        long now = System.currentTimeMillis();

        return now - lastRecordMs;
    }

    public long Stop(){
        endMs = System.currentTimeMillis();

        return endMs - startMs;
    }
}
