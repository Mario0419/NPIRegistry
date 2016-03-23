package com.mx3studios.npiregistry.npi;

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

