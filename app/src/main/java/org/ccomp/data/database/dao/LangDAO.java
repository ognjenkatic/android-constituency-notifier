package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import org.ccomp.data.domain.settings.lang.Language;

import java.util.List;

public abstract class LangDAO implements IDAO<Language,String> {

    @Override
    @Query("DELETE FROM language")
    public abstract void deleteAll();


    @Override
    @Query("SELECT * FROM language")
    public abstract LiveData<List<Language>> getAll();

    @Override
    @Query("SELECT * FROM language WHERE lang_id in (:keys)")
    public abstract LiveData<List<Language>> getAll(List<String> keys);

    @Override
    @Query("SELECT * FROM language WHERE lang_id=:key")
    public abstract LiveData<Language> get(String key);




    @Override
    @Query("SELECT * FROM language")
    public abstract List<Language> getAllSync();

    @Override
    @Query("SELECT * FROM language WHERE lang_id in (:keys)")
    public abstract List<Language> getAllSync(List<String> keys);

    @Override
    @Query("SELECT * FROM language WHERE lang_id=:key")
    public abstract Language getSync(String key);
}
