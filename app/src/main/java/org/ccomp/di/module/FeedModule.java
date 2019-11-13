package org.ccomp.di.module;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.service.feed.FeedParserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedModule {

    @Provides
    @Singleton
    FeedParserService provideFeedParserService(){
        return new FeedParserService();
    }
}
