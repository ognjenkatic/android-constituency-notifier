package org.ccomp.data.database;

import androidx.room.TypeConverter;

import org.ccomp.data.domain.feed.FeedCategoryImportance;

import java.sql.Timestamp;

public class FeedImportanceConverter {

    @TypeConverter
    public static FeedCategoryImportance toFeedCategoryImportance(int importance){
        if (importance == FeedCategoryImportance.HIDE.ordinal())
            return FeedCategoryImportance.HIDE;
        else if (importance == FeedCategoryImportance.SHOW.ordinal())
            return FeedCategoryImportance.SHOW;
        else if (importance == FeedCategoryImportance.NOTIFY.ordinal())
            return FeedCategoryImportance.NOTIFY;
        else
            return FeedCategoryImportance.UNCATEGORIZED;
    }

    @TypeConverter
    public static int toInt(FeedCategoryImportance importance){
        return importance.ordinal();
    }
}