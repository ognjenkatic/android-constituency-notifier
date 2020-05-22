package org.ccomp.ui.reporting;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
      //  reportingButton.setOnClickListener(this::buttonOnClick);
        reportingButton.setOnClickListener(this::buttonSend);
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

    public void buttonSend(View view){
        String xml="<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\t\tincident_type=\"resources-misuse\" incident_class=\"fraud\">\n" +
                "\t\t\t\t<incident_type_description>Use of institutional resources for purposes other than those intended.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Loss of property caused with fraudulent or dishonest intent of procuring, without right, an economic benefit for oneself or for another person.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\t\tincident_type=\"false-representation\" incident_class=\"fraud\">\n" +
                "\t\t\t\t<incident_type_description>Unauthorised use of the name of an institution.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Loss of property caused with fraudulent or dishonest intent of procuring, without right, an economic benefit for oneself or for another person.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\t\tincident_type=\"spam\" incident_class=\"abusive-content\">\n" +
                "\t\t\t\t<incident_type_description>Sending an unusually large quantity of email messages / Unsolicited or unwanted email message sent to the recipient.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Sending SPAM messages / Distribution and sharing of copyright protected content / Dissemination of content forbidden by law.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\t\tincident_type=\"copyright\" incident_class=\"abusive-content\">\n" +
                "\t\t\t\t<incident_type_description>Unauthorised distribution or sharing of content protected by Copyright and related rights.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Sending SPAM messages / Distribution and sharing of copyright protected content / Dissemination of content forbidden by law.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"ID5\"\n" +
                "\t\t\t\tincident_type=\"cse-racism-violence-incitement\"\n" +
                "\t\t\t\tincident_class=\"abusive-content\">\n" +
                "\t\t\t\t<incident_type_description>Distribution or sharing of illegal content such as child sexual exploitation material, racism, xenophobia, etc.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Sending SPAM messages / Distribution and sharing of copyright protected content / Dissemination of content forbidden by law.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"unclassified-incident-other\"\n" +
                "\t\t\t\tincident_type=\"unclassified-incident\" incident_class=\"other\">\n" +
                "\t\t\t\t<incident_type_description>Incidents which do not fit the existing classification, acting as an indicator for the classification’s update.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Incidents not classified in the existing classification.</incident_class_description>\n" +
                "\t\t\t</incident_category>\n" +
                "\t\t\t<incident_category incident_category_id=\"undetermined-incident-other\"\n" +
                "\t\t\t\tincident_type=\"undetermined-incident\" incident_class=\"other\">\n" +
                "\t\t\t\t<incident_type_description>Unprocessed incidents which have remained undetermined from the beginning.</incident_type_description>\n" +
                "\t\t\t\t<incident_class_description>Incidents not classified in the existing classification.</incident_class_description>\n" +
                "\t\t\t</incident_category>";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","D.Kukic@mnrvoid.vladars.net", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[ALERT]Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        Uri attachment= null;
        try {
            attachment = Uri.fromFile(createCachedFile(getContext(),"Naziv.xml",xml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        emailIntent.putExtra(Intent.EXTRA_STREAM, attachment);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));


    }

    public  File createCachedFile(Context context, String fileName,
                                        String content) throws IOException {


        File cacheFile = new File(context.getCacheDir() + File.separator
                + fileName);
        cacheFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(cacheFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
        PrintWriter pw = new PrintWriter(osw);

        pw.println(content);

        pw.flush();
        pw.close();
        return  cacheFile;
    }

}
