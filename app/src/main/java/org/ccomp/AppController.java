package org.ccomp;

import android.app.Activity;
import android.app.Application;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;


import org.ccomp.data.domain.lang.Restring;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.AppSettingsRepository;
import org.ccomp.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AppController extends Application implements HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    Restring restring;

    @Inject
    AppSettingsRepository appSettingsRepository;

    LiveData<Resource<AppSettings>> appSettings;



    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public void onCreate() {
        super.onCreate();



        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        appSettings=appSettingsRepository.load(true);


    }

    @Override
    public AndroidInjector<androidx.fragment.app.Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    public Restring getRestring() {
        return restring;
    }

    public void setRestring(Restring restring) {
        this.restring = restring;
    }

    public AppSettingsRepository getAppSettingsRepository() {
        return appSettingsRepository;
    }

    public void setAppSettingsRepository(AppSettingsRepository appSettingsRepository) {
        this.appSettingsRepository = appSettingsRepository;
    }

    public LiveData<Resource<AppSettings>> getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(LiveData<Resource<AppSettings>> appSettings) {
        this.appSettings = appSettings;
    }
}