package org.ccomp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.ccomp.data.database.dao.IDAO;
import org.ccomp.data.network.NetworkBoundResource;
import org.ccomp.data.network.Resource;
import org.ccomp.service.IService;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


public abstract class GenericRepository<T,K> {

    protected IDAO<T,K> mainDAO;
    protected IService<T> mainService;
    protected ExecutorService executorService;
    protected Predicate<T> defaultPredicate=new DefaultPredicate<T>() {
        @Override
        public boolean test(T t) {
            return true;
        }
    };
    private static final String TAG = "GenericRepository";

    public LiveData<List<T>> getAll(Predicate<T> predicate){
        MutableLiveData<List<T>> mutableLiveData= new MutableLiveData<>();
        //mutableLiveData.setValue(mainDAO.getAll().getValue());
        executorService.execute(()->{
            List<T> unfiltered=mainDAO.getAllSync();
            List<T> filtered=new ArrayList<>();
            for(T obj : unfiltered){
                if(predicate.test(obj)){
                    filtered.add(build(obj));
                }
            }
            mutableLiveData.postValue(filtered);

        });
        return mutableLiveData;
    }


    public LiveData<List<T>> getAll(){
        return getAll(getDefaultPredicate());
    }

    public List<T> getAllSync(Predicate<T> predicate){
        List<T> unfiltered=mainDAO.getAllSync();
        List<T> filtered=new ArrayList<>();
        for(T obj : unfiltered){
            if(predicate.test(obj)){
                filtered.add(build(obj));
            }
        }
        return filtered;
    }

    public List<T> getAllSync(){
        return getAllSync(defaultPredicate);
    }

    public LiveData<T> get(K key){
        LiveData<T> liveData=mainDAO.get(key);
        MutableLiveData<T> mutableLiveData=new MutableLiveData<>();
        mutableLiveData.postValue(build(liveData.getValue()));
        return mutableLiveData;
    }
    public T getSync(K key){
        T t=mainDAO.getSync(key);
        return build(mainDAO.getSync(key));
    }

    public abstract T build(T in);

    public void delete(T... args){
        if(args!=null){
            executorService.execute(()->{
                mainDAO.delete(args);
            });
        }
    }

    public void save(@NotNull T... args){
         save(true,args);
    }
    public void save(boolean complexSave, @NotNull T... args){
        if(args!=null){
            if(complexSave){
                for(T obj : args){
                    dismantle(obj);
                }
            }else{
                executorService.execute(()->{
                    mainDAO.save(args);
                });

            }
        }

    }

    public abstract void dismantle(T obj);

    public  abstract void saveCallResults(@NotNull List<T> items);
    public LiveData<Resource<List<T>>> loadDefault(boolean shouldFetch){
        return load(shouldFetch,defaultPredicate);
    }

    public LiveData<Resource<List<T>>> load(boolean shouldFetch, Predicate<T> predicate){
        return new NetworkBoundResource<List<T>,List<T>>(){
            @Override
            protected void saveCallResult(@NonNull List<T> items) {
                saveCallResults(items);
            }

            @Override
            protected boolean shouldFetch() {
                return shouldFetch;
            }

            @NonNull
            @Override
            protected LiveData<List<T>> loadFromDb() {
                return getAll(predicate);
            }

            @NonNull
            @Override
            protected Resource<LiveData<List<T>>> createCall() {
                try {

                    MutableLiveData<List<T>> mutableLiveData = new MutableLiveData<>();
                    if(mainService!=null) {
                        executorService.execute(() -> {

                            try {
                                List<T> items = mainService.fetch();
                                mutableLiveData.postValue(items);
                            } catch (Exception ex) {
                                Log.e(TAG, "createCall: ",ex );
                                
                                ex.printStackTrace();
                                boolean b=true;
                            }


                        });
                    }else {
                        return null;
                    }

                    return Resource.success(mutableLiveData);

                } catch (Exception ex) {

                    Log.d(TAG, "createCall: " + ex.getMessage());
                    return null;
                }

            }
        }.getAsLiveData();
    }


    public IDAO<T, K> getMainDAO() {
        return mainDAO;
    }

    public void setMainDAO(IDAO<T, K> mainDAO) {
        this.mainDAO = mainDAO;
    }

    public IService<T> getMainService() {
        return mainService;
    }

    public void setMainService(IService<T> mainService) {
        this.mainService = mainService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Predicate<T> getDefaultPredicate() {
        return defaultPredicate;
    }

    public void setDefaultPredicate(Predicate<T> defaultPredicate) {
        this.defaultPredicate = defaultPredicate;
    }
}
