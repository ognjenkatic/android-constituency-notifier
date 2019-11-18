package org.ccomp.data.domain.settings.lang;

import java.util.List;

public class Language {

    private String langId;
    private List<Word> words;

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
