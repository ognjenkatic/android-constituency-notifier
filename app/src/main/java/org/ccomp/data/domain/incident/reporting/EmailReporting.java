package org.ccomp.data.domain.incident.reporting;

import org.ccomp.data.domain.settings.TLP;

public class EmailReporting extends Reporting {

    private String address;
    private TLP deafultTLP;
    private String pgpId;
    private String pgpFingerpring;
    private String pgpKey;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TLP getDeafultTLP() {
        return deafultTLP;
    }

    public void setDeafultTLP(TLP deafultTLP) {
        this.deafultTLP = deafultTLP;
    }

    public String getPgpId() {
        return pgpId;
    }

    public void setPgpId(String pgpId) {
        this.pgpId = pgpId;
    }

    public String getPgpFingerpring() {
        return pgpFingerpring;
    }

    public void setPgpFingerpring(String pgpFingerpring) {
        this.pgpFingerpring = pgpFingerpring;
    }

    public String getPgpKey() {
        return pgpKey;
    }

    public void setPgpKey(String pgpKey) {
        this.pgpKey = pgpKey;
    }
}
