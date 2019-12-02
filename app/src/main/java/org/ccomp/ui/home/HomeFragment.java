package org.ccomp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import org.ccomp.R;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.feed.FeedViewModel;

import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;
    private TextView textHome;
    private Button homeButton;
    @Inject
    Gson gson;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textHome = root.findViewById(R.id.text_home);
        homeButton=root.findViewById(R.id.button);
        init();
        return root;
    }

    public void init() {
        homeViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);


        /*homeViewModel.getAllEmails().observe(this, (all) -> {


            textHome.setText(gson.toJson(all));

        });

        homeViewModel.getAllLang().observe(this,(all)->{

            textHome.setText(gson.toJson(all));
        });


         */
        homeViewModel.getAppSettings().observe(this,(appSettings -> {
            textHome.setText(gson.toJson(appSettings.getProperties()));
        }));


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textHome.setText(s);
            }
        });

        homeButton.setOnClickListener(this::buttonOnClick);

    }


    public void buttonOnClick(View view) {
        Toast.makeText(view.getContext(), "Bla bla bla", 10).show();
        EmailReporting emailReporting = homeViewModel.getAllEmails().getValue().data.get(0);
        Random rand = new Random();
        emailReporting.setPgpKey("Potpis " + rand.nextInt(10));
        homeViewModel.save(emailReporting);


    }


}

