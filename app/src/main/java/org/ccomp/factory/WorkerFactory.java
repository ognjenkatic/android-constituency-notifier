package org.ccomp.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.ccomp.interfaces.ChildWorkerFactory;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class WorkerFactory extends androidx.work.WorkerFactory {

    private final Map<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> creators;

    @Inject
    public WorkerFactory(Map<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> creators) {
        this.creators = creators;
    }

    @Nullable
    @Override
    public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        Provider<? extends ChildWorkerFactory> creator = creators.get(workerClassName);

        if (creator == null) {
            for (Map.Entry<Class<? extends ListenableWorker>, Provider<ChildWorkerFactory>> entry : creators.entrySet()) {
                try {
                    if (Class.forName(workerClassName).isAssignableFrom(entry.getKey())) {
                        creator = entry.getValue();
                        break;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return creator.get().create(appContext,workerParameters);
    }
}
