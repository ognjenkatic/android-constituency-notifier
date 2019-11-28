package org.ccomp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.Resource;
import org.ccomp.service.IService;
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
    public AppSettingsRepository(FeedRepository feedRepository, EmailReportingRepo emailReportingRepository, LanguageRepository languageRepository, AppSettingsPropertyRepository appSettingsPropertyRepository, IService<AppSettings> mainService, ExecutorService executorService) {
        this.feedRepository = feedRepository;
        this.emailReportingRepository = emailReportingRepository;
        this.languageRepository = languageRepository;
        this.appSettingsPropertyRepository = appSettingsPropertyRepository;
        this.mainService=mainService;
        this,executorService=executorService;
    }

    @Override
    public LiveData<List<AppSettings>> getAll(Predicate<AppSettings> predicate) {
        List<AppSettings> all=new ArrayList<>();
        all.add(get(null));
        return  all;
    }



    @Override
    public LiveData<AppSettings> get(String key) {
        MutableLiveData<AppSettings> mutableLiveData=new MutableLiveData<>();
        mutableLiveData.postValue(build(new AppSettings()));
        return  mutableLiveData;
    }


    @Override
    public void delete(AppSettings... args) {

    }


    @Override
    public void save(boolean complexSave, @NotNull @NotNull AppSettings... args) {
        if(args!=null){
            for(AppSettings obj : args){
                dismantle(obj);
            }
        }
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


}
