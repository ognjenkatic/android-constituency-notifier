package org.ccomp.service.appsettings;

import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppSettingService implements IService<AppSettings> {

    @Override
    public List<AppSettings> fetch() {
        Map<String,Object> params=new HashMap<>();
        params.put("app.settings.url.config","https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp-settings.xml");
        params.put("app.settings.url.schema","https://raw.githubusercontent.com/ognjenkatic/android-constituency-notifier/feature-XML-config/schema/ccomp.xsd");

        return fetch(params);
    }

    @Override
    public List<AppSettings> fetch(Map<String, Object> params) {
        String urlConfigString= (String) params.get("app.settings.url.config");
        String urlSchemaString=(String) params.get("app.settings.url.schema");
    }
}
