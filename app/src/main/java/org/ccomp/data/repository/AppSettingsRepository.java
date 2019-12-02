package org.ccomp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.Resource;
import org.ccomp.service.IService;
import org.ccomp.service.appsettings.AppSettingService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

import javax.inject.Inject;

public class AppSettingsRepository extends GenericRepository<AppSettings,String> {


    FeedRepository feedRepository;
    EmailReportingRepo emailReportingRepository;
    LanguageRepository languageRepository;
    AppSettingsPropertyRepository appSettingsPropertyRepository;


    @Inject
    public AppSettingsRepository(EmailReportingRepo emailReportingRepository, LanguageRepository languageRepository, AppSettingsPropertyRepository appSettingsPropertyRepository, AppSettingService mainService, ExecutorService executorService) {

        this.emailReportingRepository = emailReportingRepository;
        this.languageRepository = languageRepository;
        this.appSettingsPropertyRepository = appSettingsPropertyRepository;
        this.mainService=mainService;
        this.executorService=executorService;
    }

    @Override
    public LiveData<List<AppSettings>> getAll(Predicate<AppSettings> predicate) {
        MediatorLiveData<List<AppSettings>> all=new MediatorLiveData<>();
        List<AppSettings> list=new ArrayList<>();
        all.setValue(list);
        all.addSource(buildLiveData(),(value)->{
            list.add(value);
            all.postValue(list);
        });

        return all;

    }



    @Override
    public LiveData<AppSettings> get(String key) {
        return buildLiveData();
    }





    @Override
    public void save(boolean complexSave, @NotNull AppSettings... args) {
        if(args!=null){
            for(AppSettings obj : args){
                dismantle(obj);
            }
        }
    }

    public LiveData<AppSettings> buildLiveData(){
        AppSettings appSettings=new AppSettings();
        MediatorLiveData<AppSettings> liveData=new MediatorLiveData<>();
        liveData.setValue(appSettings);
        executorService.execute(()->{
            LiveData<List<EmailReporting>> emailReportingLiveData=emailReportingRepository.getAll();
            LiveData<List<Language>> langLiveData=languageRepository.getAll();
            LiveData<List<AppSettingsProperty>> propertiesLiveData=appSettingsPropertyRepository.getAll();
            liveData.addSource(emailReportingLiveData,(value)->{
                AppSettings newAppSettings=liveData.getValue();
                newAppSettings.setEmailReportings(value);
                liveData.postValue(newAppSettings);
            });

            liveData.addSource(langLiveData,(value)->{
                AppSettings newAppSettings=liveData.getValue();
                newAppSettings.setSupportedLangs(value);
                liveData.postValue(newAppSettings);
            });

            liveData.addSource(propertiesLiveData,(value)->{
                AppSettings newAppSettings=liveData.getValue();
                newAppSettings.setProperties(value);
                liveData.postValue(newAppSettings);
            });


        });


        return liveData;
    }

    @Override
    public AppSettings build(AppSettings in) {
        in.setEmailReportings(emailReportingRepository.getAll().getValue());
        in.setSupportedLangs(languageRepository.getAll().getValue());
        in.setProperties(appSettingsPropertyRepository.getAll().getValue());
        return in;
    }

    @Override
    public void dismantle(AppSettings obj) {
        Collection<AppSettingsProperty> properties=obj.getProperties().values();
        appSettingsPropertyRepository.save(properties.toArray(new AppSettingsProperty[properties.size()]));
    }

    @Override
    public void saveCallResults(@NotNull List<AppSettings> items) {

    }


    public FeedRepository getFeedRepository() {
        return feedRepository;
    }

    public void setFeedRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public EmailReportingRepo getEmailReportingRepository() {
        return emailReportingRepository;
    }

    public void setEmailReportingRepository(EmailReportingRepo emailReportingRepository) {
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
