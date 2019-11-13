package org.ccomp.data.domain.settings;


import org.ccomp.data.domain.feed.Feed;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.lang.Language;

import java.util.List;

public class AppSettings {

    private String base64Logo;
    private MOTD mOTD;
    private Feed certFeed;
    private List<Feed> userFeeds;
    private EmailReporting emailReporting;
    private Language defaultLang;


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

    public Feed getCertFeed() {
        return certFeed;
    }

    public void setCertFeed(Feed certFeed) {
        this.certFeed = certFeed;
    }

    public List<Feed> getUserFeeds() {
        return userFeeds;
    }

    public void setUserFeeds(List<Feed> userFeeds) {
        this.userFeeds = userFeeds;
    }

    public EmailReporting getEmailReporting() {
        return emailReporting;
    }

    public void setEmailReporting(EmailReporting emailReporting) {
        this.emailReporting = emailReporting;
    }

    public Language getDefaultLang() {
        return defaultLang;
    }

    public void setDefaultLang(Language defaultLang) {
        this.defaultLang = defaultLang;
    }
}
