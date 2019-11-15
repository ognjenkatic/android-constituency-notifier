package org.ccomp.ui.feed;

<<<<<<< Updated upstream
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedDAO;
=======
import android.os.AsyncTask;
import android.util.Log;
import android.view.MenuItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;
import org.ccomp.task.DelegateAsyncTask;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {

<<<<<<< Updated upstream
    public FeedViewModel(){

    }

    @Inject
    public FeedViewModel(FeedDAO feedDAO) {

    }
=======
    private FeedRepository feedRepository;

    public LiveData<Resource<List<FeedItem>>> getFeedItems() {
        return feedItems;
    }

    private LiveData<Resource<List<FeedItem>>> feedItems;

    @Inject
    public FeedViewModel(FeedItemDAO feedItemDAO, FeedParserService feedParserService) {

        feedRepository =  new FeedRepository(feedItemDAO, feedParserService);

        feedItems = feedRepository.loadFeedItems("https://certrs.org/feed/atom");
    }



>>>>>>> Stashed changes
}
