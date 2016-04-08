package com.mx3studios.npiregistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mario on 4/7/2016.
 */
public class States {

    public static final HashMap<String, String> statemap;

    static {
        statemap = new HashMap<>();
        statemap.put("State", " ");
        statemap.put("ALABAMA", "AL");
        statemap.put("ALASKA", "AK");
        statemap.put("ARIZONA", "AZ");
        statemap.put("ARKANSAS", "AR");
        statemap.put("CALIFORNIA", "CA");
        statemap.put("COLORADO", "CO");
        statemap.put("CONNECTICUT", "CT");
        statemap.put("DELAWARE", "DE");
        statemap.put("FLORIDA", "FL");
        statemap.put("GEORGIA", "GA");
        statemap.put("HAWAII", "HI");
        statemap.put("IDAHO", "ID");
        statemap.put("ILLINOIS", "IL");
        statemap.put("INDIANA", "IN");
        statemap.put("IOWA", "IA");
        statemap.put("KANSAS", "KS");
        statemap.put("KENTUCKY", "KY");
        statemap.put("LOUISIANA", "LA");
        statemap.put("MAINE", "ME");
        statemap.put("MARYLAND", "MD");
        statemap.put("MASSACHUSETTS", "MA");
        statemap.put("MICHIGAN", "MI");
        statemap.put("MINNESOTA", "MN");
        statemap.put("MISSISSIPPI", "MS");
        statemap.put("MISSOURI", "MO");
        statemap.put("MONTANA", "MT");
        statemap.put("NEBRASKA", "NE");
        statemap.put("NEVADA", "NV");
        statemap.put("NEW HAMPSHIRE", "NH");
        statemap.put("NEW JERSEY", "NJ");
        statemap.put("NEW MEXICO", "NM");
        statemap.put("NEW YORK", "NY");
        statemap.put("NORTH CAROLINA", "NC");
        statemap.put("NORTH DAKOTA", "ND");
        statemap.put("OHIO", "OH");
        statemap.put("OKLAHOMA", "OK");
        statemap.put("OREGON", "OR");
        statemap.put("PENNSYLVANIA", "PA");
        statemap.put("RHODE ISLAND", "RI");
        statemap.put("SOUTH CAROLINA", "SC");
        statemap.put("SOUTH DAKOTA", "SD");
        statemap.put("TENNESSEE", "TN");
        statemap.put("TEXAS", "TX");
        statemap.put("UTAH", "UT");
        statemap.put("VERMONT", "VT");
        statemap.put("VIRGINIA", "VA");
        statemap.put("WASHINGTON", "WA");
        statemap.put("WEST VIRGINIA", "WV");
        statemap.put("WISCONSIN", "WI");
        statemap.put("WYOMING", "WY");
    }

    public static List<String> asList() {
        ArrayList<String> list = new ArrayList<>(statemap.keySet());
        Collections.sort(list);
        return list;
    }

}
