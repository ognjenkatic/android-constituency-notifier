package org.ccomp.service.appsettings;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.service.NetworkAvailabilityService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class AppSettingService {

    private static final String TAG = "AppSettingService";


    AppSettingsXMLParser xmlParser;
    NetworkAvailabilityService networkAvailabilityService;


    @Inject
    public AppSettingService(NetworkAvailabilityService networkAvailabilityService, AppSettingsXMLParser xmlParser) {

        this.networkAvailabilityService = networkAvailabilityService;
        this.xmlParser = xmlParser;

    }


    public AppSettings fetch() {
        Map<String, Object> params = new HashMap<>();
        params.put("app.settings.url.config", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/reporting/schema/ccomp-settings.xml");
        params.put("app.settings.url.schema", "https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/reporting/schema/ccomp.xsd");

        return fetch(params);
    }


    public AppSettings fetch(Map<String, Object> params) {
        String urlConfigString = (String) params.get("app.settings.url.config");
        String urlSchemaString = (String) params.get("app.settings.url.schema");
        AppSettings appSettings = null;
        if (networkAvailabilityService.isNetworkAvailable()) {
            appSettings = xmlParser.parse(urlConfigString);
        }
        return appSettings;
    }


}
