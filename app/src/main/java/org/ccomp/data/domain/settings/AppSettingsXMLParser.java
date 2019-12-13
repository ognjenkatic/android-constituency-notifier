package org.ccomp.data.domain.settings;

import android.util.Log;

import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.inject.Inject;
import javax.xml.parsers.SAXParser;

public class AppSettingsXMLParser {


    private static final String TAG = "AppSettingsXMLParser";


    SAXParser parser;
    TLP.AppSettingsHandler handler;


    @Inject
    public AppSettingsXMLParser(SAXParser parser, TLP.AppSettingsHandler handler) {
        this.parser = parser;
        this.handler = handler;
    }

    public AppSettings parseString(String input) {
        AppSettings appSettings = null;
        try {

            InputSource source = new InputSource(new StringReader(input));
            parser.parse(source, handler);
            appSettings = handler.getAppSettings();
        } catch (Exception ex) {
            Log.e(TAG, "parse: ", ex);
        }
        return appSettings;
    }

    public AppSettings parse(String url) {
        AppSettings appSettings = null;
        try {
            parser.parse(url, handler);
            appSettings = handler.getAppSettings();

        } catch (Exception ex) {
            Log.e(TAG, "parse: ", ex);
            boolean b=true;
        }
        return appSettings;
    }

    public SAXParser getParser() {
        return parser;
    }

    public void setParser(SAXParser parser) {
        this.parser = parser;
    }

    public TLP.AppSettingsHandler getHandler() {
        return handler;
    }

    public void setHandler(TLP.AppSettingsHandler handler) {
        this.handler = handler;
    }
}
