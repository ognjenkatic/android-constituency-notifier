package org.ccomp.data.domain.settings.lang;

public class Word {


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
