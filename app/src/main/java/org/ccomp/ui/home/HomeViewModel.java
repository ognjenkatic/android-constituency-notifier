package org.ccomp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.repository.EmailReportingRepository;
import org.ccomp.service.NetworkAvailabilityService;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private LiveData<List<EmailReporting>> all;

   EmailReportingRepository repository;




   @Inject
    public HomeViewModel(EmailReportingRepository repository) {
       // super(application);
        setmText(new MutableLiveData<>());
        getmText().setValue("This is home fragment");
        this.repository = repository;
        this.all=repository.getAllEmailReporting();


    }







    public LiveData<String> getText() {
        return getmText();
    }
    public void setAll(LiveData<List<EmailReporting>> all){
        this.all=all;
    }

    public LiveData<List<EmailReporting>> getAll() {
        return all;
    }

    public void save(EmailReporting emailReporting){
        getRepository().save(emailReporting);
    }


    public MutableLiveData<String> getmText() {
        return mText;
    }

    public void setmText(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public EmailReportingRepository getRepository() {
        return repository;
    }


    public void setRepository(EmailReportingRepository repository) {
        this.repository = repository;


    }
}