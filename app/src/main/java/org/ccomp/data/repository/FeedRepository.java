package org.ccomp.data.repository;

import androidx.annotation.NonNull;
import io.reactivex.Observable;


import org.ccomp.data.database.dao.FeedItemDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.feed.FeedParserService;
import org.ccomp.service.feed.FeedServiceResponse;
import org.ccomp.task.FeedFetchTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;


@Singleton
public class FeedRepository {

    private FeedItemDAO feedItemDAO;
    //private FeedParserService feedParserService;

    public FeedRepository(FeedItemDAO feedItemDAO, FeedParserService feedParserService) {
        this.feedItemDAO = feedItemDAO;
        //this.feedParserService = feedParserService;
    }

    public Observable<Object> loadFeedItems(){
        return new NetworkBoundResource<List<FeedItem>, FeedServiceResponse>() {

            @Override
            protected void saveCallResult(@NonNull FeedServiceResponse item) {
                for(FeedItem feedItem : item.getFeedItemList()){
                    feedItemDAO.insert(feedItem);
                }

            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<FeedItem>> loadFromDb() {
                List<FeedItem> feedItems = Arrays.asList(feedItemDAO.selectAll());
                if(feedItems == null || feedItems.isEmpty()) {
                    return Flowable.empty();
                }

                return Flowable.just(feedItems);
            }

            @NonNull
            @Override
            protected Observable<Resource<FeedServiceResponse>> createCall() {
                Observable<FeedServiceResponse> fos = Observable.empty();
                try {
                    List<FeedItem> feedItems = new FeedFetchTask().execute(new URL("https://certrs.org/rss")).get();

                    FeedServiceResponse feedServiceResponse = new FeedServiceResponse();
                    feedServiceResponse.setFeedItemList(feedItems);

                    fos = Observable.fromArray(feedServiceResponse);

                } catch(MalformedURLException mex){


                } finally {
                    return fos.flatMap(feedServiceResponseInstance -> Observable.just((feedServiceResponseInstance == null)
                            ? Resource.error("", new FeedServiceResponse())
                            : Resource.success(feedServiceResponseInstance)));
                }

            }
        }.getAsObservable();
    }
}
