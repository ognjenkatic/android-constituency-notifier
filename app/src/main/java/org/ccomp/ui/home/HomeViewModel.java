package org.ccomp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.EmailReportingRepo;
import org.ccomp.data.repository.EmailReportingRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private LiveData<Resource<List<EmailReporting>>> all;

   EmailReportingRepo repository;




   @Inject
    public HomeViewModel(EmailReportingRepo repository) {
       // super(application);
        setmText(new MutableLiveData<>());
        getmText().setValue("This is home fragment");
        this.repository = repository;
        this.setAll(repository.load(false,repository.getDefaultPredicate()));


    }







    public LiveData<String> getText() {
        return getmText();
    }



    public void save(EmailReporting emailReporting){
        getRepository().save(true,emailReporting);
    }


    public MutableLiveData<String> getmText() {
        return mText;
    }

    public void setmText(MutableLiveData<String> mText) {
        this.mText = mText;
    }


    public LiveData<Resource<List<EmailReporting>>> getAll() {
        return all;
    }

    public void setAll(LiveData<Resource<List<EmailReporting>>> all) {
        this.all = all;
    }

    public EmailReportingRepo getRepository() {
        return repository;
    }

    public void setRepository(EmailReportingRepo repository) {
        this.repository = repository;
    }
}