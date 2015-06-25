package deob.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TimeMap {

    private TimeMap() { }

    private static final Map<Integer, Long> times = new ConcurrentHashMap<>();

    public static long passed(int key) {
        if (!times.containsKey(key)) {
            return -1;
        }
        return System.currentTimeMillis() - times.get(key);
    }

    public static boolean validate(int key, long time) {
        if (!(times.containsKey(key) && System.currentTimeMillis() - times.get(key) < time)) {
            return true;
        }
        return false;
    }

    public static void update(int key, long time) {
        times.put(key, System.currentTimeMillis() + time);
    }

    public static void update(int key) {
        times.put(key, System.currentTimeMillis());
    }

}

