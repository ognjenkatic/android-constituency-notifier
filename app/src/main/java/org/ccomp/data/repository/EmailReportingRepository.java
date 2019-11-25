package org.ccomp.data.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.TypeConverter;

import org.ccomp.data.database.EmailReportingDatabase;
import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.TLP;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.NetworkAvailabilityService;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EmailReportingRepository {

    EmailReportingDAO emailReportingDAO;
    IncidentCategoryDAO incidentCategoryDAO;
    MappingDAO mappingDAO;
    ExecutorService executorService;
    NetworkAvailabilityService networkAvailabilityService;



    private static final String TAG = "EmailReportingRepository";

    @Inject
    public EmailReportingRepository(EmailReportingDAO emailReportingDAO, IncidentCategoryDAO incidentCategoryDAO,MappingDAO mappingDAO, ExecutorService executorService,NetworkAvailabilityService networkAvailabilityService) {


        this.emailReportingDAO = emailReportingDAO;
        this.incidentCategoryDAO = incidentCategoryDAO;
        this.mappingDAO=mappingDAO;
        this.executorService = executorService;
        this.networkAvailabilityService=networkAvailabilityService;


    }

    public LiveData<List<EmailReporting>> getAllEmailReporting() {

        MutableLiveData<List<EmailReporting>> emailReportingMutableLiveData=new MediatorLiveData<>();
        emailReportingMutableLiveData.setValue(emailReportingDAO.getAll().getValue());
        executorService.execute(()->{
            List<EmailReporting> all=emailReportingDAO.getAllSync();
            for(EmailReporting emailReporting: all){
                List<String> keys=emailReportingDAO.getCategoriesKeysSync(emailReporting.getAddress());
                emailReporting.setIncidentCategories(incidentCategoryDAO.getAllSync(keys));
            }
            emailReportingMutableLiveData.postValue(all);

        });


        return emailReportingMutableLiveData;
    }


    public LiveData<EmailReporting> get(String key) {
        return emailReportingDAO.get(key);

    }

    public void save(@NotNull EmailReporting... emailReportings) {
        if (emailReportings != null) {
            executorService.execute(() -> {
                for (EmailReporting emailReporting : emailReportings) {
                    emailReportingDAO.save(emailReporting);
                    if(emailReporting.getIncidentCategories()!=null) {
                        for (IncidentCategory category : emailReporting.getIncidentCategories()) {
                            EmailReportingIncidentCategoryMapping mapping = new EmailReportingIncidentCategoryMapping(emailReporting.getAddress(), category.getId());
                            incidentCategoryDAO.save(category);
                            mappingDAO.insert(mapping);
                        }
                    }
                }
            });

        }
    }

    public void delete(EmailReporting... emailReportings) {
        if (emailReportings != null) {
            executorService.execute(() -> {
                emailReportingDAO.delete(emailReportings);
            });

        }
    }

    public LiveData<Resource<List<EmailReporting>>> loadEmailReporting(String URL, boolean shouldFetch) {

        return new NetworkBoundResource<List<EmailReporting>, List<EmailReporting>>() {

            @Override
            protected void saveCallResult(@NonNull List<EmailReporting> items) {

                save(items.toArray(new EmailReporting[items.size()]));


            }

            @Override
            protected boolean shouldFetch() {
                return shouldFetch;
            }

            @NonNull
            @Override
            protected LiveData<List<EmailReporting>> loadFromDb() {
                return getAllEmailReporting();
            }

            @NonNull
            @Override
            protected Resource<LiveData<List<EmailReporting>>> createCall() {
                try {

                    MutableLiveData<List<EmailReporting>> ld = new MutableLiveData<>();

                    executorService.execute(() -> {
                        /*
                        try {
                            List<FeedItem> feedItems = feedParserService.parseStream(new URL(feedURL));
                            ld.postValue(feedItems);
                        } catch (MalformedURLException e) {
                            Log.d(TAG, "createCall: "+e.getMessage());
                            e.printStackTrace();
                        }

                         */
                    });

                    return Resource.success(ld);

                } catch (Exception mex) {

                    Log.d(TAG, "createCall: " + mex.getMessage());
                    return null;
                }

            }


        }.getAsLiveData();
    }


}