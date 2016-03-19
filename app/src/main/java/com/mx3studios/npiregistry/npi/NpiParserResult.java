package com.mx3studios.npiregistry.npi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mario on 3/5/2016.
 */
public class NpiParserResult {
    private ArrayList<NpiResult> list;
    private HashMap<String, String> errorMap;

    public NpiParserResult() {
        list = new ArrayList<>();
        errorMap = new HashMap<>();
    }

    public void setResultList(ArrayList<NpiResult> l) {
        list = l;
    }

    public ArrayList<NpiResult> getResultList() {
        return list == null ? new ArrayList<NpiResult>() : list;
    }

    public void setErrorMap(HashMap<String, String> map) {
        errorMap = map;
    }

    public HashMap<String, String> getErrorMap() {
        return errorMap;
    }
}
