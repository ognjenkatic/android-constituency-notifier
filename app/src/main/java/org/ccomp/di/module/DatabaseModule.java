package org.ccomp.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.room.Room;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@NonNull Application application, @Named("db name") String databaseName){
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
    FeedItemDAO provideFeedDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedItemDAO();
    }
}
