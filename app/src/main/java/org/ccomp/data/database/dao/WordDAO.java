package org.ccomp.data.database.dao;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.lang.Word;

import java.util.List;

@Dao
public abstract class WordDAO implements IDAO<Word,String>{

    @Override
    @Query("DELETE FROM words")
    public abstract void deleteAll();

    @Override
    @Query("SELECT * FROM words")
    public abstract LiveData<List<Word>> getAll();

    @Override
    @Query("SELECT * FROM words WHERE word in (:keys)")
    public abstract LiveData<List<Word>> getAll(List<String> keys);

    @Override
    @Query("SELECT * FROM words WHERE word=:key")
    public abstract LiveData<Word> get(String key);

    @Override
    @Query("SELECT word from words")
    public abstract LiveData<List<String>> getKeys();


    @Override
    @Query("SELECT * FROM words")
    public abstract List<Word> getAllSync();

    @Override
    @Query("SELECT * FROM words WHERE word in (:keys)")
    public abstract List<Word> getAllSync(List<String> keys);

    @Override
    @Query("SELECT * FROM words WHERE word=:key")
    public abstract Word getSync(String key);

    @Override
    @Query("SELECT word from words")
    public abstract List<String> getKeysSync();

}
