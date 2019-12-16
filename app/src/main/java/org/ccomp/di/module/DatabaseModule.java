package org.ccomp.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.AppSettingsPropertyDAO;
import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.LangDAO;
import org.ccomp.data.database.dao.WordDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMappingDAO;
import org.ccomp.data.database.dao.mapping.TranslationDAO;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull Application application, @Named("db name") String databaseName) {
        AppDatabase build = Room.databaseBuilder(application, AppDatabase.class, databaseName)
                .fallbackToDestructiveMigration()
                .build();

        return build;
    }

    @Provides
    @Named("db name")
    String provideDatabaseName() {
        return "ccomp";
    }


    @Provides
    @Singleton
    FeedDAO provideFeedDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedDAO();
    }

    @Provides
    @Singleton
    FeedItemDAO provideFeedDAO(@NonNull AppDatabase appDatabase) {
        return appDatabase.feedItemDAO();
    }

    @Provides
    @Singleton
    public EmailReportingDAO provideEmailReportingDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.emailReportingDAO();
    }

    @Provides
    @Singleton
    public IncidentCategoryDAO provideIncidentCategoryDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.incidentCategoryDAO();
    }

    @Provides
    @Singleton
    public EmailReportingIncidentCategoryMappingDAO provideMappingDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.emailReportingIncidentCategoryMappingDAO();
    }

    @Provides
    @Singleton
    public LangDAO provideLangDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.langDAO();
    }

    @Provides
    @Singleton
    public WordDAO provideWordDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.wordDAO();
    }

    @Provides
    @Singleton
    FeedItemCategoryDAO provideFeedItemCategoryDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedItemCategoryDAO();
    }

    @Provides
    @Singleton
    public TranslationDAO provideTranslationDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.translationDAO();
    }

    @Provides
    @Singleton
    public AppSettingsPropertyDAO provideAppSettingsPropertyDAO(@NotNull AppDatabase appDatabase) {
        return appDatabase.appSettingsPropertyDAO();
    }

}
