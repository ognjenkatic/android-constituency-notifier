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

      /*  @TypeConverter
        public List<IncidentCategory> stringsToIncidentCategories(List<String> keys){
            List<IncidentCategory> categories=new ArrayList<>();
            for(String key:keys){
                IncidentCategory category=incidentCategoryDAO.get(key);
                if(category!=null){
                    categories.add(category);
                }
            }

            if(categories.isEmpty()){
                return null;

            }else {
                return categories;
            }
        }
        @TypeConverter
        public List<String> incidentCategoriesToStrings(List<IncidentCategory> incidentCategories){
            List<String> keys=new ArrayList<>();
            for(IncidentCategory category:incidentCategories){
                try{

                    incidentCategoryDAO.save(category);
                    keys.add(category.getId());
                }catch (Exception e){
                    Log.println(Log.ERROR,"Room exception",e.getMessage());
                }
            }
            if(keys.isEmpty()){
                return null;
            }else{
                return keys;
            }
        }
*/



}
