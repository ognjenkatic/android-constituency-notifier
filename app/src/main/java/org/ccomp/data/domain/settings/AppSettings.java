package org.ccomp.data.domain.settings;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppSettings {



    private List<Feed> certFeeds;
    private List<Feed> userFeeds;
    private List<EmailReporting> emailReportings;
    private Language defaultLang;
    private List<Language> supportedLangs;

    private Map<AppSettingsOption,AppSettingsProperty > properties;






    public List<Feed> getUserFeeds() {
        return userFeeds;
    }

    public void setUserFeeds(List<Feed> userFeeds) {
        this.userFeeds = userFeeds;
    }


    public Language getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(Language defaultLang) {
        this.defaultLang = defaultLang;
    }

    public List<EmailReporting> getEmailReportings() {
        return emailReportings;
    }

    public void setEmailReportings(List<EmailReporting> emailReportings) {
        this.emailReportings = emailReportings;
    }

    public List<Feed> getCertFeeds() {
        return certFeeds;
    }

    public void setCertFeeds(List<Feed> certFeeds) {
        this.certFeeds = certFeeds;
    }

    public List<Language> getSupportedLangs() {
        return supportedLangs;
    }

    public void setSupportedLangs(List<Language> supportedLangs) {
        this.supportedLangs = supportedLangs;
    }

    public Map<AppSettingsOption, AppSettingsProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<AppSettingsOption, AppSettingsProperty> properties) {
        this.properties = properties;
    }
    public void setProperties(List<AppSettingsProperty> propertiesList){
        Map<AppSettingsOption,AppSettingsProperty> properiesMap=new HashMap<>();
        for(AppSettingsProperty property :propertiesList){
            if(properiesMap.containsKey(property.optionName)){
                properiesMap.replace(property.optionName,property)
            }else{
                properiesMap.put(property.optionName,property);
            }
        }
        this.properties=properiesMap;

    }
}
