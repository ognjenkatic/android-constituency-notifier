package org.ccomp.data.domain.settings;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity(tableName = "app_settings")
public class AppSettingsProperty {


    @PrimaryKey
    @ColumnInfo(name = "option_name")
            @NotNull
    AppSettingsOption optionName;
    @ColumnInfo(name="option_value")
    String optionValue;


    public AppSettingsProperty(AppSettingsOption optionName, String optionValue) {
        this.optionName = optionName;
        this.optionValue = optionValue;
    }

    public AppSettingsOption getOptionName() {
        return optionName;
    }

    public void setOptionName(AppSettingsOption optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((optionName == null) ? 0 : optionName.hashCode());
        result = prime * result + ((optionValue == null) ? 0 : optionValue.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppSettingsProperty other = (AppSettingsProperty) obj;
        if (optionName != other.optionName)
            return false;
        if (optionValue == null) {
            if (other.optionValue != null)
                return false;
        } else if (!optionValue.equals(other.optionValue))
            return false;
        return true;
    }
}
