package co.poynt.events.dao;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


import co.poynt.events.model.EventInfo;

public class EventInfoConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static EventInfo toEventInfo(String value) {

        Type eventType = new TypeToken<String>() {
        }.getType();

        return new Gson().fromJson(value, eventType);
    }

    @TypeConverter
    public static String toString(EventInfo value) {
        return gson.toJson(value);
    }
}
