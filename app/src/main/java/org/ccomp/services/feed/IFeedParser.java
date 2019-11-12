package org.ccomp.services.feed;

import org.ccomp.data.domain.feed.FeedItem;

import java.net.URL;
import java.util.List;

public interface IFeedParser<T> {

    List<FeedItem> parseStream(URL feedURL);

    FeedItem convertToFeedItem(T object);
}
