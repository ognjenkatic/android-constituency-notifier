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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getAll().observe(this,(all)->{

            Gson gson=new Gson();
            textView.setText(gson.toJson(all));

        });
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button button=root.findViewById(R.id.button);
        button.setOnClickListener(this::buttonOnClick);
        return root;
    }

    public void buttonOnClick(View view){
        Toast.makeText(view.getContext(),"Bla bla bla",10).show();
        EmailReporting emailReporting=homeViewModel.getAll().getValue().get(0);
        emailReporting.setPgpKey("Potpis 2");
        homeViewModel.save(emailReporting);


    }
}

/*
* homeViewModel=new ViewModelProvider(this).get(HomeViewModel.class);

* */