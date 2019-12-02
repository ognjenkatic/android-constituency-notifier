package org.ccomp.ui.feed.index;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class FeedIndexViewModel extends ViewModel {

    private FeedRepository feedRepository;

    public LiveData<List<Feed>> getFeeds() {
        return feeds;
    }

    private LiveData<List<Feed>> feeds;

    @Inject
    public FeedIndexViewModel(FeedDAO feedDAO, FeedItemDAO feedItemDAO, FeedItemCategoryDAO feedItemCategoryDAO, FeedCategoryDAO feedCategoryDAO, FeedParserService feedParserService, ExecutorService executorService) {

        feedRepository =  new FeedRepository(feedItemDAO, feedParserService, executorService, feedItemCategoryDAO, feedCategoryDAO, feedDAO);
        feeds = feedRepository.loadFeedsAsync();
    }

    public LiveData<Boolean> tryAddFeed(String feedUrl){

        return feedRepository.tryAddFeed(feedUrl);
    }
}
