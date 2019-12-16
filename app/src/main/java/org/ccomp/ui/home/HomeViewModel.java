package org.ccomp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.AppSettingsRepository;
import org.ccomp.data.repository.EmailReportingRepository;
import org.ccomp.data.repository.LanguageRepository;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private LiveData<Resource<AppSettings>> appSettings;


    EmailReportingRepository emailsRepository;
    LanguageRepository languageRepository;
    AppSettingsRepository appSettingsRepository;


    private static final String TAG = "HomeViewModel";

    @Inject
    public HomeViewModel(AppSettingsRepository appSettingsRepository) {


        mText=new MutableLiveData<>();
        mText.setValue("This is home fragment");
        this.appSettingsRepository = appSettingsRepository;
        this.appSettings = appSettingsRepository.load(false);



        boolean b = true;

    }


    public LiveData<String> getText() {
        return getmText();
    }


    public void save(EmailReporting emailReporting) {
        getEmailsRepository().save(true, emailReporting);
    }

    public void save(Language lang) {
        getLanguageRepository().save(true, lang);
    }


    public MutableLiveData<String> getmText() {
        return mText;
    }

    public void setmText(MutableLiveData<String> mText) {
        this.mText = mText;
    }



    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public EmailReportingRepository getEmailsRepository() {
        return emailsRepository;
    }

    public void setEmailsRepository(EmailReportingRepository emailsRepository) {
        this.emailsRepository = emailsRepository;
    }

    public LiveData<Resource<AppSettings>> getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(LiveData<Resource<AppSettings>> appSettings) {
        this.appSettings = appSettings;
    }

    public AppSettingsRepository getAppSettingsRepository() {
        return appSettingsRepository;
    }

    public void setAppSettingsRepository(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }
}