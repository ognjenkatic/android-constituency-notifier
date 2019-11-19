package org.ccomp.data.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.TypeConverter;

import org.ccomp.data.database.EmailReportingDatabase;
import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.MappingDAO;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.TLP;

import java.util.List;

public class EmailReportingRepository {

    EmailReportingDAO emailReportingDAO;
    IncidentCategoryDAO incidentCategoryDAO;
    MappingDAO mappingDAO;
    LiveData<List<EmailReporting>> allEmailReporting;

    public EmailReportingRepository(Application application) {

        EmailReportingDatabase database = EmailReportingDatabase.getInstance(application);
        emailReportingDAO = database.emailReportingDAO();
        incidentCategoryDAO=database.incidentCategoryDAO();
        mappingDAO=database.mappingDAO();

        emailReportingDAO.setIncidentCategoryDAO(incidentCategoryDAO);
        emailReportingDAO.setMappingDAO(mappingDAO);

        allEmailReporting = emailReportingDAO.getAll();
    }


    public void insert(EmailReporting note) {
        new InsertNoteAsyncTask(emailReportingDAO).execute(note);
    }


    public LiveData<List<EmailReporting>> getAll() {
        return allEmailReporting;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<EmailReporting, Void, String> {
        private EmailReportingDAO emailReportingDAO;


        public InsertNoteAsyncTask(EmailReportingDAO emailReportingDAO) {
            this.emailReportingDAO = emailReportingDAO;
        }

        @Override
        protected String doInBackground(EmailReporting... notes) {
            emailReportingDAO.insert(notes[0]);
            return null;
        }
    }



}