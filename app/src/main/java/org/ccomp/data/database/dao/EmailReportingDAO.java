package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.incident.reporting.EmailReporting;

import java.util.List;

@Dao
public abstract class EmailReportingDAO implements IDAO<EmailReporting,String>{


    public EmailReportingDAO(){

    }


    @Override
    @Query("DELETE FROM email_reporting_settings")
    public abstract void deleteAll();

    @Override
    @Query("SELECT * FROM email_reporting_settings WHERE address=:key")
    public abstract LiveData<EmailReporting> get(String key);

    @Override
    @Query("SELECT address FROM email_reporting_settings")
    public abstract LiveData<List<String>> getKeys();


    @Override
    @Query("SELECT * FROM email_reporting_settings")
    public abstract LiveData<List<EmailReporting>> getAll();

    @Override
    @Query("SELECT * FROM email_reporting_settings WHERE address in (:keys)")
    public abstract LiveData<List<EmailReporting>> getAll(List<String> keys);

    @Override
    @Query("SELECT * FROM email_reporting_settings WHERE address=:key")
    public abstract EmailReporting getSync(String key);

    @Override
    @Query("SELECT address FROM email_reporting_settings")
    public abstract List<String> getKeysSync();


    @Override
    @Query("SELECT * FROM email_reporting_settings")
    public abstract List<EmailReporting> getAllSync();

    @Override
    @Query("SELECT * FROM email_reporting_settings WHERE address in (:keys)")
    public abstract List<EmailReporting> getAllSync(List<String> keys);




}
