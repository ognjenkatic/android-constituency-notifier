package org.ccomp.data.domain.feed;

import org.ccomp.data.domain.lang.Language;

import java.util.List;

public class FeedSettings {

    private String link;
    private Language lang;
    private FeedType feedType;
    private String version;
    private List<FeedCategory> categories;




    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<FeedCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FeedCategory> categories) {
        this.categories = categories;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
