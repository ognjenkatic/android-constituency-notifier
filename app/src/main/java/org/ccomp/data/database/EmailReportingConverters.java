package org.ccomp.data.database;

import android.util.Log;

import androidx.room.TypeConverter;

import org.ccomp.data.domain.settings.TLP;

public class EmailReportingConverters {


//        IncidentCategoryDAO incidentCategoryDAO= EmailReportingDatabase.getInstance().incidentCategoryDAO();

        @TypeConverter
        public TLP tlpFromString(String s){

            try{
                TLP tlp=TLP.valueOf(s);
                return tlp;
            }catch (Exception e){
                Log.println(Log.ERROR,"Enumeration Conversion Error",e.getMessage());
            }
            return TLP.UNKNOWN;

        }

        @TypeConverter
        public String tlpToString(TLP tlp){
            return tlp.toString();
        }





}
