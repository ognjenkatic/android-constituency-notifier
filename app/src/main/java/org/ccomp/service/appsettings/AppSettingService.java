package org.ccomp.service.appsettings;

import androidx.lifecycle.LiveData;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.service.AsyncService;
import org.ccomp.service.IService;
import org.ccomp.service.NetworkAvailabilityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class AppSettingService extends AsyncService<AppSettings> {



    @Inject
    public AppSettingService(NetworkAvailabilityService networkAvailabilityService){
        this.networkAvailabilityService= networkAvailabilityService;
    }

    @Override
    public LiveData<List<AppSettings>> fetchAsync() {
        Map<String,Object> params=new HashMap<>();
        params.put("app.settings.url.config","https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp-settings.xml");
        params.put("app.settings.url.schema","https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp.xsd");

        return fetchAsync(params);
    }

    @Override
    public LiveData<List<AppSettings>> fetchAsync(Map<String, Object> params) {
        String urlConfigString= (String) params.get("app.settings.url.config");
        String urlSchemaString=(String) params.get("app.settings.url.schema");
        LiveData<List<AppSettings>> appSettingsList=null;

        return appSettingsList;
    }
}
