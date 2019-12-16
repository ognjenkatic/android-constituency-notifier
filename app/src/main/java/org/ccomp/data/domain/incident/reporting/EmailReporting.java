package org.ccomp.data.domain.incident.reporting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.ccomp.data.domain.settings.TLP;
import org.jetbrains.annotations.NotNull;


@Entity(tableName = "email_reporting_settings")
public class EmailReporting extends Reporting {

    @PrimaryKey
    @NotNull
    private String address;
    @ColumnInfo(name = "default_tlp")
    private TLP defaultTLP;
    @ColumnInfo(name = "pgp_id")
    private String pgpId;
    @ColumnInfo(name = "pgp_fingerprint")
    private String pgpFingerprint;
    @ColumnInfo(name = "pgp_key")
    private String pgpKey;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TLP getDefaultTLP() {
        return defaultTLP;
    }

    public void setDefaultTLP(TLP defaultTLP) {
        this.defaultTLP = defaultTLP;
    }

    public String getPgpId() {
        return pgpId;
    }

    public void setPgpId(String pgpId) {
        this.pgpId = pgpId;
    }

    public String getPgpFingerprint() {
        return pgpFingerprint;
    }

    public void setPgpFingerprint(String pgpFingerprint) {
        this.pgpFingerprint = pgpFingerprint;
    }

    public String getPgpKey() {
        return pgpKey;
    }

    public void setPgpKey(String pgpKey) {
        this.pgpKey = pgpKey;
    }


}
