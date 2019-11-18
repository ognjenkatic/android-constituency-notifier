package org.ccomp.data.database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedItem;


@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class}, version = 6,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDAO feedDAO();
    public abstract FeedItemDAO feedItemDAO();

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

        feedDAO().deleteAll();
        feedItemDAO().deleteAll();
    }
}
