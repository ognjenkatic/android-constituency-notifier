package org.ccomp.data.database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.AppSettingsPropertyDAO;
import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;

import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMappingDAO;
import org.ccomp.data.database.dao.mapping.TranslationDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.lang.Translation;
import org.ccomp.data.domain.lang.Word;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.domain.feed.FeedItemCategory;

@TypeConverters({TimestampConverter.class, FeedImportanceConverter.class, URLConverter.class,EmailReportingConverters.class, AppSettingsConverters.class})
@Database(entities = {Feed.class, FeedItem.class, FeedCategory.class, FeedItemCategory.class, EmailReporting.class, IncidentCategory.class, EmailReportingIncidentCategoryMapping.class, Language.class, Word.class, Translation.class, AppSettingsProperty.class}, version = 24,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FeedDAO feedDAO();
    public abstract FeedItemDAO feedItemDAO();
    public abstract FeedCategoryDAO feedCategoryDAO();
    public abstract FeedItemCategoryDAO feedItemCategoryDAO();

    public abstract EmailReportingDAO emailReportingDAO();
    public abstract IncidentCategoryDAO incidentCategoryDAO();
    public abstract EmailReportingIncidentCategoryMappingDAO emailReportingIncidentCategoryMappingDAO();

    public abstract LangDAO langDAO();
    public abstract WordDAO wordDAO();
    public abstract TranslationDAO translationDAO();

    public abstract AppSettingsPropertyDAO appSettingsPropertyDAO();

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
        emailReportingDAO().deleteAll();
        incidentCategoryDAO().deleteAll();
        langDAO().deleteAll();
        wordDAO().deleteAll();
        appSettingsPropertyDAO().deleteAll();
    }

    
}
