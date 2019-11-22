package org.ccomp.data.database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;


@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class,EmailReportingConverters.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class, EmailReporting.class, IncidentCategory.class, EmailReportingIncidentCategoryMapping.class}, version = 7,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDAO feedDAO();
    public abstract FeedItemDAO feedItemDAO();

    public abstract EmailReportingDAO emailReportingDAO();
    public abstract IncidentCategoryDAO incidentCategoryDAO();
    public abstract MappingDAO mappingDAO();

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
