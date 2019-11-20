package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface IDAO<T, K>  {

    @Insert
    void insert(T... objects);

    @Update
    void update(T... objects);

    @Delete
    void delete(T... objects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(T... objects);

    @Query("")
    LiveData<List<T>> getAll();

    @Query("")
    void deleteAll();

    @Query("")
    LiveData<T> get(K key);

    @Query("")
    LiveData<List<K>> getKeys();


}
