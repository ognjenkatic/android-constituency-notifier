package org.ccomp.service.appsettings;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.service.IAsyncService;
import org.ccomp.service.NetworkAvailabilityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class AppSettingService implements IAsyncService<AppSettings> {

    private static final String TAG = "AppSettingService";

    Context context;
    AppSettingsXMLParser xmlParser;
    ExecutorService executorService;
    NetworkAvailabilityService networkAvailabilityService;


    @Inject
    public AppSettingService(Context context, NetworkAvailabilityService networkAvailabilityService, AppSettingsXMLParser xmlParser, ExecutorService executorService) {

        this.context = context;
        this.networkAvailabilityService = networkAvailabilityService;
        this.xmlParser = xmlParser;
        this.executorService = executorService;
    }


    @Override
    public LiveData<List<AppSettings>> fetchAsync() {
        Map<String, Object> params = new HashMap<>();
        params.put("app.settings.url.config", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp-settings.xml");
        params.put("app.settings.url.schema", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp.xsd");

        return fetchAsync(params);
    }

    @Override
    public LiveData<List<AppSettings>> fetchAsync(Map<String, Object> params) {
        String urlConfigString = (String) params.get("app.settings.url.config");
        String urlSchemaString = (String) params.get("app.settings.url.schema");
        MutableLiveData<List<AppSettings>> appSettingsLiveData = new MutableLiveData<>();
        boolean networAvailable = networkAvailabilityService.isNetworkAvailable();
        if (networkAvailabilityService.isNetworkAvailable()) {

            executorService.execute(() -> {
                List<AppSettings> appSettingsList = new ArrayList<>();
                AppSettings appSettings = xmlParser.parse(urlConfigString);
                appSettingsList.add(appSettings);
                appSettingsLiveData.postValue(appSettingsList);
            });

        } else {
            return null;
        }

        return appSettingsLiveData;
    }


    @Override
    public List<AppSettings> fetch() {
        Map<String, Object> params = new HashMap<>();
        params.put("app.settings.url.config", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp-settings.xml");
        params.put("app.settings.url.schema", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp.xsd");

        return fetch(params);
    }

    @Override
    public List<AppSettings> fetch(Map<String, Object> params) {
        String urlConfigString = (String) params.get("app.settings.url.config");
        String urlSchemaString = (String) params.get("app.settings.url.schema");
        boolean networAvailable = networkAvailabilityService.isNetworkAvailable();
        List<AppSettings> appSettingsList = null;
        if (networkAvailabilityService.isNetworkAvailable()) {

            appSettingsList = new ArrayList<>();
            AppSettings appSettings = xmlParser.parse(urlConfigString);
            appSettingsList.add(appSettings);

        }
        return appSettingsList;


    }


}
