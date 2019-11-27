package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedItem;

import java.util.List;

@Dao
public interface FeedItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insert(FeedItem... feedItems);

    @Update
    public void update(FeedItem... feedItems);

    @Delete
    public void delete(FeedItem... feedItems);

    @Query("SELECT * FROM feed_item")
    public LiveData<List<FeedItem>> selectAll();

    @Query("DELETE FROM feed_item")
    public void deleteAll();

    @Query("SELECT * FROM feed_item WHERE id = :id LIMIT 1")
    public LiveData<FeedItem> selectById(int id);

    @Query("SELECT DISTINCT feed_item.id,feed_item.title,feed_item.description,feed_item.published, feed_item.link, feed_item.author, feed_item.is_read FROM feed_item JOIN feed_item_category ON feed_item.id = feed_item_category.feed_item_id JOIN feed_category ON feed_item_category.category_id = feed_category.id WHERE feed_category.feedCategoryImportance >= :importance")
    public LiveData<List<FeedItem>> selectAllByCategoryName(FeedCategoryImportance importance);

    @Query("SELECT COUNT(id) FROM feed_item WHERE title = :title")
    public int selectCountByTitle(String title);

}
