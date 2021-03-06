package org.ccomp.di.component;

import android.app.Application;

import org.ccomp.AppController;
import org.ccomp.di.module.ActivityModule;
import org.ccomp.di.module.DatabaseModule;
import org.ccomp.di.module.FeedModule;
import org.ccomp.di.module.FragmentModule;
import org.ccomp.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {FeedModule.class, DatabaseModule.class, ActivityModule.class, AndroidSupportInjectionModule.class, ViewModelModule.class, FragmentModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AppController appController);
}