package org.ccomp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;

@Dao
public interface FeedCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FeedCategory... feedCategories);

    @Update
    public void update(FeedCategory... feedCategories);

    @Delete
    public void delete(FeedCategory... feedCategories);

    @Query("SELECT * FROM feed_category")
    public FeedCategory[] selectAll();

    @Query("DELETE FROM feed_category")
    public void deleteAll();

    @Query("SELECT * FROM feed_category WHERE id = :id")
    public FeedCategory selectAllById(int id);
}
