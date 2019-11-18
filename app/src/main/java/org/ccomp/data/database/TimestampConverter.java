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
        if (timestamp == null)
            return 1000000L;
        return timestamp.getTime();
    }
}
