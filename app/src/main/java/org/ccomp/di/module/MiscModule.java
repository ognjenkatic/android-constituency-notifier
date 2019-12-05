package org.ccomp.di.module;


import android.util.Log;

import com.google.gson.Gson;

import org.apache.xerces.jaxp.SAXParserFactoryImpl;
import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.data.domain.settings.xml.AppSettingsHandler;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MiscModule {

    private static final String TAG = "MiscModule";

    @Provides
    @Singleton
    public SchemaFactory provideSchemaFactory() {
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

    @Provides
    @Singleton
    public AppSettingsXMLParser provideAppSettingsXMLParser(@NotNull SAXParser parser, @NotNull AppSettingsHandler handler) {
        return new AppSettingsXMLParser(parser,handler);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }



    @Provides
    public ErrorHandler provideXMLErrorHandler(){
        return new ErrorHandler() {
            private final String TAG = "XML Error Handler";

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                Log.e(TAG, "warning: ", exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                Log.e(TAG, "error: ", exception );
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                Log.e(TAG, "fatalError: ", exception );
                throw exception;
            }
        };
    }

    @Provides
    @Singleton
    public SAXParser provideSAXParser(@NotNull ErrorHandler errorHandler) {
        SAXParserFactory factory = new SAXParserFactoryImpl();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        SAXParser parser=null;
        //factory.setNamespaceAware(true);
        try {

            parser= factory.newSAXParser();
            parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty("http://apache.org/xml/properties/input-buffer-size",new Integer(8192));
            parser.getXMLReader().setErrorHandler(errorHandler);
        } catch (ParserConfigurationException | SAXException e) {
            Log.e(TAG, "provideSAXParser: ", e);
        }finally {
            return parser;
        }
    }

    @Provides
    @Singleton
    public AppSettingsHandler provideAppSettingsHandler() {
        return new AppSettingsHandler();
    }


}
