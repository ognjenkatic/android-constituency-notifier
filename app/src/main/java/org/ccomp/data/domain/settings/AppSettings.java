package org.ccomp.data.domain.settings;


import org.ccomp.data.domain.feed.FeedSettings;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppSettings {


    private List<FeedSettings> certFeeds;

    private List<EmailReporting> emailReportings;
    private Language defaultLang;
    private List<Language> supportedLangs;

    private Map<AppSettingsOption, AppSettingsProperty> properties;


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

    public List<FeedSettings> getCertFeeds() {
        return certFeeds;
    }

    public void setCertFeeds(List<FeedSettings> certFeeds) {
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

    public void setProperties(List<AppSettingsProperty> propertiesList) {
        Map<AppSettingsOption, AppSettingsProperty> properiesMap = new HashMap<>();
        for (AppSettingsProperty property : propertiesList) {
            if (properiesMap.containsKey(property.optionName)) {
                properiesMap.remove(property.optionName);
            }
            properiesMap.put(property.optionName, property);

        }
        this.properties = properiesMap;

    }
}
