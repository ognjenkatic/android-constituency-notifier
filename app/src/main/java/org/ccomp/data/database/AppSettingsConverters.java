package org.ccomp.data.database;

import android.util.Log;

import androidx.room.TypeConverter;

import org.ccomp.data.domain.settings.AppSettingsOption;

public class AppSettingsConverters {


    @TypeConverter
    public AppSettingsOption appSettingsOptionFromString(String s){


            AppSettingsOption option=AppSettingsOption.valueOf(s);
            return option;



    }

    @TypeConverter
    public String appSettingsOptionToString(AppSettingsOption appSettingsOption){
        return appSettingsOption.toString();
    }
}
