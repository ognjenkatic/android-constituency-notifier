package org.ccomp.data.database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.domain.feed.Feed;

<<<<<<< Updated upstream
@TypeConverters({TimestampConverter.class})
@Database(entities = {Feed.class}, version = 1,exportSchema = false)
=======
@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class}, version = 3,exportSchema = false)
>>>>>>> Stashed changes
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDAO feedDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
