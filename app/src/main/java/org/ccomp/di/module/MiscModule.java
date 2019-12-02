package org.ccomp.di.module;


import com.google.gson.Gson;

import org.ccomp.data.domain.settings.AppSettingsXMLParser;

import javax.inject.Singleton;
import javax.xml.XMLConstants;
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


}
