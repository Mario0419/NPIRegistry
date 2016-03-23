package com.mx3studios.npiregistry.npi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.JsonReader;

import android.util.Log;

/**
 * Created by Mario on 3/2/2016.
 */
public class NpiParser {

    private URL url;
    private HttpURLConnection httpConn;
    private BufferedReader buffer;
    private JsonReader reader;
    private HashMap<String, String> errorMap;
    private ArrayList<NpiResult> list = null;
    private NpiParserResult result = null;

    public NpiParser(URL u) {
        url = u;
        try {
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            InputStream in = httpConn.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(in));
            reader = new JsonReader(buffer);
            list = new ArrayList<>();
            errorMap = new HashMap<>();
            result = new NpiParserResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public NpiParserResult getResults() throws Exception {
        reader.beginObject();
        while (reader.hasNext()) {
            String line = reader.nextName();
            switch (line) {
                case "result_count":
                    reader.nextInt();
                    break;
                case "results":
                    readList();
                    break;
                case "Errors":
                    readErrors();
                    break;
                default:
                    break;
            }
        }
        reader.endObject();
        return result;
    }

    private void readErrors() throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String entry = reader.nextName();
            String value = reader.nextString();
            Log.i("Error", "bub " + value);
            errorMap.put(entry, value);
        }
        reader.endObject();
        result.setErrorMap(errorMap);
    }

    private void readList() throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            NpiResult provider = new NpiResult();
            provider = readProvider();
            provider.getBasicInfo().setIsOrganization(!provider.getEnumType().equals("NPI-1"));
            list.add(provider);
        }
        reader.endArray();
        result.setResultList(list);
    }

    private NpiResult readProvider() throws IOException {
        NpiResult provider = new NpiResult();

        reader.beginObject();
        while (reader.hasNext()) {
            String entry = reader.nextName();
            switch (entry) {
                case "addresses":
                    provider.setAddresses(readAddresses());
                    break;
                case "basic":
                    provider.setBasicInfo(readBasicInfo());
                    break;
                case "created_epoch":
                    reader.nextInt();
                    break;
                case "enumeration_type":
                    provider.setEnumType(reader.nextString());
                    break;
                case "identifiers":
                    provider.setIdentifiers(readIdentifiers());
                    break;
                case "last_updated_epoch":
                    provider.setLastEpoch(reader.nextInt());
                    break;
                case "number":
                    provider.setNpi(reader.nextInt());
                    break;
                case "other_names":
                    provider.setOtherNames(readOtherNames());
                    break;
                case "taxonomies":
                    provider.setTaxonomies(readTaxonomies());
                    break;
                default:
                    break;
            }
        }
        reader.endObject();
        return provider;
    }

    private ArrayList<NpiTaxonomy> readTaxonomies() throws IOException {
        ArrayList<NpiTaxonomy> taxonomies = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            NpiTaxonomy taxonomy = new NpiTaxonomy();
            while (reader.hasNext()) {
                String entry = reader.nextName();
                switch (entry) {
                    case "primary":
                        reader.nextBoolean();
                        break;
                    case "desc":
                        taxonomy.setDesc(reader.nextString());
                        break;
                    case "code":
                        taxonomy.setCode(reader.nextString());
                        break;
                    default:
                        reader.nextString();
                        break;
                }
            }
            taxonomies.add(taxonomy);
            reader.endObject();
        }
        reader.endArray();

        return taxonomies;
    }

    private ArrayList<NpiIdentifier> readIdentifiers() throws IOException {
        ArrayList<NpiIdentifier> identifiers = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext()) {
                String entry = reader.nextName();
                switch (entry) {
                    default:
                        reader.nextString();
                        break;
                }
            }

            reader.endObject();
        }

        reader.endArray();
        return identifiers;
    }

    private ArrayList<String> readOtherNames() throws IOException {
        ArrayList<String> names = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext()) {
                String entry = reader.nextName();
                switch (entry) {
                    default:
                        reader.nextString();
                }
            }
            reader.endObject();
        }
        reader.endArray();

        return names;
    }

    private ArrayList<NpiAddress> readAddresses() throws IOException {
        ArrayList<NpiAddress> addresses = new ArrayList<NpiAddress>();
        reader.beginArray();
        while (reader.hasNext()) {
            NpiAddress address = new NpiAddress();
            reader.beginObject();
            while (reader.hasNext()) {
                String entry = reader.nextName();
                switch (entry) {
                    case "address_1":
                        address.setAddress1(reader.nextString());
                        break;
                    case "address_2":
                        address.setAddress2(reader.nextString());
                        break;
                    case "address_purpose":
                        address.setAddressPurpose(reader.nextString());
                        break;
                    case "address_type":
                        address.setAddressType(reader.nextString());
                        break;
                    case "city":
                        address.setCity(reader.nextString());
                        break;
                    case "country_code":
                        address.setCountryCode(reader.nextString());
                        break;
                    case "country_name":
                        address.setCountryName(reader.nextString());
                        break;
                    case "fax_number":
                        address.setFax(reader.nextString());
                        break;
                    case "postal_code":
                        address.setPostalCode(reader.nextString());
                        break;
                    case "state":
                        address.setState(reader.nextString());
                        break;
                    case "telephone_number":
                        address.setPhone(reader.nextString());
                        break;
                    default:
                        break;
                }
            }
            reader.endObject();
            addresses.add(address);
        }
        reader.endArray();
        return addresses;
    }

    private NpiBasicInfo readBasicInfo() throws IOException {
        NpiBasicInfo basicInfo = new NpiBasicInfo();

        reader.beginObject();
        while (reader.hasNext()) {
            String entry = reader.nextName();
            switch (entry) {
                case "replacement_npi":
                    reader.nextInt();
                    break;
                case "last_name":
                    basicInfo.setLastName(reader.nextString());
                    break;
                case "first_name":
                    basicInfo.setFirstName(reader.nextString());
                    break;
                case "middle_name":
                    basicInfo.setMiddleName(reader.nextString());
                    break;
                case "credential":
                    basicInfo.setCredential(reader.nextString());
                    break;
                case "organization_name":
                    basicInfo.setLastName(reader.nextString());
                    break;
                case "enumeration_date":
                    basicInfo.setEnumDate(reader.nextString());
                    break;
                case "gender":
                    basicInfo.setGender(reader.nextString());
                    break;
                default:
                    reader.nextString();
                    break;
            }
        }
        reader.endObject();

        return basicInfo;
    }
}
