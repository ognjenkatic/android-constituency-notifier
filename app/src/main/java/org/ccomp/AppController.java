package org.ccomp;

import android.app.Activity;
import android.app.Application;
import android.media.VolumeShaper;
import android.os.StrictMode;

import androidx.fragment.app.Fragment;
import androidx.work.Configuration;
import androidx.work.WorkManager;


import org.ccomp.di.component.AppComponent;
import androidx.lifecycle.LiveData;

import org.ccomp.data.database.AppDatabase;
import org.ccomp.data.domain.lang.Restring;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.network.Resource;
import org.ccomp.data.repository.AppSettingsRepository;
import org.ccomp.di.component.DaggerAppComponent;
import org.ccomp.factory.WorkerFactory;

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

    @Inject
    AppDatabase appDatabase;


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public void onCreate() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        super.onCreate();


        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        appSettings = appSettingsRepository.load(true);

        WorkManager.initialize(this, new Configuration.Builder().setWorkerFactory(factory).build());

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