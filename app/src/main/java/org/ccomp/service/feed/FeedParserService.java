package org.ccomp.service.feed;

import android.util.Log;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedParserService implements IFeedParser<SyndEntry> {


    private static final String TAG = "FeedParserService";

    private SyndFeedInput syndFeedInput = new SyndFeedInput();
    private Map<String, FeedCategoryImportance> categoryImportanceMap = new HashMap<>();


    public FeedParserService(){

    }

    public FeedParserService(Map<String,FeedCategoryImportance> categoryImportanceMap){
        this.categoryImportanceMap=categoryImportanceMap;
    }

    public Feed parseStreamToFeed(URL feedURL){

        Feed feed = null;
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

            feed = new Feed();

            feed.setLink(feedURL.toString().toLowerCase());
            feed.setTitle(syndFeed.getTitle());
            feed.setSubtitle(syndFeed.getDescription());
            feed.setFeedItemList(feedItems);

        } catch (FeedException | IOException e) {
            Log.d(TAG, "parseStream: "+e.getMessage());
        }
        return feed;

    }
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
            Log.d(TAG, "parseStream: "+e.getMessage());
        }

        return feedItems;
    }

    @Override
    public FeedItem convertToFeedItem(final SyndEntry syndEntry) {

        FeedItem feedItem = new FeedItem();
        feedItem.setAuthor(syndEntry.getAuthor());
        feedItem.setDescription(syndEntry.getDescription().getValue());
        feedItem.setLink(syndEntry.getLink());

        if (syndEntry.getPublishedDate() != null)
            feedItem.setPublished(new Timestamp(syndEntry.getPublishedDate().getTime()));
        feedItem.setTitle(syndEntry.getTitle());
        List<FeedCategory> feedCategories = new ArrayList<>();
        for(SyndCategory category:syndEntry.getCategories()){
            FeedCategory feedCategory = new FeedCategory();
            feedCategory.setName(category.getName().toLowerCase());
            if(getCategoryImportanceMap().containsKey(feedCategory.getName())){
                feedCategory.setFeedCategoryImportance(getCategoryImportanceMap().get(feedCategory.getName().toLowerCase()));
            }else{
                feedCategory.setFeedCategoryImportance(FeedCategoryImportance.UNCATEGORIZED);
            }
            try{
                feedCategory.setTeaxonomyUrl(new URL(category.getTaxonomyUri()));
            }catch (MalformedURLException e){
                Log.d(TAG, "convertToFeedItem: "+e.getMessage());
            }

            feedCategories.add(feedCategory);
        }



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
