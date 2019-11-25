package org.ccomp.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class EmailReportingDAO implements IDAO<EmailReporting,String>{


    public EmailReportingDAO(){

    }


    @Query("DELETE FROM email_reporting_settings")
    public abstract void deleteAll();

    @Query("SELECT * FROM email_reporting_settings WHERE address=:key")
    public abstract LiveData<EmailReporting> get(String key);

    @Query("SELECT address FROM email_reporting_settings")
    public abstract LiveData<List<String>> getKeys();


    @Query("SELECT incident_category_id from email_reporting_incident_category_mapping WHERE email_address=:key")
    public abstract LiveData<List<String>> getCategoriesKeys(String key);

    @Query("SELECT * FROM email_reporting_settings")
    public abstract LiveData<List<EmailReporting>> getAll();

    @Query("SELECT * FROM email_reporting_settings WHERE address in (:keys)")
    public abstract LiveData<List<EmailReporting>> getAll(List<String> keys);




    @Query("SELECT * FROM email_reporting_settings WHERE address=:key")
    public abstract EmailReporting getSync(String key);

    @Query("SELECT address FROM email_reporting_settings")
    public abstract List<String> getKeysSync();


    @Query("SELECT incident_category_id from email_reporting_incident_category_mapping WHERE email_address=:key")
    public abstract List<String> getCategoriesKeysSync(String key);

    @Query("SELECT * FROM email_reporting_settings")
    public abstract List<EmailReporting> getAllSync();

    @Query("SELECT * FROM email_reporting_settings WHERE address in (:keys)")
    public abstract List<EmailReporting> getAllSync(List<String> keys);




}
