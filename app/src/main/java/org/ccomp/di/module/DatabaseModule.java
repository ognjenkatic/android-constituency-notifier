package org.ccomp.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMappingDAO;
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
        return Room.databaseBuilder(application, AppDatabase.class, databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Named("db name")
    String provideDatabaseName() {
        return "ccomp";
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

}
