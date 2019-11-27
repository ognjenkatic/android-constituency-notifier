package org.ccomp.data.domain.settings;


import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;

import java.util.List;

public class AppSettings {

    private String base64Logo;
    private MOTD mOTD;
    private List<Feed> certFeeds;
    private List<Feed> userFeeds;
    private List<EmailReporting> emailReportings;
    private Language defaultLang;
    private List<Language> supportedLangs;


    public String getBase64Logo() {
        return base64Logo;
    }

    public void setBase64Logo(String base64Logo) {
        this.base64Logo = base64Logo;
    }

    public MOTD getmOTD() {
        return mOTD;
    }

    public void setmOTD(MOTD mOTD) {
        this.mOTD = mOTD;
    }

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
}
