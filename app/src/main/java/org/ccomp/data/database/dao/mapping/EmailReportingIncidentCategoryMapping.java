package org.ccomp.data.database.dao.mapping;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "email_reporting_incident_category_mapping",primaryKeys = {"email_address","incident_category_id"})
public class EmailReportingIncidentCategoryMapping {

    @ColumnInfo(name = "email_address")
    @NotNull
    private
    String emailAddress;
    @ColumnInfo(name="incident_category_id")
    @NotNull
    private
    String incidentCategoryId;


    public EmailReportingIncidentCategoryMapping(String emailAddress, String incidentCategoryId) {
        this.emailAddress = emailAddress;
        this.incidentCategoryId = incidentCategoryId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIncidentCategoryId() {
        return incidentCategoryId;
    }

    public void setIncidentCategoryId(String incidentCategoryId) {
        this.incidentCategoryId = incidentCategoryId;
    }
}
