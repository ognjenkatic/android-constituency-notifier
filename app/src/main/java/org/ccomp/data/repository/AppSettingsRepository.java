package org.ccomp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.feed.FeedSettings;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.appsettings.AppSettingService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class AppSettingsRepository {

    private static final String TAG = "AppSettingsRepository";
    FeedRepository feedRepository;
    EmailReportingRepository emailReportingRepository;
    LanguageRepository languageRepository;
    AppSettingsPropertyRepository appSettingsPropertyRepository;
    AppSettingService mainService;
    ExecutorService executorService;


    @Inject
    public AppSettingsRepository(EmailReportingRepository emailReportingRepository, FeedRepository feedRepository, LanguageRepository languageRepository, AppSettingsPropertyRepository appSettingsPropertyRepository, AppSettingService mainService, ExecutorService executorService) {

        this.emailReportingRepository = emailReportingRepository;
        this.languageRepository = languageRepository;
        this.appSettingsPropertyRepository = appSettingsPropertyRepository;
        this.mainService = mainService;
        this.executorService = executorService;
        this.feedRepository = feedRepository;
    }


    public synchronized LiveData<Resource<AppSettings>> load(boolean shouldFetch) {
        return new NetworkBoundResource<AppSettings, AppSettings>() {
            @Override
            protected void saveCallResult(@NonNull AppSettings item) {
                dismantle(item);
            }

            @Override
            protected boolean shouldFetch() {
                return shouldFetch;
            }

            @NonNull
            @Override
            protected LiveData<AppSettings> loadFromDb() {

                return buildLiveDataSync();
            }

            @NonNull
            @Override
            protected Resource<LiveData<AppSettings>> createCall() {
                try {

                    MutableLiveData<AppSettings> mutableLiveData = new MutableLiveData<>();
                    if (mainService != null) {
                        executorService.execute(() -> {

                            try {
                                AppSettings item = mainService.fetch();
                                mutableLiveData.postValue(item);
                            } catch (Exception ex) {
                                Log.e(TAG, "createCall: ", ex);

                                ex.printStackTrace();
                            }


                        });
                    } else {
                        return null;
                    }

                    return Resource.success(mutableLiveData);

                } catch (Exception ex) {

                    Log.d(TAG, "createCall: " + ex.getMessage());
                    return null;
                }
            }
        }.getAsLiveData();
    }

    public LiveData<AppSettings> buildLiveDataSync() {
        MutableLiveData<AppSettings> mutableLiveData = new MutableLiveData<>();
        executorService.execute(() -> {
            AppSettings appSettings = new AppSettings();
            appSettings.setSupportedLangs(languageRepository.getAllSync());
            appSettings.setEmailReportings(emailReportingRepository.getAllSync());
            appSettings.setProperties(appSettingsPropertyRepository.getAllSync());
            if (appSettings.getProperties().containsKey(AppSettingsOption.app_settings_lang_default)) {
                AppSettingsProperty defaultLangProp = appSettings.getProperties().get(AppSettingsOption.app_settings_lang_default);
                Language defaultLang = languageRepository.getSync(defaultLangProp.getOptionValue());
                appSettings.setDefaultLang(defaultLang);
                if (defaultLang != null) {
                    appSettings.setDefaultLangString(defaultLang.getLangId());
                }
            }
            mutableLiveData.postValue(appSettings);
            appSettings = null;
        });
        return mutableLiveData;
    }

    private LiveData<AppSettings> buildLiveData() {
        AppSettings appSettings = new AppSettings();
        MediatorLiveData<AppSettings> liveData = new MediatorLiveData<>();
        liveData.setValue(appSettings);
        LiveData<List<EmailReporting>> emailReportingLiveData = emailReportingRepository.getAll();
        LiveData<List<Language>> langLiveData = languageRepository.getAll();
        LiveData<List<AppSettingsProperty>> propertiesLiveData = appSettingsPropertyRepository.getAll();
        liveData.addSource(emailReportingLiveData, (value) -> {
            AppSettings newAppSettings = liveData.getValue();
            newAppSettings.setEmailReportings(value);
            liveData.postValue(newAppSettings);
        });

        liveData.addSource(langLiveData, (value) -> {
            AppSettings newAppSettings = liveData.getValue();
            newAppSettings.setSupportedLangs(value);
            liveData.postValue(newAppSettings);
        });

        liveData.addSource(propertiesLiveData, (value) -> {
            AppSettings newAppSettings = liveData.getValue();
            newAppSettings.setProperties(value);
            if (newAppSettings.getProperties().containsKey(AppSettingsOption.app_settings_lang_default)) {
                newAppSettings.setDefaultLangString(newAppSettings.getProperties().get(AppSettingsOption.app_settings_lang_default).getOptionValue());
            }
            liveData.postValue(newAppSettings);
        });


        return liveData;
    }

    public void dismantle(AppSettings obj) {
        if (obj != null) {
            if (obj.getProperties() != null) {
                Collection<AppSettingsProperty> properties = obj.getProperties().values();
                appSettingsPropertyRepository.save(properties.toArray(new AppSettingsProperty[properties.size()]));
            }
            if (obj.getCertFeeds() != null) {
                for(FeedSettings feedSettings : obj.getCertFeeds()){
                    feedRepository.tryAddFeed(feedSettings.getLink());
                }
            }
            if (obj.getSupportedLangs() != null) {
                languageRepository.save(obj.getSupportedLangs().toArray(new Language[obj.getSupportedLangs().size()]));
            }
            if(obj.getEmailReportings()!=null){
                emailReportingRepository.save(obj.getEmailReportings().toArray(new EmailReporting[obj.getEmailReportings().size()]));
            }
        }
    }


    public FeedRepository getFeedRepository() {
        return feedRepository;
    }

    public void setFeedRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public EmailReportingRepository getEmailReportingRepository() {
        return emailReportingRepository;
    }

    public void setEmailReportingRepository(EmailReportingRepository emailReportingRepository) {
        this.emailReportingRepository = emailReportingRepository;
    }

    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public AppSettingsPropertyRepository getAppSettingsPropertyRepository() {
        return appSettingsPropertyRepository;
    }

    public void setAppSettingsPropertyRepository(AppSettingsPropertyRepository appSettingsPropertyRepository) {
        this.appSettingsPropertyRepository = appSettingsPropertyRepository;
    }
}
