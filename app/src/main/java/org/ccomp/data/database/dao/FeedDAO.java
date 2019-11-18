package org.ccomp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.ccomp.data.domain.feed.Feed;

@Dao
public interface FeedDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Feed... feeds);

    @Update
    public void update(Feed... feeds);

    @Delete
    public void delete(Feed... feeds);

    @Query("SELECT * FROM feed")
    public Feed[] selectAll();

    @Query("DELETE FROM feed")
    public void deleteAll();

    @Query("SELECT * FROM feed WHERE id = :id")
    public Feed selectAllById(int id);
}
