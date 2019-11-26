package org.ccomp.data.database;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.ccomp.data.database.dao.EmailReportingDAO;
import org.ccomp.data.database.dao.IncidentCategoryDAO;
import org.ccomp.data.database.dao.mapping.EmailReportingIncidentCategoryMapping;

import org.ccomp.data.domain.incident.IncidentCategory;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.settings.TLP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Database(entities = {EmailReporting.class,IncidentCategory.class, EmailReportingIncidentCategoryMapping.class}, version = 1)
@TypeConverters({EmailReportingConverters.class})
public abstract class EmailReportingDatabase extends RoomDatabase {


    private static EmailReportingDatabase instance;





    public static synchronized  EmailReportingDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), EmailReportingDatabase.class, "email_reporting_settings")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }


    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {

        private EmailReportingDAO emailReportingDAO;

        public PopulateDBAsyncTask(EmailReportingDatabase db) {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            EmailReporting reporting=new EmailReporting();
            reporting.setAddress("cert@certrs.org");
            reporting.setDefaultTLP(TLP.AMBER);
            reporting.setPgpId("0xEE03B7F3");
            reporting.setPgpFingerprint("6F1F 1A61 EBA9 50A7 E3FC 5C03 1B5A 3A3B EE03 B7F3");
            reporting.setPgpKey("Potpis");
            List<IncidentCategory> categories=new ArrayList<>();
            for(int i=0;i<5;i++){
                IncidentCategory category=new IncidentCategory();
                category.setId("ID"+i);
                category.setClassDescription("ClassDescription");
                category.setClassName("Class");
                category.setDescription("Description");
                category.setTypeDescription("TypeDescription");
                category.setTypeName("TypeName");
                categories.add(category);

            }
            reporting.setIncidentCategories(categories);

            emailReportingDAO.save(reporting);

            return null;
        }
    }



}
