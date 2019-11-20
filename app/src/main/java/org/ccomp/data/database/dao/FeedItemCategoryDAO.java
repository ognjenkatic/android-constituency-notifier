package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedItemCategory;

import java.util.List;

@Dao
public interface FeedItemCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FeedItemCategory... feedItemCategories);

    @Update
    public void update(FeedItemCategory... feedItemCategories);

    @Delete
    public void delete(FeedItemCategory... feedItemCategories);

    @Query("SELECT * FROM feed_item_category")
    public LiveData<List<FeedItemCategory>> selectAll();

    @Query("DELETE FROM feed_item_category")
    public void deleteAll();

    @Query("SELECT * FROM feed_item_category WHERE feed_item_id = :id LIMIT 1")
    public FeedItemCategory selectByFeedItemId(int id);

    @Query("SELECT * FROM feed_item_category WHERE category_id = :id LIMIT 1")
    public FeedItemCategory selectByCategoryId(int id);

}
