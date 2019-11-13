package org.ccomp.data.database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;

@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedItemDAO feedItemDAO();
    public abstract FeedDAO feedDAO();
    public abstract FeedCategoryDAO feedCategoryDAO();


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
        feedItemDAO().deleteAll();
        feedDAO().deleteAll();
        feedCategoryDAO().deleteAll();
    }
}
