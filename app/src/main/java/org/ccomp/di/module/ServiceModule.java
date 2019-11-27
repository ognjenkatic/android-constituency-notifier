package org.ccomp.di.module;

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
    FeedParserService provideFeedParserService(){
        return new FeedParserService();
    }
}
