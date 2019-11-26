package org.ccomp.di.module;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.TLP;
import org.ccomp.data.domain.settings.lang.Language;
import org.ccomp.data.domain.settings.lang.Word;
import org.ccomp.data.repository.EmailReportingRepo;
import org.ccomp.data.repository.EmailReportingRepository;
import org.ccomp.service.NetworkAvailabilityService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public EmailReportingRepository provideEmailReportingRepository(@NotNull AppDatabase appDatabase, @NotNull ExecutorService executorService, @NotNull NetworkAvailabilityService networkAvailabilityService){
        EmailReportingRepository emailReportingRepository=new EmailReportingRepository(appDatabase.emailReportingDAO(),appDatabase.incidentCategoryDAO(),appDatabase.emailReportingIncidentCategoryMappingDAO(),executorService,networkAvailabilityService);

        EmailReporting reporting=new EmailReporting();
        reporting.setAddress("cert@certrs.org");
        reporting.setDefaultTLP(TLP.AMBER);
        reporting.setPgpId("0xEE03B7F3");
        reporting.setPgpFingerprint("6F1F 1A61 EBA9 50A7 E3FC 5C03 1B5A 3A3B EE03 B7F3");
        reporting.setPgpKey("Potpis");
        List<IncidentCategory> categories=new ArrayList<>();
        for(int i=0;i<5;i++){
            IncidentCategory category=new IncidentCategory();
            category.setId("ID"+i);
            category.setClassDescription("ClassDescription");
            category.setClassName("Class");
            category.setDescription("Description");
            category.setTypeDescription("TypeDescription");
            category.setTypeName("TypeName");
            categories.add(category);

        }
        reporting.setIncidentCategories(categories);

       emailReportingRepository.save(reporting);


        return emailReportingRepository;
    }

    @Provides
    @Singleton
    public EmailReportingRepo provideEmailReportingRepo(@NotNull AppDatabase appDatabase, @NotNull ExecutorService executorService, @NotNull NetworkAvailabilityService networkAvailabilityService){

      



        return new EmailReportingRepo(appDatabase.emailReportingDAO(),appDatabase.incidentCategoryDAO(),appDatabase.emailReportingIncidentCategoryMappingDAO(),executorService);
    }
}
