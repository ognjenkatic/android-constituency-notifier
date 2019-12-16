package org.ccomp.data.database;

import androidx.room.TypeConverter;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

public class URLConverter {

    @TypeConverter
    public static URL toURL(String url){

        URL parsedUrl = null;

        try{
            parsedUrl = new URL(url);
        } catch (MalformedURLException mex){

        }

        return parsedUrl;
    }

    @TypeConverter
    public static String fromURL(URL url){
        return url.toString();
    }
}