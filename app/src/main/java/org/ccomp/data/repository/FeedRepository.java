package org.ccomp.data.repository;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.feed.FeedParserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;


@Singleton
public class FeedRepository {


    private FeedItemDAO feedItemDAO;
    private FeedParserService feedParserService;

    public FeedRepository(FeedItemDAO feedItemDAO, FeedParserService feedParserService) {
        this.feedItemDAO = feedItemDAO;
        this.feedParserService = feedParserService;
    }


    public LiveData<Resource<List<FeedItem>>> loadFeedItems(String feedURL){
        return new NetworkBoundResource<List<FeedItem>, List<FeedItem>>() {

            @Override
            protected void saveCallResult(@NonNull List<FeedItem> items) {
                ExecutorService es1= Executors.newFixedThreadPool(1);
                CountDownLatch cdl1 = new CountDownLatch(1);

                es1.execute(()->{
                    if (items != null)
                        for(FeedItem item: items) {
                            if (feedItemDAO.selectCountByTitle(item.getTitle()) == 0)
                                feedItemDAO.insert(item);
                        }
                    cdl1.countDown();
                });


                try {
                    cdl1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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

                    CreateCallAsyncTask ccat = new CreateCallAsyncTask(feedParserService);
                    List<FeedItem> feedItems = ccat.execute(feedURL).get();
                    MutableLiveData<List<FeedItem>> ld = new MutableLiveData<>();
                    ld.postValue(feedItems);
                    return Resource.success(ld);

                } catch(Exception mex){

                    String g = "s";
                    return null;
                }

            }


        }.getAsLiveData();
    }

    private static class CreateCallAsyncTask extends AsyncTask<String, Void, List<FeedItem>>{

        FeedParserService fps;
        public CreateCallAsyncTask(FeedParserService feedParserService){
            this.fps = feedParserService;
        }
        @Override
        protected List<FeedItem> doInBackground(String... strings) {
            try {
                return fps.parseStream(new URL(strings[0]));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }



}
