package edu.petrov.benchmark;

import java.util.concurrent.TimeUnit;

/**
 * Created by anton on 6/7/16.
 */
public class TimeWatch {

    long start;

    private TimeWatch() {
        reset();
    }

    public static TimeWatch start() {
        return new TimeWatch();
    }

    public static long convert(long timeNano, TimeUnit unit) {
        return unit.convert(timeNano, TimeUnit.NANOSECONDS);
    }

    public TimeWatch reset() {
        start = System.nanoTime();
        return this;
    }

    public long time() {
        return System.nanoTime() - start;
    }

    public long time(TimeUnit unit) {
        return unit.convert(time(), TimeUnit.NANOSECONDS);
    }
}
