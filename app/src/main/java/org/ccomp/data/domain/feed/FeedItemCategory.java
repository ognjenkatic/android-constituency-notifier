package org.ccomp.data.domain.feed;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "feed_item_category", primaryKeys = { "feed_item_id", "category_id" })
public class FeedItemCategory {

    public long getFeedItemId() {
        return feedItemId;
    }

    public void setFeedItemId(long feedItemId) {
        this.feedItemId = feedItemId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @ColumnInfo(name = "feed_item_id")
    private long feedItemId;

    @ColumnInfo(name = "category_id")
    private long categoryId;
}
