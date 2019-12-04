package org.ccomp.data.domain.settings;

import android.util.Log;

import org.ccomp.data.domain.settings.xml.AppSettingsHandler;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AppSettingsXMLParser {


    private static final String TAG = "AppSettingsXMLParser";


    SAXParser parser;
    AppSettingsHandler handler=new AppSettingsHandler();

    public AppSettingsXMLParser(){
        init();
    }



    public AppSettings parse(String input)  {
        AppSettings appSettings=null;
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            AppSettingsHandler handler=new AppSettingsHandler();
            InputSource source=new InputSource(new StringReader(xml));

            parser.parse(source,handler);
            appSettings=handler.getAppSettings();
            boolean b=true;


        }catch (Exception ex){
            Log.e(TAG, "parse: ",ex );
        }
        return appSettings;
    }

    public void init(){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuffer.append("<certapp xmlns=\"http://www.certrs.org/certapp\"\n");
        stringBuffer.append("\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        stringBuffer.append("\txsi:schemaLocation=\"http://www.certrs.org/certapp ccomp.xsd\">\n");
        stringBuffer.append("\t<settings>\n");
        stringBuffer.append("\t\t<default_lang>\n");
        stringBuffer.append("\t\t\tsr-latn-BA\n");
        stringBuffer.append("\t\t</default_lang>\n");
        stringBuffer.append("\t\t<base64_logo>\n");
        stringBuffer.append("\t\t\tdata:image/png;base64,");
        stringBuffer.append("\t\t</base64_logo>\n");
        stringBuffer.append("\t</settings>\n");
        stringBuffer.append("\t<cert_feeds_settings>\n");
        stringBuffer.append("\t\t<feed_settings>\n");
        stringBuffer.append("\t\t\t<feed_link>https://certrs.org/feed/atom/</feed_link>\n");
        stringBuffer.append("\t\t\t<feed_lang>sr-latn-BA</feed_lang>\n");
        stringBuffer.append("\t\t\t<feed_categories>\n");
        stringBuffer.append("\t\t\t\t<feed_category name=\"Kategorija neka 0. stagod\"\n");
        stringBuffer.append("\t\t\t\t\timportance=\"UNCATEGORIZED\" />\n");
        stringBuffer.append("\t\t\t\t<feed_category name=\"Kategorija neka 1. stagod\"\n");
        stringBuffer.append("\t\t\t\t\timportance=\"HIDE\" />\n");
        stringBuffer.append("\t\t\t\t<feed_category name=\"Kategorija neka 2. stagod\"\n");
        stringBuffer.append("\t\t\t\t\timportance=\"NOTIFY\" />\n");
        stringBuffer.append("\t\t\t\t<feed_category name=\"Novosti\" importance=\"SHOW\" />\n");
        stringBuffer.append("\t\t\t</feed_categories>\n");
        stringBuffer.append("\t\t</feed_settings>\n");
        stringBuffer.append("\t</cert_feeds_settings>\n");
        stringBuffer.append("\t<reporting_emails>\n");
        stringBuffer.append("\t\t<reporting_email address=\"cert@certrs.org\"\n");
        stringBuffer.append("\t\t\tdefault_tlp=\"AMBER\">\n");
        stringBuffer.append("\t\t\t<incident_category incident_category_id=\"ID1\"\n");
        stringBuffer.append("\t\t\t\tincident_type=\"Tip1\" incident_class=\"Klasa1\" />\n");
        stringBuffer.append("\t\t\t<incident_category incident_category_id=\"ID2\"\n");
        stringBuffer.append("\t\t\t\tincident_type=\"Tip2\" incident_class=\"Klasa2\" />\n");
        stringBuffer.append("\t\t\t<incident_category incident_category_id=\"ID3\"\n");
        stringBuffer.append("\t\t\t\tincident_type=\"Tip3\" incident_class=\"Klasa3\" />\n");
        stringBuffer.append("\t\t\t<incident_category incident_category_id=\"ID4\"\n");
        stringBuffer.append("\t\t\t\tincident_type=\"Tip4\" incident_class=\"Klasa4\">\n");
        stringBuffer.append("\t\t\t\t<incident_type_description>Opis tipa 4</incident_type_description>\n");
        stringBuffer.append("\t\t\t\t<incident_class_description>Opis klase 4</incident_class_description>\n");
        stringBuffer.append("\t\t\t\t<incident_description>Opis4</incident_description>\n");
        stringBuffer.append("\t\t\t</incident_category>\n");
        stringBuffer.append("\t\t\t<incident_category incident_category_id=\"ID5\"\n");
        stringBuffer.append("\t\t\t\tincident_type=\"Tip5\" incident_class=\"Klasa5\">\n");
        stringBuffer.append("\t\t\t\t<incident_type_description>Opis tipa 5</incident_type_description>\n");
        stringBuffer.append("\t\t\t\t<incident_class_description>Opis klase 5</incident_class_description>\n");
        stringBuffer.append("\t\t\t\t<incident_description>Opis5</incident_description>\n");
        stringBuffer.append("\t\t\t</incident_category>\n");
        stringBuffer.append("\t\t\t<pgp_public>\n");
        stringBuffer.append("\t\t\t\t<pgp_id>0xEE03B7F3</pgp_id>\n");
        stringBuffer.append("\t\t\t\t<pgp_fingerprint>6F1F 1A61 EBA9 50A7 E3FC 5C03 1B5A 3A3B EE03 B7F3</pgp_fingerprint>\n");
        stringBuffer.append("\t\t\t\t<pgp_pubkey>-----BEGIN PGP PUBLIC KEY BLOCK-----\n");
        stringBuffer.append("\n");
        stringBuffer.append("mQINBFzr6FoBEAC6zON1gkaKluE7Tp6cXbtLDzR6xwbTI/TIl1QkMAMC/x3f9jxO\n");
        stringBuffer.append("cxQjRMWAVCWu69gp7dAeFbbhbTmqpc21KKgF8Na3I4A1E622w0TlZJk39Nvp5JXt\n");
        stringBuffer.append("QcsRN1Eu5Omn+crW0JBh/KhsMyp2JFUpjhov1ZT7varsbEpyiczuF8xGcGkBiXRh\n");
        stringBuffer.append("uBThnYDUO9lQ5AkWfTBDjmlTSK2LrQyNKmeApnlmZlfa1Z8D8fAHf3u4FbaeQot2\n");
        stringBuffer.append("TuonQ2uN53wUc0vq/Sxcjo5YxHcfvmb5vb/ofzickLSyPLbMzaYIvKMR1Wr8wUSM\n");
        stringBuffer.append("gwR93cIOA6klxZdbyCvRlHiczmzufMNF1vZU0zy6J5afqWBOi4ShSh5WxZrlQ4wU\n");
        stringBuffer.append("T0emFkuz4JMoAOL58L3D6157Zyev3vBp0yKPTrNl0cxCM/04X8SP5QahITuDIoR/\n");
        stringBuffer.append("nAJWLJJ4bSqJyLB+ipVbiFRBkgQ6UVVKLFF5NMy8Jf4mRs2gHLyOwbbAU3m8s6od\n");
        stringBuffer.append("/z/ipOylAwGK14tEy8RvhpOYQNsd5/oqXCll4YlHOv13jA28Uij30J69/I6r6Lq4\n");
        stringBuffer.append("hrvyochoyasiyujWxKUV4FUeR6Lf4s1ql4k1u8Y/A6dyFTJHmGXC6PUdiONVJjUA\n");
        stringBuffer.append("0TUwCs4COqmJ4CAsnlNV7Td/Mm6Cmx2iAquDjcvPCvSDKLbSdX9z1l+CZwARAQAB\n");
        stringBuffer.append("tC1DRVJUIFJTIEluY2lkZW50IFJlcG9ydHMgPHJlcG9ydHNAY2VydHJzLm9yZz6J\n");
        stringBuffer.append("AlQEEwEIAD4WIQRvHxph66lQp+P8XAMbWjo77gO38wUCXOvoWgIbAwUJAeJRxgUL\n");
        stringBuffer.append("CQgHAgYVCgkICwIEFgIDAQIeAQIXgAAKCRAbWjo77gO38woREAC3Q0DM8NqJFaKh\n");
        stringBuffer.append("7kdustVP8HFgcw5AUvahyDfZoJCaMUU2WTskwcU5idNhO2u6n+Hg12VRDw9iZzM1\n");
        stringBuffer.append("7bQBEodJoRSp7WvpUfv3wseP4OAxFKmF2XBPG9sMGL7TKIjMwIlNy/1NVHe/KI6A\n");
        stringBuffer.append("ipQnnKsBs29f56Ygy0jbtIj1EEJDrTOHnH/JShoue7bXTQnZI4B4cTYy3Nc2xtK1\n");
        stringBuffer.append("TqQBHNBL1AX3DjasYIsVDrN1eI97SIyMxiacyX41ojuZy+asqp5T+qL9mk2HaJuQ\n");
        stringBuffer.append("MirqL/8/qF88aUVF6PUXtnmsu0WvUa79sBVrqn2b4yrNwhgLPAvIS2z/0+2x3AAN\n");
        stringBuffer.append("1FrP7kjFOc2sG0VBDWe4cjqpV7BbuzWttR/SXrkUQzo/IyygIRKRWyu4jHRLXDot\n");
        stringBuffer.append("0ff5NcWD5H4O3+bEUTgy2M10/CBrt7zYh8pqiiqYkz5s1qmk2+bMiuL9KFgxdueA\n");
        stringBuffer.append("SZRv/ThFcP5IMIGflR28pkiPCogiHl5iLgPb9jaelpC3P1DMhv3yqzoI/DzqXCXV\n");
        stringBuffer.append("/2IMHBmM+Gtbvw4kbSksHpJmNcljPnKYrqhwpxA3Kj3WMRJQhkMsIVtF3OfoACY2\n");
        stringBuffer.append("VCdmZr8OWYOQTmQN3w4bis+NSwakpG8wsZKo3v2nTjD/0oKSPWaqoPiJA0I8Bth5\n");
        stringBuffer.append("Jz1AJ4nRGWfjCcbwITWRj9MZ2a4Q9bkCDQRc6+haARAAwJ28n104fU9DVlqx6ni+\n");
        stringBuffer.append("skx6rgTJGISypDsC5t8a7ChLaxpL2HbM/gO5Jc0Y8w0GnQXyB5MhrPtp51Y+BqRT\n");
        stringBuffer.append("gzs1VNFeJ14rJDDHEoUn8EVCtStr0u8mgMaF3U6gOXEVl3bKcQpc87tSnNrY501G\n");
        stringBuffer.append("a4rFy0SxWI5GpOTvmN7eqb5HX1V1B6wgcJhCQukIUfSDRwZpXF14jMahigwj2R1B\n");
        stringBuffer.append("2r0gll7XEaq2XWXVxgAShLG+k8gMb2mtGYEjnJWuDhjp4qDtpskVebe2Qc+k7uma\n");
        stringBuffer.append("JacvQ/wdFb0nuqR4Th4fHlpa9IqypxYMnIyBB4ujKWSeAinZgOteOJ5xFpSr6a0/\n");
        stringBuffer.append("4DiXJa9D4lEtG5XYxfpSXMpejJFXYkf/fvxQF8KGVn6vjwKWWzc7+dbSRwVTHD52\n");
        stringBuffer.append("B1IIPHMSHjCQMTHw004Vt8r+Aa4r6xO9cHscIhtqhvaH+xFxDllEyGwZkMhkf5sg\n");
        stringBuffer.append("emjSzXtpSce5iZne+rf+Ae7/SqwEbHkg5WOe57Oc/YmLmGoS5qignEDtJ0Y0zd2B\n");
        stringBuffer.append("eV4mlvnAU1lHra5Kh8/5+TuqAD37QWUvytQb/uCkOfj6W3SoHo0l0E44i2eOpw5r\n");
        stringBuffer.append("Y62z3SkN+LyVBwSWekl+SNhabrNtdqxXAF+NAysFj+x+DzzMX1Lpl8lQ25v4LV2M\n");
        stringBuffer.append("k5OdWCrWtvmZ2WKJfEArZRUAEQEAAYkCPAQYAQgAJhYhBG8fGmHrqVCn4/xcAxta\n");
        stringBuffer.append("OjvuA7fzBQJc6+haAhsMBQkB4lHGAAoJEBtaOjvuA7fzJIoP/Ak7S6ghidb518UI\n");
        stringBuffer.append("I/e+qzFs9LUi9J7PmKVIfpujkFPTk89kwiTD3PF5TEBXmPD4EujYXiESzGsl/l0a\n");
        stringBuffer.append("npMF75A0eMee5m03eOwQEfGYBYE70KPM6z/63RH61w7FSq1XhlJOnacBE6b4XL46\n");
        stringBuffer.append("Zu6UCYRQY7XFZ9otTIikTQqXsto971pVUioKr5ZDNDVbHOnLJoxp8E4u0Z28u7gs\n");
        stringBuffer.append("aOv6FZfm4sv/DgbmmQDytvF7iQ8mx5Cfubv+3pA9EWmLZ+ov/3o/SabYe8Qpob0G\n");
        stringBuffer.append("z2Mi+mMX0Aa0AG6KpMyqHUTXM35zAZRWosMK7LS7AV9y/BtN1ahCb8Fh2ffRejSB\n");
        stringBuffer.append("6QMh64pVfliIsj52hibF/dPe7WXMqYZCqqQk4Crl5btO7X6iiUvH9tefMxEH+huA\n");
        stringBuffer.append("nuGXt2AcalY042dYd8taXBSNTgyl9wFuc0ajOn+6baKXW3EyMk4inDjio2XYY2mC\n");
        stringBuffer.append("7v09CWwZnA0qR4+UkFs8sBDWeeqbmBec43uOmf8bNDu29veJ9WdS5/KlSZGv0AGe\n");
        stringBuffer.append("aErm6CxmxgL92cdk/eQd5lRpMrg59XUfInaRCvih8uQ2MttKEPhL2hLNuHgYFsoF\n");
        stringBuffer.append("2CzPdmG7cMxpnh8y78Fx2bZkDEHXMtIq3Il+TVIGyMobp03Jc3HSHo3AWNPGyWL5\n");
        stringBuffer.append("482r8oofl7dXTi1+oxvjwpokh1w0\n");
        stringBuffer.append("=Cwt0\n");
        stringBuffer.append("-----END PGP PUBLIC KEY BLOCK-----</pgp_pubkey>\n");
        stringBuffer.append("\t\t\t</pgp_public>\n");
        stringBuffer.append("\t\t</reporting_email>\n");
        stringBuffer.append("\t</reporting_emails>\n");
        stringBuffer.append("\t<supported_langs>\n");
        stringBuffer.append("\t\t<supported_lang lang_id=\"sr-Latn-BA\">\n");
        stringBuffer.append("\t\t\t<translation word=\"app_name\">CERT RS pomoćnik</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"navigation_drawer_open\" >Otvori navigaciju</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"navigation_drawer_close\" >Zatvori navigaciju</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"nav_header_title\" >CERT RS pomoćnik</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"nav_header_subtitle\" >Jednostavan pristup servisina CERTa</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"nav_header_desc\" >Navigacija aplikacije</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"action_settings\" >Podešavanja</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"menu_home\" >Početna</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"menu_about\" >O nama</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"menu_settings\" >Podešavanja</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"menu_feed\" >Feed</translation>\n");
        stringBuffer.append("\t\t\t<translation word=\"welcome_message\" >Dobrodošli</translation>\n");
        stringBuffer.append("\t\t</supported_lang>\n");
        stringBuffer.append("\t</supported_langs>\n");
        stringBuffer.append("</certapp>");
        xml=stringBuffer.toString();

    }

    String xml="";
}
