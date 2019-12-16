package org.ccomp.ui;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.ccomp.R;
import org.ccomp.data.domain.lang.Restring;

import javax.inject.Inject;

public class ViewTranslator {



    Restring restring;

    @Inject
    public ViewTranslator(Restring restring) {
        this.restring = restring;
    }

    public void translate(View view) {


        ImageView headerImageView = view.findViewById(R.id.header_image);
        if (headerImageView != null)
            headerImageView.setContentDescription(restring.getTranslateOrDefault(R.string.nav_header_desc));

        TextView headerTitle = view.findViewById(R.id.header_title);
        if (headerTitle != null)
            headerTitle.setText(restring.getTranslateOrDefault(R.string.nav_header_title));

        TextView headerTextView = view.findViewById(R.id.header_subtitle);
        if (headerTextView != null)
            headerTextView.setText(restring.getTranslateOrDefault(R.string.nav_header_subtitle));


        Button homeButton = view.findViewById(R.id.button_home);
        if (homeButton != null)
            homeButton.setText(restring.getTranslateOrDefault(R.string.nav_header_subtitle));

    }

    public void translate(Fragment fragment) {

        switch (fragment.getId()) {
            case R.layout.fragment_home: {
                ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setTitle(restring.getTranslateOrDefault(R.string.menu_home));
            }
            break;
            case R.layout.fragment_feed: {
                ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setTitle(restring.getTranslateOrDefault(R.string.menu_feed));
            }
        }
    }

    public void translate(MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.action_settings: {
                menuItem.setTitle(restring.getTranslateOrDefault(R.string.action_settings));
            }
            break;
            case R.id.nav_home: {
                menuItem.setTitle(restring.getTranslateOrDefault(R.string.menu_home));
            }
            break;
            case R.id.nav_feed: {
                menuItem.setTitle(restring.getTranslateOrDefault(R.string.menu_feed));
            }
            break;

            case R.id.nav_about: {
                menuItem.setTitle(restring.getTranslateOrDefault(R.string.menu_about));
            }
            break;
            case R.id.nav_settings: {
                String translation = restring.getTranslateOrDefault(R.string.menu_settings);
                menuItem.setTitle(translation);
            }
            break;


        }


    }

    public Restring getRestring() {
        return restring;
    }

    public void setRestring(Restring restring) {
        this.restring = restring;
    }
}
