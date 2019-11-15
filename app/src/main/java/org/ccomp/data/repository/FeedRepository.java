package org.ccomp.data.repository;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
<<<<<<< Updated upstream
import org.ccomp.service.feed.FeedService;
import org.ccomp.service.feed.FeedServiceResponse;

import java.util.Arrays;
=======
import org.ccomp.service.feed.FeedParserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;


@Singleton
public class FeedRepository {

<<<<<<< Updated upstream
    private FeedDAO feedDAO;
    private FeedService feedService;

    public FeedRepository(FeedDAO feedDAO,
                           FeedService feedService) {
        this.feedDAO = feedDAO;
        this.feedService = feedService;
    }

    public Observable<Object> loadFeeds(){
        return new NetworkBoundResource<List<Feed>, FeedServiceResponse>() {

            @Override
            protected void saveCallResult(@NonNull FeedServiceResponse item) {
                feedDAO.insert(item.getFeed());
=======
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

>>>>>>> Stashed changes
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
<<<<<<< Updated upstream
            protected Flowable<List<Feed>> loadFromDb() {
                List<Feed> feedEntries = Arrays.asList(feedDAO.selectAll());
                if(feedEntries == null || feedEntries.isEmpty()) {
                    return Flowable.empty();
                }

                return Flowable.just(feedEntries);
=======
            protected LiveData<List<FeedItem>> loadFromDb() {
                return feedItemDAO.selectAll();
>>>>>>> Stashed changes
            }

            @NonNull
            @Override
<<<<<<< Updated upstream
            protected Observable<Resource<FeedServiceResponse>> createCall() {
                return feedService.fetchFeeds()
                        .flatMap(movieApiResponse -> Observable.just((movieApiResponse == null)
                                ? Resource.error("", new FeedServiceResponse())
                                : Resource.success(movieApiResponse)));
=======
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
>>>>>>> Stashed changes
            }
        }
    }



}
