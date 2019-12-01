package org.ccomp.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.room.Room;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
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
    FeedDAO provideFeedDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedDAO();
    }

    @Provides
    @Singleton
    FeedItemDAO provideFeedItemDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedItemDAO();
    }

    @Provides
    @Singleton
    FeedCategoryDAO provideFeedCategoryDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedCategoryDAO();
    }

    @Provides
    @Singleton
    FeedItemCategoryDAO provideFeedItemCategoryDAO(@NonNull AppDatabase appDatabase){
        return appDatabase.feedItemCategoryDAO();
    }


}
