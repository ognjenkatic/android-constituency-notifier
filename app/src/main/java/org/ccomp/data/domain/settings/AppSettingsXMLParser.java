package org.ccomp.data.domain.settings;

import android.util.Log;

import org.ccomp.data.domain.settings.xml.AppSettingsHandler;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.inject.Inject;
import javax.xml.parsers.SAXParser;

public class AppSettingsXMLParser {


    private static final String TAG = "AppSettingsXMLParser";


    SAXParser parser;
    AppSettingsHandler handler = new AppSettingsHandler();

    public AppSettingsXMLParser() {
    }


    @Inject
    public AppSettingsXMLParser(SAXParser parser, AppSettingsHandler handler) {
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
        }
        return appSettings;
    }

    public SAXParser getParser() {
        return parser;
    }

    public void setParser(SAXParser parser) {
        this.parser = parser;
    }

    public AppSettingsHandler getHandler() {
        return handler;
    }

    public void setHandler(AppSettingsHandler handler) {
        this.handler = handler;
    }
}
