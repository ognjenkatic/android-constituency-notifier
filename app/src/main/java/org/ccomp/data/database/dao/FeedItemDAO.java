package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;
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

    @Query("SELECT * FROM feed_item WHERE id = :id")
    public LiveData<List<FeedItem>> selectAllById(int id);

    @Query("SELECT COUNT(id) FROM feed_item WHERE title = :title")
    public int selectCountByTitle(String title);

}
