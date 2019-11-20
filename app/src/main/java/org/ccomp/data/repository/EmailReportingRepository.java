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
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.feed.FeedItem;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.TLP;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class EmailReportingRepository {

    EmailReportingDAO emailReportingDAO;
    IncidentCategoryDAO incidentCategoryDAO;
    MappingDAO mappingDAO;
    LiveData<List<EmailReporting>> allEmailReporting;
    ExecutorService executorService;

    MediatorLiveData<List<EmailReporting>> med = new MediatorLiveData<>();

    private static final String TAG = "EmailReportingRepositor";

    public EmailReportingRepository(Application application) {

        EmailReportingDatabase database = EmailReportingDatabase.getInstance(application);
        emailReportingDAO = database.emailReportingDAO();
        incidentCategoryDAO=database.incidentCategoryDAO();
        mappingDAO=database.mappingDAO();

        //allEmailReporting = emailReportingDAO.getAll();
        allEmailReporting = getAllEmailReporting();

       med.addSource(allEmailReporting, (newData)->{
            List<EmailReporting> emailReportings=allEmailReporting.getValue();
            EmailReporting reporting=emailReportings.get(0);
            Log.d(TAG, "EmailReportingRepository: "+reporting.getPgpKey());
            med.setValue(newData);
        });





    }

    public LiveData<List<EmailReporting>> getAllEmailReporting(){
        LiveData<List<EmailReporting>> emailReportingsLiveData=emailReportingDAO.getAll();
        MediatorLiveData<List<EmailReporting>> mediatorLiveData=new MediatorLiveData<>();
        mediatorLiveData.addSource(emailReportingsLiveData,(newData)->{
            List<EmailReporting> allEmailReportings=emailReportingsLiveData.getValue();
            for(EmailReporting emailReporting:allEmailReportings){
                
            }
        });

        return null;
    }

    public LiveData<EmailReporting> get(String key){
        return null;
    }

    public void save(EmailReporting...emailReportings){

    }
    public void delete(EmailReporting... emailReportings){

    }

    public LiveData<Resource<List<EmailReporting>>> loadEmailReporting(String feedURL, boolean shouldFetch){

        return new NetworkBoundResource<List<EmailReporting>, List<EmailReporting>>() {

            @Override
            protected void saveCallResult(@NonNull List<EmailReporting> items) {

                executorService.execute(()->{

                    if (items != null)
                        save(items.toArray(new EmailReporting[items.size()]));

                });



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

                    executorService.execute(()->{
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

                } catch(Exception mex){

                    Log.d(TAG, "createCall: "+mex.getMessage());
                    return null;
                }

            }


        }.getAsLiveData();
    }



    public void insert(EmailReporting note) {
        new InsertNoteAsyncTask(emailReportingDAO).execute(note);
    }


    public LiveData<List<EmailReporting>> getAll() {
        //return allEmailReporting;
        return med;
    }

    public void save(EmailReporting emailReporting){
        new InsertNoteAsyncTask(emailReportingDAO).execute(emailReporting);


    }

    private static class InsertNoteAsyncTask extends AsyncTask<EmailReporting, Void, String> {
        private EmailReportingDAO emailReportingDAO;


        public InsertNoteAsyncTask(EmailReportingDAO emailReportingDAO) {
            this.emailReportingDAO = emailReportingDAO;
        }

        @Override
        protected String doInBackground(EmailReporting... notes) {
            emailReportingDAO.save(notes[0]);
            return null;
        }
    }







}