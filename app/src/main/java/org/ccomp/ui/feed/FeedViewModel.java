package org.ccomp.ui.feed;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FeedViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private FeedRepository feedRepository;

    @Inject
    public FeedViewModel(FeedItemDAO feedItemDAO, FeedParserService feedParserService) {

        feedRepository = new FeedRepository(feedItemDAO, feedParserService);

        Observable<Object> obs=  feedRepository.loadFeedItems();

        Log.d("tag",obs.count().toString());
    }

}
