package com.mx3studios.npiregistry.npi;

/**
 * Created by Mario on 3/2/2016.
 */
public class NpiIdentifier {

    private String code = "";
    private String desc = "";
    private String identifier = "";
    private String issuer = "";
    private String state = "";

    public NpiIdentifier() {

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
