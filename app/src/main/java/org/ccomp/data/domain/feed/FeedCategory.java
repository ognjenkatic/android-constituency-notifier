package org.ccomp.data.domain.feed;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.net.URL;

@Entity(tableName = "feed_category",indices = {@Index("id")})
public class FeedCategory {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
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