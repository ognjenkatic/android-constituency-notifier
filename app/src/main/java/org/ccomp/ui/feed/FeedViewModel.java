package org.ccomp.ui.feed;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;


import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {


    private FeedRepository feedRepository;

    public LiveData<List<FeedItem>> getFeedItems(int feedId) {

        feedItems = feedRepository.loadFeedItemsByCategoryNameAndFeedId(FeedCategoryImportance.SHOW, feedId);
        return feedItems;
    }

    private LiveData<List<FeedItem>> feedItems;


    @Inject
    public FeedViewModel(FeedDAO feedDAO, FeedItemDAO feedItemDAO, FeedItemCategoryDAO feedItemCategoryDAO, FeedCategoryDAO feedCategoryDAO, FeedParserService feedParserService, ExecutorService executorService) {

        feedRepository =  new FeedRepository(feedItemDAO, feedParserService, executorService, feedItemCategoryDAO, feedCategoryDAO, feedDAO);

    }




}
