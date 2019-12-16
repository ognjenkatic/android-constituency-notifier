package org.ccomp.data.domain.lang;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity(tableName = "language")
public class Language {

    @PrimaryKey
    @ColumnInfo(name = "lang_id")
    @NotNull
    private String langId;
    private String name;
    private String description;

    @Ignore
    private List<Translation> translations;

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Map<String, String> getDictionary() {
        Map<String, String> dictionary = new HashMap<>();
        for (Translation translation : translations) {
            if (dictionary.containsKey(translation.getWord())) {
                dictionary.remove(translation.getWord());
            }
            dictionary.put(translation.getWord(), translation.getValue());

        }
        return dictionary;

    }
}
