package com.mx3studios.npiregistry;

/**
 * Created by Mario on 3/2/2016.
 */
public class NpiBasicInfo {

    private String credential = "";
    private String enumDate = "";
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private String gender = "";
    private String lastUpdated = "";
    private String fullName = "";
    private String soleProprietor = "";
    private String status = "";
    private boolean isOrganization = false;

    public NpiBasicInfo() {

    }

    public void setIsOrganization(boolean isOrg) {
        isOrganization = isOrg;
    }

    public boolean isOrganization() {
        return isOrganization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEnumDate() {
        return enumDate;
    }

    public void setEnumDate(String enumDate) {
        this.enumDate = enumDate;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getFullName() {
        if(isOrganization) {
            fullName = getLastName();
        } else {
            fullName = getCredential() + " " + getFirstName() + " " + getLastName();
        }
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSoleProprietor() {
        return soleProprietor;
    }

    public void setSoleProprietor(String soleProprietor) {
        this.soleProprietor = soleProprietor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
