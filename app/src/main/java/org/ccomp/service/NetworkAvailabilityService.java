package org.ccomp.service;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

public class NetworkAvailabilityService {


    Context context;

    @Inject
    public NetworkAvailabilityService(Context context) {
        this.context = context;

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
