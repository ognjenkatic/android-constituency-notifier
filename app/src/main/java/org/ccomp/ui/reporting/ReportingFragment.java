package org.ccomp.ui.reporting;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ccomp.R;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.Status;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.ViewTranslator;
import org.ccomp.ui.home.HomeViewModel;

import java.util.Map;

import javax.inject.Inject;

public class ReportingFragment extends Fragment {


    @Inject
    ViewModelFactory viewModelFactory;
    private ReportingViewModel reportingViewModel;
    Button reportingButton;
    TextView reportingText;

    @Inject
    ViewTranslator viewTranslator;

    public static ReportingFragment newInstance() {
        return new ReportingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root=inflater.inflate(R.layout.fragment_reporting, container, false);
        reportingButton=root.findViewById(R.id.reporting_button);
        reportingButton.setOnClickListener(this::buttonOnClick);
        reportingText=root.findViewById(R.id.reporting_text);
        reportingViewModel= ViewModelProviders.of(this, viewModelFactory).get(ReportingViewModel.class);
        init();
        return root;
    }



    public void init() {

        /*homeViewModel.getAppSettings().observe(this, (value) -> {
            if (value != null && value.data != null && value.status == Status.SUCCESS) {
                Map<AppSettingsOption, AppSettingsProperty> properties = value.data.getProperties();
                String s = gson.toJson(homeViewModel.getAppSettings().getValue().data.getProperties());
                if (s != null && s.length() > 101) {
                    s = s.substring(0, 100);
                }
                textHome.setText(s);
                if(value.data.getDefaultLang()!=null) {
                    viewTranslator.getRestring().setLanguage(value.data.getDefaultLang());
                    viewTranslator.translate(getView());
                }
            }


        });




        homeViewModel.getmText().observe(this, (value) -> {
            textHome.setText(value);
        });

         */




    }


    public void buttonOnClick(View view) {
        Toast.makeText(view.getContext(), "Bla bla bla", Toast.LENGTH_LONG).show();


    }

}
