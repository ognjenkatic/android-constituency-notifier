package org.ccomp.data.network;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private MediatorLiveData<Resource<ResultType>> result;

    @MainThread
    protected NetworkBoundResource() {
        result = new MediatorLiveData<>();
        result.postValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDb();

        result.addSource(dbSource, (data)->{
            result.removeSource(dbSource);
            if (shouldFetch()){
                fetchFromNetwork(dbSource);
            }else{
                result.addSource(dbSource, (updatedData)->{
                    setResourceValue(Resource.success(updatedData));
                });
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource){

        Resource<LiveData<RequestType>> networkSource = createCall();

        result.addSource(dbSource, (newData)->{
            setResourceValue(Resource.loading(newData));
        });


        result.addSource(networkSource.data, (newData)->{
            result.removeSource(networkSource.data);
            result.removeSource(dbSource);

            switch (networkSource.status){
                case SUCCESS:{
                    saveCallResult(processResponse(networkSource));

                    LiveData<ResultType> dbSourceUpdated = loadFromDb();
                    result.addSource(dbSourceUpdated, (updatedData)->{
                        setResourceValue(Resource.success(updatedData));
                    });
                    break;
                }

                case ERROR:{
                    onFetchFailed();
                    result.addSource(dbSource, (updatedData)->{
                        setResourceValue(Resource.error("Failed loading online data", updatedData));
                    });
                    break;
                }
            }


        });




    }
    public LiveData<Resource<ResultType>> getAsLiveData() { return result;}

    protected void onFetchFailed() {}

    @WorkerThread
    protected RequestType processResponse(Resource<LiveData<RequestType>> response) {
        return response.data.getValue();
    }

    @MainThread
    protected void setResourceValue(Resource<ResultType> value){
        if (result.getValue() != value){
            result.postValue(value);
        }
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch();

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Resource<LiveData<RequestType>> createCall();


}
