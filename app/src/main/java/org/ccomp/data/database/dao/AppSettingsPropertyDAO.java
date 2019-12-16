package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;

import java.util.List;

@Dao
public interface AppSettingsPropertyDAO extends IDAO<AppSettingsProperty, AppSettingsOption> {


    @Override
    @Query("DELETE FROM app_settings")
    void deleteAll();

    @Override
    @Query("SELECT * FROM app_settings")
    LiveData<List<AppSettingsProperty>> getAll();

    @Override
    @Query("SELECT * FROM app_settings WHERE option_name in (:keys)")
    LiveData<List<AppSettingsProperty>> getAll(List<AppSettingsOption> keys);

    @Override
    @Query("SELECT * FROM app_settings WHERE option_name=:key")
    LiveData<AppSettingsProperty> get(AppSettingsOption key);

    @Override
    @Query("SELECT option_name FROM app_settings")
    LiveData<List<AppSettingsOption>> getKeys();

    @Query("SELECT option_name FROM app_settings WHERE option_name like :key")
    LiveData<List<AppSettingsOption>> getKeys(AppSettingsOption key);


    @Override
    @Query("SELECT * FROM app_settings")
    List<AppSettingsProperty> getAllSync();

    @Override
    @Query("SELECT * FROM app_settings WHERE option_name in (:keys)")
    List<AppSettingsProperty> getAllSync(List<AppSettingsOption> keys);

    @Override
    @Query("SELECT * FROM app_settings WHERE option_name=:key")
    AppSettingsProperty getSync(AppSettingsOption key);

    @Override
    @Query("SELECT option_name FROM app_settings")
    List<AppSettingsOption> getKeysSync();

    @Query("SELECT option_name FROM app_settings WHERE option_name like :key")
    List<AppSettingsOption> getKeysSync(AppSettingsOption key);

}
