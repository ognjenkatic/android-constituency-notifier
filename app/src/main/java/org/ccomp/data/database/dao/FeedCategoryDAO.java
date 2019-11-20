package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedCategory;

import java.util.List;

@Dao
public interface FeedCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insert(FeedCategory... feedCategories);

    @Update
    public void update(FeedCategory... feedCategories);

    @Delete
    public void delete(FeedCategory... feedCategories);

    @Query("SELECT * FROM feed_category")
    public LiveData<List<FeedCategory>> selectAll();

    @Query("DELETE FROM feed_category")
    public void deleteAll();

    @Query("SELECT * FROM feed_category WHERE id = :id LIMIT 1")
    public LiveData<FeedCategory> selectAllById(int id);

    @Query("SELECT * FROM feed_category WHERE name = :name LIMIT 1")
    public FeedCategory selectByName(String name);

    @Query("SELECT COUNT(*) FROM feed_category WHERE name = :name")
    public int selectCountByName(String name);
}
