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
    void deleteAll();

    @Query("")
    LiveData<List<T>> getAll();

    @Query("")
    LiveData<List<T>> getAll(List<K>keys);

    @Query("")
    LiveData<T> get(K key);

    @Query("")
    LiveData<List<K>> getKeys();

    @Query("")
    List<T> getAllSync();

    @Query("")
    List<T> getAllSync(List<K>keys);

    @Query("")
    T getSync(K key);

    @Query("")
    List<K> getKeysSync();


}