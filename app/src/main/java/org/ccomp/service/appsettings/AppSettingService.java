package org.ccomp.service.appsettings;

import android.content.Context;

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

import javax.inject.Inject;

public class AppSettingService extends AsyncService<AppSettings> {



    Context context;
    XMLValidatorService xmlValidatorService;
    NetworkAvailabilityService networkAvailabilityService;
    AppSettingsXMLParser xmlParser;


    @Inject
    public AppSettingService(Context context, XMLValidatorService xmlValidatorService, NetworkAvailabilityService networkAvailabilityService,AppSettingsXMLParser xmlParser) {

        this.context = context;
        this.xmlValidatorService = xmlValidatorService;
        this.networkAvailabilityService=networkAvailabilityService;
        this.xmlParser=xmlParser;
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
        if(networkAvailabilityService.isNetworkAvailable()) {
            MutableLiveData<List<AppSettings>> appSettingsMutableLiveData = new MutableLiveData<>();
            MutableLiveData<String> xmlConfigStringMutableLiveData=new MutableLiveData<>();

            final Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };

            Response.Listener<String> xmlSchemaListener=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    boolean valid=xmlValidatorService.validate(xmlConfigStringMutableLiveData.getValue(),response);
                    if(valid){
                        AppSettings appSettings=xmlParser.parse(xmlConfigStringMutableLiveData.getValue());
                        if(appSettings!=null){
                            List<AppSettings> appSettingsList=new ArrayList<>();
                            appSettingsList.add(appSettings);
                            appSettingsMutableLiveData.postValue(appSettingsList);
                        }
                    }

                }
            };


            Response.Listener<String> xmlConfigListener=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    xmlConfigStringMutableLiveData.postValue(response);
                    StringRequest xmlSchemaRequest=new StringRequest(Request.Method.GET,urlSchemaString,xmlSchemaListener, errorListener);
                    requestQueue.add(xmlSchemaRequest);

                }
            };







            StringRequest xmlConfigStringRequest = new StringRequest(Request.Method.GET, urlConfigString, xmlConfigListener,errorListener);
            requestQueue.add(xmlConfigStringRequest);

            return appSettingsMutableLiveData;
        }else {
            return null;
        }

    }
}
