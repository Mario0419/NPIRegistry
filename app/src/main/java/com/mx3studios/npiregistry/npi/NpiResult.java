package com.mx3studios.npiregistry.npi;

import android.database.Cursor;

import com.mx3studios.npiregistry.npiDatabase.NpiReaderContract;

import java.util.ArrayList;

/**
 * Created by Mario on 2/28/2016.
 */
public class NpiResult {

    private ArrayList<NpiAddress> addresses;
    private ArrayList<NpiIdentifier> identifiers;
    private ArrayList<NpiTaxonomy> taxonomies;
    private ArrayList<String> otherNames;
    private NpiBasicInfo basicInfo;
    private String enumType = "";
    private Integer lastEpoch;
    private Integer npi;
    private boolean favorite;

    public NpiResult() {

    }

    public NpiResult(Cursor c) {
        setCursorValues(c);
    }

    private void setCursorValues(Cursor c) {
        setNpi(Integer.parseInt(c.getString(0)));
        basicInfo.setFirstName(c.getString(1));
        basicInfo.setLastName(c.getString(2));
        if(basicInfo.getLastName().isEmpty()) {
            basicInfo.setLastName(c.getString(3));
        }
        taxonomies.get(0).setDesc(c.getString(4));
        taxonomies.get(0).setCode(c.getString(5));
        addresses.get(0).setAddress1(c.getString(6));
        addresses.get(0).setCity(c.getString(7));
        addresses.get(0).setState(c.getString(8));
        addresses.get(0).setCountryName(c.getString(9));
        addresses.get(0).setCountryCode(c.getString(10));
        addresses.get(0).setPostalCode(c.getString(11));
        addresses.get(0).setPhone(c.getString(12));
        enumType = c.getString(13);
        basicInfo.setLastUpdated(c.getString(14));

    }

    public ArrayList<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(ArrayList<String> otherNames) {
        this.otherNames = otherNames;
    }

    public NpiAddress getAddressOfIndex(int index) {
        return addresses.get(index);
    }

    public ArrayList<NpiAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<NpiAddress> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<NpiIdentifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<NpiIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<NpiTaxonomy> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(ArrayList<NpiTaxonomy> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public NpiBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(NpiBasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getEnumType() {
        return enumType;
    }

    public void setEnumType(String enumType) {
        this.enumType = enumType;
    }

    public Integer getLastEpoch() {
        return lastEpoch;
    }

    public void setLastEpoch(Integer lastEpoch) {
        this.lastEpoch = lastEpoch;
    }

    public Integer getNpi() {
        return npi;
    }

    public void setNpi(Integer npi) {
        this.npi = npi;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean getFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return (basicInfo.getFirstName() + " " + basicInfo.getLastName());
    }
}

