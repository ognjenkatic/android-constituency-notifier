package org.ccomp.interfaces;

import android.content.Context;

import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public interface ChildWorkerFactory {

    ListenableWorker create(Context context, WorkerParameters parameters);
}
