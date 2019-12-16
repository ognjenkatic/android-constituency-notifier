package org.ccomp.data.database;

import android.util.Log;

import androidx.room.TypeConverter;

import org.ccomp.data.domain.settings.TLP;

public class EmailReportingConverters {


    private static final String TAG = "EmailReportingConverter";


        @TypeConverter
        public TLP tlpFromString(String s){

            try{
                TLP tlp=TLP.valueOf(s);
                return tlp;
            }catch (Exception e){
                Log.e(TAG, "tlpFromString: ",e );
            }
            return TLP.UNKNOWN;

        }

        @TypeConverter
        public String tlpToString(TLP tlp){
            return tlp.toString();
        }





}
