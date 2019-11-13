package org.ccomp.data.domain.feed;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "feed_category_join", primaryKeys = {"feed_item_id","feed_category_id"}
                                        , foreignKeys = {
        @ForeignKey( entity = FeedItem.class,
                     parentColumns = "id",
                     childColumns = "feed_item_id"),
        @ForeignKey( entity = FeedCategory.class,
                     parentColumns = "id",
                     childColumns =  "feed_category_id")
})
public class FeedItemJoinFeedCategory {

    public int getFeedItemId() {
        return feedItemId;
    }

    public void setFeedItemId(int feedItemId) {
        this.feedItemId = feedItemId;
    }

    public int getFeedCategoryId() {
        return feedCategoryId;
    }

    public void setFeedCategoryId(int feedCategoryId) {
        this.feedCategoryId = feedCategoryId;
    }

    @ColumnInfo(name = "feed_item_id")
    private int feedItemId;

    @ColumnInfo(name = "feed_category_id")
    private int feedCategoryId;

}
