package org.ccomp.data.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.feed.FeedParserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class FeedRepository {

    private static final String TAG = "FeedRepository";

    private FeedItemDAO feedItemDAO;
    private FeedParserService feedParserService;
    private ExecutorService executorService;

    @Inject
    public FeedRepository(FeedItemDAO feedItemDAO, FeedParserService feedParserService, ExecutorService executorService) {
        this.feedItemDAO = feedItemDAO;
        this.feedParserService = feedParserService;
        this.executorService = executorService;
    }



    public LiveData<Resource<List<FeedItem>>> loadFeedItems(String feedURL){

        return new NetworkBoundResource<List<FeedItem>, List<FeedItem>>() {

            @Override
            protected void saveCallResult(@NonNull List<FeedItem> items) {

                executorService.execute(()->{

                    if (items != null)
                        for(FeedItem item: items) {
                            if (feedItemDAO.selectCountByTitle(item.getTitle()) == 0)
                                feedItemDAO.insert(item);
                        }
                });



            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<FeedItem>> loadFromDb() {
                return feedItemDAO.selectAll();
            }

            @NonNull
            @Override
            protected Resource<LiveData<List<FeedItem>>> createCall() {
                try {

                    MutableLiveData<List<FeedItem>> ld = new MutableLiveData<>();

                    executorService.execute(()->{
                        try {
                            List<FeedItem> feedItems = feedParserService.parseStream(new URL(feedURL));
                            ld.postValue(feedItems);
                        } catch (MalformedURLException e) {
                            Log.d(TAG, "createCall: "+e.getMessage());
                            e.printStackTrace();
                        }
                    });

                    return Resource.success(ld);

                } catch(Exception mex){

                    Log.d(TAG, "createCall: "+mex.getMessage());
                    return null;
                }

            }


        }.getAsLiveData();
    }





}
