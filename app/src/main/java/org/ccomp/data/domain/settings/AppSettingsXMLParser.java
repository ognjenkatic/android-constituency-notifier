package org.ccomp.data.domain.settings;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class AppSettingsXMLParser {


    private static final String TAG = "AppSettingsXMLParser";

    public AppSettingsXMLParser(){

    }

    public AppSettings parse(String input)  {
        try {
            

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<certapp xmlns=\"http://www.certrs.org/certapp\"\n" +
                "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "\txsi:schemaLocation=\"http://www.certrs.org/certapp ccomp.xsd\">\n" +
                "\t<settings>\n" +
                "\t\t<MOTD is_url=\"false\">\n" +
                "\t\tHello World MOTD\n" +
                "\t\t</MOTD>\n" +
                "\t\t<default_lang lang_id=\"sr\">\n" +
                "\t\t\t<word key=\"hello\">\n" +
                "\t\t\t\tPozdrav</word>\n" +
                "\t\t\t<word key=\"world\">Svijete</word>\n" +
                "\t\t</default_lang>\n" +
                "\t\t<base64_logo>\n" +
                "\t\t\tdata:image/png;base64</base64_logo>\n" +
                "\t\t<supported_langs>\n" +
                "\t\t\t<supported_lang lang_id=\"sr\">\n" +
                "\t\t\t\t<word key=\"hello\">\n" +
                "\t\t\t\t\tPozdrav\n" +
                "\t\t\t\t</word>\n" +
                "\t\t\t\t<word key=\"world\">Svijete</word>\n" +
                "\t\t\t</supported_lang>\n" +
                "\t\t</supported_langs>\n" +
                "\t</settings>\n" +
                "\t<cert_feeds_settings>\n" +
                "\t\t<feed_settings>\n" +
                "\t\t\t<link>https://certrs.org/feed/atom/</link>\n" +
                "\t\t\t<lang>sr</lang>\n" +
                "\t\t\t<type>ATOM</type>\n" +
                "\t\t\t<version>1.0</version>\n" +
                "\t\t\t<categories>\n" +
                "\t\t\t\t<category name=\"Kategorija neka 0. stagod\"\n" +
                "\t\t\t\t\timportance=\"UNCATEGORIZED\" />\n" +
                "\t\t\t\t<category name=\"Kategorija neka 1. stagod\"\n" +
                "\t\t\t\t\timportance=\"HIDE\" />\n" +
                "\t\t\t\t<category name=\"Kategorija neka 2. stagod\"\n" +
                "\t\t\t\t\timportance=\"NOTIFY\" />\n" +
                "\t\t\t\t<category name=\"Novosti\" importance=\"SHOW\" />\n" +
                "\t\t\t</categories>\n" +
                "\t\t</feed_settings>\n" +
                "\t</cert_feeds_settings>\n" +
                "\t<reporting_emails>\n" +
                "\t\n" +
                "\t<reporting_email address=\"cert@certrs.org\"\n" +
                "\t\tdefault_tlp=\"AMBER\">\n" +
                "\t\t<incident_category incident_category_id=\"ID1\"\n" +
                "\t\t\tincident_type=\"Tip1\" incident_class=\"Klasa1\" />\n" +
                "\t\t<incident_category incident_category_id=\"ID2\"\n" +
                "\t\t\tincident_type=\"Tip2\" incident_class=\"Klasa2\" />\n" +
                "\t\t<incident_category incident_category_id=\"ID3\"\n" +
                "\t\t\tincident_type=\"Tip3\" incident_class=\"Klasa3\" />\n" +
                "\t\t<incident_category incident_category_id=\"ID4\"\n" +
                "\t\t\tincident_type=\"Tip4\" incident_class=\"Klasa4\">\n" +
                "\t\t\t<incident_type_description>Opis tipa 4</incident_type_description>\n" +
                "\t\t\t<incident_class_description>Opis klase 4</incident_class_description>\n" +
                "\t\t\t<description>Opis4</description>\n" +
                "\t\t</incident_category>\n" +
                "\t\t<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\tincident_type=\"Tip5\" incident_class=\"Klasa5\">\n" +
                "\t\t\t<incident_type_description>Opis tipa 5</incident_type_description>\n" +
                "\t\t\t<incident_class_description>Opis klase 5</incident_class_description>\n" +
                "\t\t\t<description>Opis5</description>\n" +
                "\t\t</incident_category>\n" +
                "\t\t<pgp_public>\n" +
                "\t\t\t<id>0xEE03B7F3</id>\n" +
                "\t\t\t<fingerprint>6F1F 1A61 EBA9 50A7 E3FC 5C03 1B5A 3A3B EE03 B7F3</fingerprint>\n" +
                "\t\t\t<key>-----BEGIN PGP PUBLIC KEY BLOCK-----\n" +
                "\n" +
                "-----END PGP PUBLIC KEY BLOCK-----</key>\n" +
                "\t\t</pgp_public>\n" +
                "\t</reporting_email>\n" +
                "\t\n" +
                "\t</reporting_emails>\n" +
                "\t\n" +
                "</certapp>";

        xpp.setInput( new StringReader(xml ) );
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == XmlPullParser.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == XmlPullParser.END_TAG) {
                System.out.println("End tag "+xpp.getName());
            } else if(eventType == XmlPullParser.TEXT) {
                System.out.println("Text "+xpp.getText());
            }
            eventType = xpp.next();
        }
        System.out.println("End document");
        }catch (Exception ex){
            Log.e(TAG, "parse: ",ex );
        }
        return null;
    }
}
