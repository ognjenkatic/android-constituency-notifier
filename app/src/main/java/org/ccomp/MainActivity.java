package org.ccomp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.ccomp.data.domain.lang.Language;
import org.ccomp.data.domain.lang.Restring;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.network.Resource;
import org.ccomp.ui.ViewTranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ccomp.service.feed.FeedFetchWorker;
import org.ccomp.service.notification.NotificationService;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


import org.ccomp.ui.home.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Inject
    public NotificationService notificationService;

    @Inject ViewTranslator viewTranslator;

    LiveData<Resource<AppSettings>> appSettings;
    List<MenuItem> menuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        AppController appController = (AppController) getApplication();
        appSettings=appController.getAppSettings();
        appSettings.observe(this, (value) -> {
            if (value != null && value.data != null) {
                Language defaultLang = value.data.getDefaultLang();
                if(defaultLang!=null) {
                    viewTranslator.getRestring().setLanguage(defaultLang);
                    viewTranslator.translate(drawer);
                    viewTranslator.translate(navigationView);
                    if (menuItems != null && menuItems.size() > 0)
                        for (MenuItem menuItem : menuItems) {
                            viewTranslator.translate(menuItem);
                        }
                }

            }
        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        notificationService.createNotificationChannel();
        /*
        Constraints consts = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        Data inputData = new Data.Builder().putInt("key", 2).build();
        // we then retrieve it inside the NotifyWorker with:
        // final int DBEventID = getInputData().getInt(DBEventIDTag, ERROR_VALUE);



        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(FeedFetchWorker.class)
                .setConstraints(consts)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build();




        WorkManager.getInstance(getApplicationContext()).enqueueUniqueWork("name1", ExistingWorkPolicy.REPLACE, notificationWork);*/
        navigationView.post(() -> {
            if (navigationView.getMenu() != null && navigationView.getMenu().size() > 0) {
                for (int i = 0; i < navigationView.getMenu().size(); i++) {
                    menuItems.add(navigationView.getMenu().getItem(i));
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        for (int i = 0; i < menu.size(); i++) {
            menuItems.add(menu.getItem(i));
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
