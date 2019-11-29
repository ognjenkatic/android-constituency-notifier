package org.ccomp.service;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

public interface IAsyncService<T>  extends IService<T>{

    LiveData<List<T>> fetchAsync();
    LiveData<List<T>> fetchAsync(Map<String,Object> params);
}
