package org.ccomp.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public abstract class AsyncService<T> implements IAsyncService<T> {

   protected NetworkAvailabilityService networkAvailabilityService;





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

    public NetworkAvailabilityService getNetworkAvailabilityService() {
        return networkAvailabilityService;
    }

    public void setNetworkAvailabilityService(NetworkAvailabilityService networkAvailabilityService) {
        this.networkAvailabilityService = networkAvailabilityService;
    }
}
