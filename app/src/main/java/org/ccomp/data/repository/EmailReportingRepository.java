package org.ccomp.data.repository;

import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMappingDAO;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class EmailReportingRepository extends GenericRepository<EmailReporting,String> {



    IncidentCategoryDAO incidentCategoryDAO;
    EmailReportingIncidentCategoryMappingDAO mappingDAO;


    @Inject
    public EmailReportingRepository(EmailReportingDAO emailReportingDAO, IncidentCategoryDAO incidentCategoryDAO, EmailReportingIncidentCategoryMappingDAO mappingDAO, ExecutorService executorService){
        this.mainDAO=emailReportingDAO;
        this.incidentCategoryDAO=incidentCategoryDAO;
        this.mappingDAO=mappingDAO;
        this.executorService=executorService;

        boolean b=true;
    }

    @Override
    public EmailReporting build(EmailReporting in) {
        List<String> keys=mappingDAO.getIncidentyCategoryKeysByEmailAddressSync(in.getAddress());
        in.setIncidentCategories(incidentCategoryDAO.getAllSync(keys));
        return in;
    }

    @Override
    public void dismantle(EmailReporting obj) {
        executorService.execute(()->{
            mainDAO.save(obj);
            if(obj.getIncidentCategories()!=null){
                for(IncidentCategory category:obj.getIncidentCategories()){
                    EmailReportingIncidentCategoryMapping mapping=new EmailReportingIncidentCategoryMapping(obj.getAddress(),category.getId());
                    incidentCategoryDAO.save(category);
                    mappingDAO.insert(mapping);
                }
            }
        });


    }

    @Override
    public void saveCallResults(@NotNull List<EmailReporting> items) {
        save(true,items.toArray(new EmailReporting[items.size()]));
    }



}
