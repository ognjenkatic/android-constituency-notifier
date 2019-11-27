package org.ccomp.ui.feed.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.FeedCategoryDAO;
import org.ccomp.data.database.dao.FeedItemCategoryDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.repository.FeedRepository;
import org.ccomp.service.feed.FeedParserService;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class FeedItemViewModel extends ViewModel {

    private FeedRepository feedRepository;

    private MediatorLiveData<FeedItem> feedItem = new MediatorLiveData<>();

    @Inject
    public FeedItemViewModel(FeedItemDAO feedItemDAO, FeedItemCategoryDAO feedItemCategoryDAO, FeedCategoryDAO feedCategoryDAO, FeedParserService feedParserService, ExecutorService executorService) {

        feedRepository =  new FeedRepository(feedItemDAO, feedParserService, executorService, feedItemCategoryDAO, feedCategoryDAO);

    }

    public LiveData<FeedItem> getFeedItem(){
        return feedItem;
    }

    public void setFeedItem(int id){

        LiveData<FeedItem> feedItemLiveData = feedRepository.loadFeedItem(id);

        feedItem.addSource(feedItemLiveData, (newData)->{


            if (newData.isRead() == false) {
                newData.setRead(true);
                feedRepository.updateFeedItem(newData);
            }

            feedItem.setValue(newData);

        });
    }
}
