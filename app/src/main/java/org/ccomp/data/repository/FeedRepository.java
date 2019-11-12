package org.ccomp.data.repository;

import androidx.annotation.NonNull;
import io.reactivex.Observable;


import org.ccomp.data.database.dao.FeedDAO;
import org.ccomp.data.domain.Feed;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.feed.FeedService;
import org.ccomp.service.feed.FeedServiceResponse;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;


@Singleton
public class FeedRepository {

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
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<Feed>> loadFromDb() {
                List<Feed> feedEntries = Arrays.asList(feedDAO.selectAll());
                if(feedEntries == null || feedEntries.isEmpty()) {
                    return Flowable.empty();
                }

                return Flowable.just(feedEntries);
            }

            @NonNull
            @Override
            protected Observable<Resource<FeedServiceResponse>> createCall() {
                return feedService.fetchFeeds()
                        .flatMap(movieApiResponse -> Observable.just((movieApiResponse == null)
                                ? Resource.error("", new FeedServiceResponse())
                                : Resource.success(movieApiResponse)));
            }
        }.getAsObservable();
    }
}
