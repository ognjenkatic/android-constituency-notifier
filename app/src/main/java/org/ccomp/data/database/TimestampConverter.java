package org.ccomp.data.database;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

public class TimestampConverter {

    @TypeConverter
    public static Timestamp toTimestamp(Long ticks){
        return new Timestamp(ticks);
    }

    @TypeConverter
    public static Long toLong(Timestamp timestamp){
        return timestamp.getTime();
    }
}
