package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.settings.MOTD;

import java.util.List;

@Dao
public abstract class MODTDAO implements IDAO<MOTD, String> {

    @Override
    @Query("DELETE FROM motd")
    public abstract void deleteAll();

    @Override
    @Query("SELECT * FROM motd")
    public abstract LiveData<List<MOTD>> getAll();

    @Override
    @Query("SELECT * FROM motd WHERE value in (:keys)")
    public abstract LiveData<List<MOTD>> getAll(List<String> keys);

    @Override
    @Query("SELECT * FROM motd WHERE value=:key")
    public abstract LiveData<MOTD> get(String key);


    @Override
    @Query("SELECT * FROM motd")
    public abstract List<MOTD> getAllSync();

    @Override
    @Query("SELECT * FROM motd WHERE value in (:keys)")
    public abstract List<MOTD> getAllSync(List<String> keys);

    @Override
    @Query("SELECT * FROM motd WHERE value=:key")
    public abstract MOTD getSync(String key);


}
