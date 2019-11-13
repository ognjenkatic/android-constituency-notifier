package org.ccomp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.FeedItem;

@Dao
public interface FeedItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FeedItem... feedItems);

    @Update
    public void update(FeedItem... feedItems);

    @Delete
    public void delete(FeedItem... feedItems);

    @Query("SELECT * FROM feed_item")
    public FeedItem[] selectAll();

    @Query("DELETE FROM feed_item")
    public void deleteAll();

    @Query("SELECT * FROM feed_item WHERE id = :id")
    public FeedItem selectAllById(int id);
}
