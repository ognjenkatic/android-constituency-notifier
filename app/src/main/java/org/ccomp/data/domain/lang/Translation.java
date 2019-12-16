package org.ccomp.data.domain.lang;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import org.jetbrains.annotations.NotNull;


@Entity(tableName = "translation", primaryKeys = {"lang_id","word"})
public class Translation {


    @ColumnInfo(name = "lang_id")
    @ForeignKey(entity = Language.class, parentColumns = {"lang_id"}, childColumns = {"lang_id"}, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    @NotNull
    private String langId;
    @ColumnInfo(name = "word")
    @ForeignKey(entity = Word.class, parentColumns = {"word"}, childColumns = {"word"}, onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    @NotNull
    private String word;
    private String value;

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
