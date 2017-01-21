package etiennedesticourt.cravenkings.Combat.Engine.Core.Utils;

import android.util.Log;

import java.util.ArrayList;

public class ThreadProfiler {
    private final String DEBUG_TAG = "THREAD_PROFILER";
    private final int numValues = 15;
    private ArrayList<Long> times;
    private long iterationStart;
    private long lastLog = System.nanoTime();
    private String threadId;

    public ThreadProfiler(String threadId) {
        this.threadId = threadId;
        this.times = new ArrayList<>();
    }

    public void setIterationStart() {
        iterationStart = System.nanoTime();
    }

    public void setIterationStop(){
        long current = System.nanoTime();
        long elapsed = current - iterationStart;
        times.add(elapsed);

        int elapsedSeconds = (int) ((current - lastLog) / 1000000000);

        if (elapsedSeconds > 10) {
            long average = calcAverage() / 1000;
            Log.d(DEBUG_TAG, "Iteration time for thread " + threadId + ": " + String.valueOf(average));
            lastLog = current;
        }
    }

    public long calcAverage() {
        if (times.size() > numValues) {
            long average;
            long total = 0;
            for (int i=times.size() - numValues - 1; i < times.size(); i++) {
                total += times.get(i);
            }
            average = total / numValues;
            return average;
        }
        return 0;
    }
}
