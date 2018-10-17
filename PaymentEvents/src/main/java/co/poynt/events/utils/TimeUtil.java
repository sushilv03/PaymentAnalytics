package co.poynt.events.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    private static final String TAG = "TimeUtil";

    public static String formatISO8601(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public static long getCurrentTime() {

        Date date = new Date();

        long time = date.getTime();
        Log.d(TAG, "Time in Milliseconds: " + time);

        Timestamp ts = new Timestamp(time);
        Log.d(TAG,"Current Time Stamp: " + ts);

        return ts.getTime();
    }
}
