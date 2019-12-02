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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppSettingsProperty)) return false;
        AppSettingsProperty that = (AppSettingsProperty) o;
        return getOptionName() == that.getOptionName() &&
                Objects.equals(getOptionValue(), that.getOptionValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOptionName(), getOptionValue());
    }
}
