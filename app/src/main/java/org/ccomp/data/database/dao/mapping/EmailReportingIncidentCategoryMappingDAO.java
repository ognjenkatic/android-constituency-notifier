package org.ccomp.data.database.dao.mapping;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmailReportingIncidentCategoryMappingDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmailReportingIncidentCategoryMapping mapping);

    @Delete
    void delete(EmailReportingIncidentCategoryMapping mapping);


    @Query("SELECT incident_category_id FROM email_reporting_incident_category_mapping WHERE email_address=:key")
    public abstract LiveData<List<String>> getIncidentyCategoryKeysByEmailAddress(String key);


    @Query("SELECT incident_category_id FROM email_reporting_incident_category_mapping WHERE email_address=:key")
    public abstract List<String> getIncidentyCategoryKeysByEmailAddressSync(String key);


    @Query("SELECT email_address FROM email_reporting_incident_category_mapping WHERE incident_category_id=:key")
    public abstract LiveData<List<String>> getEmailAddressKeysByIncidentCategoryId(String key);


    @Query("SELECT email_address FROM email_reporting_incident_category_mapping WHERE incident_category_id=:key")
    public abstract List<String> getEmailAddressKeysByIncidentCategoryIdSync(String key);


}
