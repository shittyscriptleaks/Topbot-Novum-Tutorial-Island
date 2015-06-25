package deob;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimeMap {

    private static Map<Integer, Long> d = new ConcurrentHashMap<>();

    public static long passed(int key) {
        if (!d.containsKey(key)) {
            return -1;
        }
        return System.currentTimeMillis() - d.get(key);
    }

    public static boolean validate(int key, long time) {
        if (!(d.containsKey(key) && System.currentTimeMillis() - d.get(key) < time)) {
            return true;
        }
        return false;
    }

    public static void update(int key, long time) {
        d.put(key, System.currentTimeMillis() + time);
    }

    public static void update(int key) {
        d.put(key, System.currentTimeMillis());
    }
}

