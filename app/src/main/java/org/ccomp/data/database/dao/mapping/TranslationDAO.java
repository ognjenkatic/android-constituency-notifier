package org.ccomp.data.database.dao.mapping;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.ccomp.data.domain.lang.Translation;

import java.util.List;


@Dao
public interface TranslationDAO   {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Translation translation);

    @Delete
    void delete(Translation translation);


    @Query("SELECT lang_id,word,value FROM language natural join translation where lang_id=:langId ")
    LiveData<List<Translation>> getTranslationByLangId(String langId);


    @Query("SELECT lang_id,word,value FROM language natural join translation where lang_id=:langId ")
    List<Translation> getTranslationByLangIdSync(String langId);


}
