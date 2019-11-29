package org.ccomp.service;

import java.util.List;
import java.util.Map;

public abstract class AsyncService<T> implements IAsyncService<T> {

    NetworkAvailabilityService networkAvailabilityService;


    public AsyncService(NetworkAvailabilityService networkAvailabilityService) {
        this.networkAvailabilityService = networkAvailabilityService;
    }

    @Override
    public List<T> fetch() {
        if (networkAvailabilityService.isNetworkAvailable()) {
            return fetchAsync().getValue();
        } else {
            return null;
        }
    }

    @Override
    public List<T> fetch(Map<String, Object> params) {
        if (networkAvailabilityService.isNetworkAvailable()) {
            return fetchAsync(params).getValue();
        } else {
            return null;
        }
    }


}
