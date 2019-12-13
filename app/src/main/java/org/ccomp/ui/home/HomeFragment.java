package org.ccomp.ui.home;

import android.content.ClipData;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import org.ccomp.MainActivity;
import org.ccomp.R;
import org.ccomp.data.domain.incident.reporting.EmailReporting;
import org.ccomp.data.domain.lang.Restring;
import org.ccomp.data.domain.lang.StringsEnum;
import org.ccomp.data.domain.settings.AppSettings;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.Status;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.ViewTranslator;
import org.ccomp.ui.feed.FeedViewModel;
import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;
    private TextView textHome;
    private ImageView imageView;
    private Button homeButton;
    @Inject
    Gson gson;
    @Inject
    Restring restring;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textHome = root.findViewById(R.id.text_home);
        imageView=root.findViewById(R.id.image_home);
        homeButton=root.findViewById(R.id.button_home);
        homeViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        init();
        return root;
    }

    public void init() {

        homeViewModel.getAppSettings().observe(this,(value) -> {
            if(value!=null && value.data!=null && value.status== Status.SUCCESS) {
                Map<AppSettingsOption, AppSettingsProperty> properties = value.data.getProperties();
                String s=gson.toJson(homeViewModel.getAppSettings().getValue().data.getProperties());
                if(s!=null && s.length()>101){
                    s=s.substring(0,100);
                }
                textHome.setText(s);
                restring.setLanguage(value.data.getDefaultLang());
                ViewTranslator viewTranslator= new ViewTranslator();
                viewTranslator.translate(this,restring);
                viewTranslator.translate(getView(),restring);
            }


        });


       homeViewModel.getmText().observe(this, (value)->{
           textHome.setText(value);
       });



        homeButton.setOnClickListener(this::buttonOnClick);

    }


    public void buttonOnClick(View view) {
        Toast.makeText(view.getContext(), "Bla bla bla", Toast.LENGTH_LONG).show();



    }







}

