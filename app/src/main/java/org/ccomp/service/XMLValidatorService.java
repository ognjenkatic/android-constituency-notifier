package org.ccomp.service;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


@Singleton
public class XMLValidatorService {




    SchemaFactory factory;
    private static final String TAG = "XMLValidatorService";

    @Inject
    public XMLValidatorService(SchemaFactory factory) {
        this.factory = factory;
    }

    public boolean validate(String xmlFile , String xmlSchema){



        Source fileSource=new StreamSource(new StringReader(xmlFile));
        Source schemaSource=new StreamSource(new StringReader(xmlSchema));
        boolean val=true;
        Schema schema= null;
        try {
            schema = factory.newSchema(schemaSource);

        Validator xmlValidator=schema.newValidator();

        xmlValidator.validate(fileSource);
        } catch (SAXException | IOException e) {
            Log.e(TAG, "validate: ",e );
            val=false;
        }finally {
            return val;
        }


    }




}
