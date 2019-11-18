package org.ccomp.data.database.dao.mapping;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface MappingDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmailReportingIncidentCategoryMapping mapping);

    @Delete
    void delete(EmailReportingIncidentCategoryMapping mapping);
}
