package Utils;

import java.util.HashMap;
import java.util.Map;

public class Stopwatch {
    private long startMs;
    private long endMs;
    private long lastRecordMs;
    private Map<String, Long> cumulativeElapsedTimeMs = new HashMap<>();

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

    public void addCumulativeElapsedTimeMs(String name, long ms){
        if(cumulativeElapsedTimeMs.containsKey(name)){
            cumulativeElapsedTimeMs.put(name, cumulativeElapsedTimeMs.get(name) + ms);
        } else {
            cumulativeElapsedTimeMs.put(name, ms);
        }
    }

    public long Stop(){
        endMs = System.currentTimeMillis();

        return endMs - startMs;
    }
}
