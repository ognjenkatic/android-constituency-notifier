package org.ccomp.data.database;

import android.util.Log;

import androidx.room.TypeConverter;

import org.ccomp.data.database.EmailReportingDatabase;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.settings.TLP;

import java.util.ArrayList;
import java.util.List;

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
