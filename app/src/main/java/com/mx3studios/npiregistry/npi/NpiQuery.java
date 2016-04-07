package com.mx3studios.npiregistry.npi;

import java.net.URL;

/**
 * Created by Mario on 2/28/2016.
 */
public class NpiQuery {
    private String firstName = "";
    private String lastName = "";
    private String npi = "";
    private String enumType = "";
    private String taxDesc = "";
    private String orgName = "";
    private String addrPurpose = "";
    private String city="";
    private String state="";
    private String zipCode = "";
    private String countryCode = "";
    private String limit = "200";
    private String skip = "";

    private String url = "https://npiregistry.cms.hhs.gov/api/?";
    public NpiQuery() {
    }

    public URL getApiUrl() {
        return buildUrl();
    }

    private URL buildUrl() {
        url += "first_name=" + firstName + "&";
        url += "last_name=" + lastName + "&";
        url += "number=" + npi + "&";
        url += "enumeration_type=" + enumType + "&";
        url += "taxonomy_description=" + taxDesc + "&";
        url += "address_purpose=" + addrPurpose + "&";
        url += "city=" + city + "&";
        url += "state=" + state + "&";
        url += "postal_code=" + zipCode + "&";
        url += "country_code=" + countryCode + "&";
        url += "limit=" + limit + "&";
        url += "skip=" + skip + "&";

        URL result = null;
        try {
            result = new URL(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public void setFirstName(String name) {
        firstName = name;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }
    public void setEnumType(String type) {
        enumType = type;
    }

    public void setTaxDesc(String desc) {
        this.taxDesc = desc;
    }
    public void setOrgName(String name) {
        orgName = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
