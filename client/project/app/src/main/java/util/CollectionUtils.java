package util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by bruce on 2016/1/7.
 */
public class CollectionUtils {


    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static boolean hasData(Collection collection) {
        return (collection != null && collection.size() > 0);
    }   public static boolean hasData(Object[] collection) {
        return (collection != null && collection.length > 0);
    }
    public static boolean hasData(Map map) {
        return (map != null && map.size() > 0);
    }
}
