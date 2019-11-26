package org.ccomp.data.database.dao;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.settings.lang.Word;

import java.util.List;

@Dao
public abstract class WordDAO implements IDAO<Word, Pair<String,String>>{

    @Override
    @Query("DELETE FROM lang_words")
    public abstract void deleteAll();

    @Override
    @Query("SELECT * FROM lang_words")
    public abstract LiveData<List<Word>> getAll();

    @Override
    @Query("SELECT * FROM lang_words WHERE value in (:keys)")
    public abstract LiveData<List<Word>> getAll(List<Pair<String,String>>keys);

    @Override
    @Query("SELECT * FROM lang_words WHERE value=:key")
    public abstract LiveData<Word> get(Pair<String,String> key);

    @Query("SELECT lang_id,value FROM lang_words where lang_id=:key")
    public abstract List<Pair<String,String>> getWordKeysByLangId(String key);



    @Override
    @Query("SELECT * FROM lang_words")
    public abstract List<Word> getAllSync();

    @Override
    @Query("SELECT * FROM lang_words WHERE value in (:keys)")
    public abstract List<Word> getAllSync(List<Pair<String,String>> keys);

    @Override
    @Query("SELECT * FROM lang_words WHERE value=:key")
    public abstract  Word getSync(Pair<String ,String > key);

    @Query("SELECT lang_id,value FROM lang_words where lang_id=:key")
    public abstract List<Pair<String,String>> getWordKeysByLangIdSync(String key);
}
