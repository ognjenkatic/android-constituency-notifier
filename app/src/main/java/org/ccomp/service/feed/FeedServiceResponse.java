package org.ccomp.service.feed;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItem;

import java.util.List;

public class FeedServiceResponse {

    public List<FeedItem> getFeedItemList() {
        return feedItemList;
    }

    public void setFeedItemList(List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
    }

    private List<FeedItem> feedItemList;

}
