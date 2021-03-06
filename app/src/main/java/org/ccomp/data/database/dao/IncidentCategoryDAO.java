package org.ccomp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;

import java.util.List;

@Dao
public interface IncidentCategoryDAO extends IDAO<IncidentCategory,String> {

    @Query("SELECT * FROM incident_category")
    List<IncidentCategory> getAll();

    @Query("DELETE FROM incident_category")
    void deleteAll();

    @Query("SELECT * FROM incident_category WHERE id=:key")
    IncidentCategory get(String key);

    @Query("SELECT id FROM incident_category")
    List<String> getKeys();
}
