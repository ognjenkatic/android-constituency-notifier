package org.ccomp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.AppSettingsRepository;
import org.ccomp.data.repository.EmailReportingRepository;
import org.ccomp.data.repository.LanguageRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private LiveData<Resource<List<EmailReporting>>> allEmails;
    private LiveData<Resource<List<Language>>> allLang;
    private LiveData<AppSettings> appSettings;
    private LiveData<List<AppSettingsProperty>> appSettingsPropertyLiveData;

    EmailReportingRepository emailsRepository;
    LanguageRepository languageRepository;
    AppSettingsRepository appSettingsRepository;


    private static final String TAG = "HomeViewModel";

    @Inject
    public HomeViewModel(AppSettingsRepository appSettingsRepository) {
        // super(application);

        try {
            AppSettingsXMLParser xmlParser = new AppSettingsXMLParser();
            xmlParser.parse("");
        } catch (Exception ex) {
            Log.e(TAG, "HomeViewModel: ", ex);
        }

        setmText(new MutableLiveData<>());
        getmText().setValue("This is home fragment");
        this.appSettingsRepository = appSettingsRepository;
        MediatorLiveData<AppSettings> mediatorAppSettings = new MediatorLiveData<>();
        LiveData<Resource<List<AppSettings>>> listLiveData = appSettingsRepository.load(true, appSettingsRepository.getDefaultPredicate());
        mediatorAppSettings.addSource(listLiveData, (value) -> {
            List<AppSettings> allSettings = value.data;
            if (allSettings != null && !allSettings.isEmpty()) {
                AppSettings appSettings = allSettings.get(0);
                mediatorAppSettings.setValue(appSettings);
            }
        });
        this.appSettings = mediatorAppSettings;


        this.appSettingsPropertyLiveData = appSettingsRepository.getAppSettingsPropertyRepository().getAll();
        this.emailsRepository = appSettingsRepository.getEmailReportingRepository();
        this.setAllEmails(emailsRepository.load(false, emailsRepository.getDefaultPredicate()));
        this.languageRepository = appSettingsRepository.getLanguageRepository();
        this.setAllLang(languageRepository.load(false, languageRepository.getDefaultPredicate()));
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

    public LiveData<Resource<List<EmailReporting>>> getAllEmails() {
        return allEmails;
    }

    public void setAllEmails(LiveData<Resource<List<EmailReporting>>> allEmails) {
        this.allEmails = allEmails;
    }

    public LiveData<Resource<List<Language>>> getAllLang() {
        return allLang;
    }

    public void setAllLang(LiveData<Resource<List<Language>>> allLang) {
        this.allLang = allLang;
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

    public LiveData<AppSettings> getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(LiveData<AppSettings> appSettings) {
        this.appSettings = appSettings;
    }

    public AppSettingsRepository getAppSettingsRepository() {
        return appSettingsRepository;
    }

    public void setAppSettingsRepository(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }
}