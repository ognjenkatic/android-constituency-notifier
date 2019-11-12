package org.ccomp.data.domain.feed;

import java.util.Date;
import java.util.List;

public class FeedItem {


    private String title;
    private String description;
    private Date published;
    private String link;
    private String author;
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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
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
