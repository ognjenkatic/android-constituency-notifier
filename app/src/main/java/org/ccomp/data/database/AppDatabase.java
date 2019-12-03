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
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.feed.FeedItemCategory;


@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class, FeedItemCategory.class}, version = 20,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDAO feedDAO();
    public abstract FeedItemDAO feedItemDAO();
    public abstract FeedCategoryDAO feedCategoryDAO();
    public abstract FeedItemCategoryDAO feedItemCategoryDAO();

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

        feedItemCategoryDAO().deleteAll();
        feedItemDAO().deleteAll();
        feedCategoryDAO().deleteAll();
        feedDAO().deleteAll();

    }

    
}
