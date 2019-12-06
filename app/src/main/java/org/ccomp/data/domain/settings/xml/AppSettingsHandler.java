package org.ccomp.data.domain.settings.xml;

import android.util.Log;

import org.apache.commons.text.TextStringBuilder;
import org.ccomp.data.domain.feed.FeedCategory;
import org.ccomp.data.domain.feed.FeedCategoryImportance;
import org.ccomp.data.domain.feed.FeedSettings;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.lang.Translation;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.domain.settings.TLP;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AppSettingsHandler extends DefaultHandler {


    private static final String TAG = "AppSettingsHandler";

    private AppSettings appSettings;
    private TextStringBuilder elementValue=new TextStringBuilder();

    private final String tag_certapp = "certapp";
    private final String tag_settings = "settings";
    private final String tag_default_lang = "default_lang";
    private final String tag_base64_logo = "base64_logo";
    private final String tag_cert_feeds_settings = "cert_feeds_settings";
    private final String tag_feed_settings = "feed_settings";
    private final String tag_link = "feed_link";
    private final String tag_lang = "feed_lang";
    private final String tag_categories = "feed_categories";
    private final String tag_category = "feed_category";
    private final String tag_reporting_emails = "reporting_emails";
    private final String tag_reporting_email = "reporting_email";
    private final String tag_incident_category = "incident_category";
    private final String tag_incident_type_description = "incident_type_description";
    private final String tag_incident_class_description = "incident_class_description";
    private final String tag_description = "incident_description";
    private final String tag_pgp_public = "pgp_public";
    private final String tag_id = "pgp_id";
    private final String tag_fingerprint = "pgp_fingerprint";
    private final String tag_key = "pgp_pubkey";
    private final String tag_supported_langs = "supported_langs";
    private final String tag_supported_lang = "supported_lang";
    private final String tag_word = "translation";

    private final String att_schema_location = "xsi:schemaLocation";
    private final String att_feed_name = "name";
    private final String att_feed_importance = "importance";
    private final String att_feed_taxonomy = "taxonomy_uri";
    private final String att_email_tlp = "default_tlp";
    private final String att_email_address = "address";
    private final String att_incident_category_id = "incident_category_id";
    private final String att_incident_category_class = "incident_class";
    private final String att_incident_category_type = "incident_type";
    private final String att_lang_id = "lang_id";
    private final String att_lang_description = "lang_description";
    private final String att_lang_name = "lang_name";
    private final String att_word_word = "word";

    List<AppSettingsProperty> properties;
    ArrayList<FeedSettings> certFeeds;
    List<EmailReporting> emailReportings;
    List<Language> supportedLangs;

    String schemaLocation;


    //Pre i atributi


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


        switch (qName) {
            case tag_certapp: {
                if (attributes.getLength() > 0) {
                    schemaLocation = attributes.getValue(att_schema_location);
                    String [] tmp=schemaLocation.split(" ");
                    schemaLocation=tmp[tmp.length-1];
                }
            }
            break;
            case tag_feed_settings: {
                FeedSettings feedSettings = new FeedSettings();
                certFeeds.add(feedSettings);

            }
            break;
            case tag_categories: {
                getLastFeedSettings().setCategories(new ArrayList<>());
            }
            break;
            case tag_category: {
                if (attributes.getLength() > 0) {
                    String name = attributes.getValue(att_feed_name);
                    String importance = attributes.getValue(att_feed_importance);
                    String taxonomy = attributes.getValue(att_feed_taxonomy);
                    URL taxonomyUrl = null;
                    try {
                        taxonomyUrl = new URL(taxonomy);
                    } catch (Exception ex) {
                        //Log.e(TAG, "startElement: taxonomy URL", ex);
                    }
                    FeedCategory feedCategory = new FeedCategory();
                    feedCategory.setName(name);
                    feedCategory.setFeedCategoryImportance(FeedCategoryImportance.valueOf(importance));
                    feedCategory.setTeaxonomyUrl(taxonomyUrl);
                    getLastFeedSettings().getCategories().add(feedCategory);
                }

            }
            break;
            case tag_reporting_email: {
                if (attributes.getLength() > 0) {
                    EmailReporting emailReporting = new EmailReporting();
                    String address = attributes.getValue(att_email_address);
                    String tlpString = attributes.getValue(att_email_tlp);
                    emailReporting.setAddress(address);
                    emailReporting.setIncidentCategories(new ArrayList<>());
                    try {
                        emailReporting.setDefaultTLP(TLP.valueOf(tlpString));
                    } catch (Exception ex) {
                        Log.e(TAG, "startElement: email TLP", ex);
                    }

                    emailReportings.add(emailReporting);
                }


            }
            break;
            case tag_incident_category: {
                if (attributes.getLength() > 0) {
                    IncidentCategory incidentCategory = new IncidentCategory();
                    String type = attributes.getValue(att_incident_category_type);
                    String clazz = attributes.getValue(att_incident_category_class);
                    String id = attributes.getValue(att_incident_category_id);
                    incidentCategory.setTypeName(type);
                    incidentCategory.setClassName(clazz);
                    incidentCategory.setId(id);
                    getLastEmailReporting().getIncidentCategories().add(incidentCategory);
                }


            }
            break;
            case tag_supported_lang: {
                if (attributes.getLength() > 0) {
                    Language language = new Language();
                    String id = attributes.getValue(att_lang_id);
                    String name = attributes.getValue(att_lang_name);
                    String description = attributes.getValue(att_lang_description);
                    language.setLangId(id);
                    language.setName(name);
                    language.setDescription(description);
                    language.setTranslations(new ArrayList<>());
                    supportedLangs.add(language);
                }

            }
            break;
            case tag_word: {
                if (attributes.getLength() > 0) {

                    Translation translation = new Translation();
                    String word = attributes.getValue(att_word_word);
                    translation.setWord(word);
                    translation.setLangId(getLastLang().getLangId());
                    getLastLang().getTranslations().add(translation);

                }

            }
            break;

        }
    }


    //Post i value
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case tag_base64_logo: {
                AppSettingsProperty appSettingsProperty = new AppSettingsProperty(AppSettingsOption.app_settings_logo_base64, elementValue.build());
                properties.add(appSettingsProperty);
            }
            break;
            case tag_default_lang: {
                AppSettingsProperty appSettingsProperty = new AppSettingsProperty(AppSettingsOption.app_settings_lang_default, elementValue.build());
                properties.add(appSettingsProperty);
            }
            break;
            case tag_link: {
                getLastFeedSettings().setLink(elementValue.build());

            }
            break;
            case tag_lang: {
                getLastFeedSettings().setLang(elementValue.build());
            }
            break;
            case tag_incident_type_description: {
                getLastCategory().setTypeDescription(elementValue.build());
            }
            break;

            case tag_incident_class_description: {
                getLastCategory().setClassDescription(elementValue.build());

            }
            break;
            case tag_description: {
                getLastCategory().setDescription(elementValue.build());

            }
            break;
            case tag_id: {
                getLastEmailReporting().setPgpId(elementValue.build());
            }
            break;
            case tag_fingerprint: {
                getLastEmailReporting().setPgpFingerprint(elementValue.build());
            }
            break;
            case tag_key: {
                getLastEmailReporting().setPgpKey(elementValue.build());
            }
            break;
            case tag_word: {
                getLastTranslation().setValue(elementValue.build());
            }
            break;


        }

        elementValue.delete(0,elementValue.length());
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
            elementValue.append(ch,start,length);
            boolean b=true;



    }

    @Override
    public void startDocument() throws SAXException {
        appSettings = new AppSettings();
        properties = new ArrayList<>(10);
        certFeeds = new ArrayList<>(1);
        emailReportings = new ArrayList<>(3);
        supportedLangs = new ArrayList<>(2);


    }

    @Override
    public void endDocument() throws SAXException {
        appSettings.setProperties(properties);
        appSettings.setCertFeeds(certFeeds);
        appSettings.setEmailReportings(emailReportings);
        appSettings.setSupportedLangs(supportedLangs);
    }

    public FeedSettings getLastFeedSettings() {
        return certFeeds.get(certFeeds.size() - 1);
    }

    public EmailReporting getLastEmailReporting() {
        return emailReportings.get(emailReportings.size() - 1);
    }

    public IncidentCategory getLastCategory() {
        List<IncidentCategory> categories = getLastEmailReporting().getIncidentCategories();

        return categories.get(categories.size() - 1);
    }

    public Language getLastLang() {
        return supportedLangs.get(supportedLangs.size() - 1);
    }

    public Translation getLastTranslation() {
        List<Translation> translations = getLastLang().getTranslations();
        return translations.get(translations.size() - 1);
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }
}
