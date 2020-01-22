package org.ccomp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import org.ccomp.R;
import org.ccomp.data.domain.settings.AppSettingsOption;
import org.ccomp.data.domain.settings.AppSettingsProperty;
import org.ccomp.data.network.Status;
import org.ccomp.factory.ViewModelFactory;
import org.ccomp.ui.ViewTranslator;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private HomeViewModel homeViewModel;
    @Inject
    Gson gson;

    @Inject
    ViewTranslator viewTranslator;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        return root;
    }




}

