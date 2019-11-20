package org.ccomp.data.domain.feed;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity(tableName = "feed_item",indices = {@Index("id")})
public class FeedItem {


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private Timestamp published;
    private String link;
    private String author;


    @Ignore
    private List<FeedCategory> categories;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<FeedCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FeedCategory> categories) {
        this.categories = categories;
    }
}