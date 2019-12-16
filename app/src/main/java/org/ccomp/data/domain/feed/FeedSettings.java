package org.ccomp.data.domain.feed;

import java.util.List;

public class FeedSettings {

    private String link;
    private String lang;

    private List<FeedCategory> categories;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<FeedCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FeedCategory> categories) {
        this.categories = categories;
    }
}
