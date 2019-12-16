package org.ccomp.data.domain.lang;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "words")
public class Word {


    @PrimaryKey
    @NotNull
    String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Word() {

    }

    public Word(@NotNull String word) {
        this.word = word;
    }
}
