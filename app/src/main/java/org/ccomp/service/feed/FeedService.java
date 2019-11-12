package org.ccomp.service.feed;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FeedService {

    @GET("https://certrs.org/feed/atom")
    Observable<FeedServiceResponse> fetchFeeds();
}
