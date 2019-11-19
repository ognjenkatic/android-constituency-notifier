package org.ccomp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.repository.EmailReportingRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    EmailReportingRepository repository;
    private LiveData<List<EmailReporting>> all;


    /*
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

     */

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        repository=new EmailReportingRepository(application);
        setAll(repository.getAll());

    }



    public LiveData<String> getText() {
        return mText;
    }
    public void setAll(LiveData<List<EmailReporting>> all){
        this.all=all;
    }

    public LiveData<List<EmailReporting>> getAll() {
        return all;
    }


}