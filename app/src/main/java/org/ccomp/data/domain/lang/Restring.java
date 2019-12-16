package org.ccomp.data.domain.lang;

import android.content.Context;
import android.util.Log;

import org.ccomp.R;

import java.lang.reflect.Field;
import java.util.Map;

import javax.inject.Inject;

public class Restring {


    Context context;
    Language language;

    @Inject
    public Restring(Context context) {
        this.context = context;
    }


    private static final String TAG = "Restring";

    public String getTranslateOrDefault(int rid) {
        return getTranslateOrDefault(getName(rid));
    }

    public String getTranslateOrDefault(String id) {

        String translation = null;
        try {
            if(language!=null) {
                Map<String, String> dictionary = language.getDictionary();
                if (dictionary.containsKey(id)) {
                    translation = dictionary.get(id);
                } else {
                    translation = getDefaultStringByRId(getRId(id));
                }
            }else{
                translation = getDefaultStringByRId(getRId(id));
            }

        } catch (Exception ex) {
            Log.e(TAG, "getTranslateOrDefault: ", ex);
        }


        return translation;

    }

    public int getRId(String id) {
        return context.getResources().getIdentifier(id, "string", context.getPackageName());
    }

    public String getDefaultStringByRId(int rId) {
        return context.getResources().getString(rId);
    }

    public String getName(int rId) {
        Class rClass = R.string.class;
        for (Field field : rClass.getFields()) {
            try {
                if (rId == field.getInt(null)) {
                    return field.getName();
                }
            } catch (IllegalAccessException ex) {
                Log.e(TAG, "getName: ", ex);
            }
        }
        return null;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
