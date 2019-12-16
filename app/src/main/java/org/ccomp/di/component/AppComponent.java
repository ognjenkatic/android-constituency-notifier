package org.ccomp.di.component;

import android.app.Application;

import org.ccomp.AppController;
import org.ccomp.di.module.ActivityModule;
import org.ccomp.di.module.AppAssistedInjectModule;
import org.ccomp.di.module.DatabaseModule;
import org.ccomp.di.module.FragmentModule;
import org.ccomp.di.module.MiscModule;
import org.ccomp.di.module.RepositoryModule;
import org.ccomp.di.module.ServiceModule;
import org.ccomp.di.module.ViewModelModule;
import org.ccomp.di.module.WorkerModule;
import org.ccomp.factory.WorkerFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AppAssistedInjectModule.class, WorkerModule.class, DatabaseModule.class, ActivityModule.class, AndroidSupportInjectionModule.class, ViewModelModule.class, FragmentModule.class, ServiceModule.class, RepositoryModule.class, MiscModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    WorkerFactory factory();

    void inject(AppController appController);
}