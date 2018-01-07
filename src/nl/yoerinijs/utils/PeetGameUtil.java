package nl.yoerinijs.utils;

/**
 * Created by Yoeri on 7-1-2018.
 */
public class PeetGameUtil {

    public static <T> T nullChecked(T o) {
        if(o == null) throw new IllegalStateException("Expecting that object is not null!");
        return o;
    }
}
