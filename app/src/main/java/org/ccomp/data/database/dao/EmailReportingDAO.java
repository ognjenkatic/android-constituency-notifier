package org.ccomp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class EmailReportingDAO implements IDAO<EmailReporting,String> {

    private IncidentCategoryDAO incidentCategoryDAO;
    private MappingDAO mappingDAO;



    public EmailReportingDAO(){

    }

    public EmailReportingDAO(IncidentCategoryDAO incidentCategoryDAO,MappingDAO mappingDAO) {
        this.incidentCategoryDAO = incidentCategoryDAO;
        this.mappingDAO=mappingDAO;
    }

    @Query("SELECT * FROM email_reporting_settings")
    public abstract List<EmailReporting> getAll();

    @Query("DELETE FROM email_reporting_settings")
    public abstract void deleteAll();

    @Query("SELECT * FROM email_reporting_settings WHERE address=:key")
    public abstract EmailReporting get(String key);

    @Query("SELECT address FROM email_reporting_settings")
    public abstract List<String> getKeys();


    @Query("SELECT incident_category_id from email_reporting_incident_category_mapping WHERE email_address=:key")
    public abstract List<String> getCategoriesKeys(String key);



    @Override
    public void save(EmailReporting emailReporting){
        super(emailReporting);


    }


    EmailReporting getWithCategories(String address){
        EmailReporting reporting=get(address);
        reporting.setIncidentCategories(getCategories(address));
        return reporting;
    }

    public List<IncidentCategory> getCategories(String key){
        List<IncidentCategory> categories=new ArrayList<>();
        List<String> keys=getCategoriesKeys();
        for(String categoryKey:keys ){
            IncidentCategory category=incidentCategoryDAO.get(categoryKey);
            if(category!=null){
                categories.add(category);
            }
        }

        return categories;
    }

    public void saveCategorines( EmailReporting emailReporting){
        for(IncidentCategory category:emailReporting.getIncidentCategories()){
            mappingDAO.insert(new EmailReportingIncidentCategoryMapping(emailReporting.getAddress(),category.getId()));
        }
    }

    public IncidentCategoryDAO getIncidentCategoryDAO() {
        return incidentCategoryDAO;
    }

    public void setIncidentCategoryDAO(IncidentCategoryDAO incidentCategoryDAO) {
        this.incidentCategoryDAO = incidentCategoryDAO;
    }

    public MappingDAO getMappingDAO() {
        return mappingDAO;
    }

    public void setMappingDAO(MappingDAO mappingDAO) {
        this.mappingDAO = mappingDAO;
    }

}
