package org.ccomp.di.module;

import android.app.Application;

import androidx.annotation.NonNull;

import org.ccomp.service.NetworkAvailabilityService;
import org.ccomp.service.feed.FeedParserService;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    ExecutorService provideExecutorService(){
        return  Executors.newFixedThreadPool(5);
    }

    @Provides
    @Singleton
    public NetworkAvailabilityService provideNetworkAvailabilityService(@NonNull Application app){
        return  new NetworkAvailabilityService(app.getApplicationContext());
    }
}
