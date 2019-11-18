package org.ccomp.di.module;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.room.Room;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.database.dao.FeedDAO;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@Named("db name") String databaseName, Context context){
        return Room.databaseBuilder(context, AppDatabase.class, databaseName)
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
}
