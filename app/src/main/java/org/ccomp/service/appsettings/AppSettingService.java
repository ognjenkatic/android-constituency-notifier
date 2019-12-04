package org.ccomp.service.appsettings;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.service.AsyncService;
import org.ccomp.service.IService;
import org.ccomp.service.NetworkAvailabilityService;
import org.ccomp.service.XMLValidatorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class AppSettingService extends AsyncService<AppSettings> {

    private static final String TAG = "AppSettingService";

    Context context;
    XMLValidatorService xmlValidatorService;
    AppSettingsXMLParser xmlParser;
    ExecutorService executorService;


    @Inject
    public AppSettingService(Context context, XMLValidatorService xmlValidatorService, NetworkAvailabilityService networkAvailabilityService,AppSettingsXMLParser xmlParser, ExecutorService executorService) {

        this.context = context;
        this.xmlValidatorService = xmlValidatorService;
        this.networkAvailabilityService=networkAvailabilityService;
        this.xmlParser=xmlParser;
        this.executorService=executorService;
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
        String urlConfigString = (String) params.get("app.settings.url.config");
        String urlSchemaString = (String) params.get("app.settings.url.schema");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        MutableLiveData<List<AppSettings>> appSettingsLiveData=null;
        if(networkAvailabilityService.isNetworkAvailable()) {
            appSettingsLiveData=new MutableLiveData<>();
            executorService.execute(()->{
                    AppSettings appSettings=xmlParser.parse(urlConfigString);
                    appSettingsLiveData.postValue(appSettings);
            });

        }

        return appSettingsLiveData;



    }
}
