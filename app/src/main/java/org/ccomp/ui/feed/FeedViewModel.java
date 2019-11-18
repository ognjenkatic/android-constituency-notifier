package org.ccomp.ui.feed;


import android.app.Activity;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;


import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {


    private FeedRepository feedRepository;

    public LiveData<Resource<List<FeedItem>>> getFeedItems() {
        return feedItems;
    }

    private LiveData<Resource<List<FeedItem>>> feedItems;

    @Inject
    public FeedViewModel(FeedItemDAO feedItemDAO, FeedParserService feedParserService, ExecutorService executorService) {

        feedRepository =  new FeedRepository(feedItemDAO, feedParserService, executorService);
        feedItems = feedRepository.loadFeedItems("https://certrs.org/feed/atom");
    }




}
