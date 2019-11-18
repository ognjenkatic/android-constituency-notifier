package org.ccomp.data.domain.settings;

class MOTD {

    private String value;
    private boolean url;


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
