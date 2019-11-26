package org.ccomp.data.domain.settings.lang;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "lang_words",primaryKeys = {"lang_id","key"})
public class Word {


    @ColumnInfo(name = "lang_id")
    @ForeignKey(entity = Language.class, parentColumns ={"lang_id"}, childColumns = {"lang_id"},onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    private String langId;
    private String key;
    private String value;

    public Word(){

    }

    public Word(String key,String value){
        this.key=key;
        this.value=value;
    }

    public Word(String langId,String key,String value){
        this.langId=langId;
        this.key=key;
        this.value=value;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
