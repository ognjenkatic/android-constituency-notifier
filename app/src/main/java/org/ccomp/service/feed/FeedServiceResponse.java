package org.ccomp.service.feed;

import org.ccomp.data.domain.feed.Feed;

public class FeedServiceResponse {
    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    private Feed feed;

}
