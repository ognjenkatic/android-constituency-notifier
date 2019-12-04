package org.ccomp.di.module;


import com.google.gson.Gson;

import org.ccomp.data.domain.settings.AppSettingsXMLParser;
import org.ccomp.data.domain.settings.xml.AppSettingsHandler;

import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MiscModule {

    @Provides
    @Singleton
    public SchemaFactory provideSchemaFactory(){
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

    @Provides
    @Singleton
    public AppSettingsXMLParser provideAppSettingsXMLParser(){
        return new AppSettingsXMLParser();
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }


    @Provides
    @Singleton
    public SAXParser provideSAXParser(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        return factory.newSAXParser();
    }

    @Provides
    @Singleton
    public AppSettingsHandler provideAppSettingsHandler(){
        return new AppSettingsHandler();
    }


}
