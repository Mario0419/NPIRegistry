package com.mx3studios.npiregistry.npi;

/**
 * Created by Mario on 3/2/2016.
 */
public class NpiTaxonomy {

    private String code = "";
    private String desc = "";
    private String license = "";
    private boolean primary = false;
    private String state = "";

    public NpiTaxonomy() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
