package org.ccomp;

import android.app.Activity;
import android.app.Application;
import android.media.VolumeShaper;
import android.os.StrictMode;


import androidx.fragment.app.Fragment;
import androidx.work.Configuration;
import androidx.work.WorkManager;


import org.ccomp.di.component.AppComponent;
import org.ccomp.di.component.DaggerAppComponent;
import org.ccomp.factory.WorkerFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AppController extends Application implements HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

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



        AppComponent ac = DaggerAppComponent.builder()
                .application(this)
                .build();

        ac.inject(this);
        WorkerFactory factory = ac.factory();

        WorkManager.initialize(this, new Configuration.Builder().setWorkerFactory(factory).build());

    }

    @Override
    public AndroidInjector<androidx.fragment.app.Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}