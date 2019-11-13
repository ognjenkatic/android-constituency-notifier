package org.ccomp.service.feed;

import android.util.Log;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedParserService implements IFeedParser<SyndEntry> {


    private SyndFeedInput syndFeedInput = new SyndFeedInput();
    private Map<String, FeedCategoryImportance> categoryImportanceMap = new HashMap<>();

    @Override
    public List<FeedItem> parseStream(URL feedURL) {

        List<FeedItem> feedItems = null;
        SyndFeed syndFeed = null;


        try {
            syndFeed = getSyndFeedInput().build(new XmlReader(feedURL));
            feedItems = new ArrayList<>();
            Iterator<SyndEntry> iterator = syndFeed.getEntries().iterator();
            while (iterator.hasNext()) {
                FeedItem feedItem = convertToFeedItem(iterator.next());
                feedItems.add(feedItem);
            }
        } catch (FeedException | IOException e) {
            Log.println(Log.ERROR, "FEED PARSING", e.getMessage());
        }
        return feedItems;
    }

    @Override
    public FeedItem convertToFeedItem(final SyndEntry syndEntry) {
        FeedItem feedItem = new FeedItem();
        feedItem.setAuthor(syndEntry.getAuthor());
        feedItem.setDescription(syndEntry.getDescription());
        feedItem.setLink(syndEntry.getLink());
        feedItem.setPublished(syndEntry.getPublishedDate());
        List<FeedCategory> feedCategories = syndEntry.getCategories().stream().map(category -> {
            FeedCategory feedCategory = new FeedCategory();
            feedCategory.setName(category.getName())
            feedCategory.setFeedCategoryImportance(getCategoryImportanceMap().getOrDefault(feedCategory.getName(), FeedCategoryImportance.UNCATEGORIZED));
            feedCategory.setTeaxonomyUrl(new URL(category.getTaxonomyUri()));
            return feedCategory;
        }).collect(Collectors.toList());
        feedItem.setCategories(feedCategories);
        return feedItem;


    }


    public SyndFeedInput getSyndFeedInput() {
        return syndFeedInput;
    }

    public void setSyndFeedInput(SyndFeedInput syndFeedInput) {
        this.syndFeedInput = syndFeedInput;
    }

    public Map<String, FeedCategoryImportance> getCategoryImportanceMap() {
        return categoryImportanceMap;
    }

    public void setCategoryImportanceMap(Map<String, FeedCategoryImportance> categoryImportanceMap) {
        this.categoryImportanceMap = categoryImportanceMap;
    }
}
