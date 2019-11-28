package org.ccomp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerFactory;

import org.ccomp.di.ViewModelKey;
import org.ccomp.di.WorkerKey;
import org.ccomp.interfaces.ChildWorkerFactory;
import org.ccomp.service.feed.FeedFetchWorker;
import org.ccomp.ui.feed.FeedViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(FeedFetchWorker.class)
    protected abstract ChildWorkerFactory bindChildWorkerFactory(FeedFetchWorker.ChildFactoryImpl childFactoryImpl);
}
