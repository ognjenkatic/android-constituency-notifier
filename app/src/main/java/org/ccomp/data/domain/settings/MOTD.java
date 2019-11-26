package org.ccomp.data.domain.settings;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

@Entity(tableName = "motd")
public class MOTD {


    private Timestamp timestamp;
    @PrimaryKey
    @NotNull
    private String value;
    private boolean url;


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }
}
