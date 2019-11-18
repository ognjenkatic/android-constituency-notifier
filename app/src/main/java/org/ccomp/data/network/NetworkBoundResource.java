package org.ccomp.data.network;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import io.reactivex.Flowable;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private Observable<Object> result;

    @MainThread
    protected NetworkBoundResource() {
        Observable<Object> source;
        if (shouldFetch()) {
            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(apiResponse -> NetworkBoundResource.this.saveCallResult(NetworkBoundResource.this.processResponse(apiResponse)))
                    .flatMap((Function<Resource<RequestType>, ObservableSource<?>>) apiResponse -> NetworkBoundResource.this.loadFromDb().toObservable().map(Resource::success))
                    .doOnError(t -> NetworkBoundResource.this.onFetchFailed())
                    .onErrorResumeNext(t -> {
                        return NetworkBoundResource.this.loadFromDb()
                                .toObservable()
                                .map((Function<ResultType, Object>) data -> Resource.error(t.getMessage(), data));

                    })
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            source = loadFromDb()
                    .toObservable()
                    .map(Resource::success);
        }

        result = Observable.concat(
                loadFromDb()
                        .toObservable()
                        .map(Resource::loading)
                        .take(1),
                source
        );
    }

    public Observable<Object> getAsObservable() {return result;}

    protected void onFetchFailed() {}

    @WorkerThread
    protected RequestType processResponse(Resource<RequestType> response) {return response.data;}

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch();

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Observable<Resource<RequestType>> createCall();
}
