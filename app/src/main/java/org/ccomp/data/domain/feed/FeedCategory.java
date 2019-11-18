package org.ccomp.data.domain.feed;


import java.net.URL;

public class FeedCategory {

    private String name;
    private FeedCategoryImportance feedCategoryImportance;
    private URL teaxonomyUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public URL getTeaxonomyUrl() {
        return teaxonomyUrl;
    }

    public void setTeaxonomyUrl(URL teaxonomyUrl) {
        this.teaxonomyUrl = teaxonomyUrl;
    }

    public FeedCategoryImportance getFeedCategoryImportance() {
        return feedCategoryImportance;
    }

    public void setFeedCategoryImportance(FeedCategoryImportance feedCategoryImportance) {
        this.feedCategoryImportance = feedCategoryImportance;
    }
}
