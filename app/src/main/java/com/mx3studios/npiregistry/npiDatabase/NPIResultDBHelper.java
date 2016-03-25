package com.mx3studios.npiregistry.npiDatabase;

import android.content.ContentValues;
import com.mx3studios.npiregistry.npi.NpiResult;

/**
 * Created by David on 3/24/2016.
 */
public class NPIResultDBHelper {

    public static ContentValues toTableRow(NpiResult result) {

        ContentValues values = new ContentValues();

        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, result.getNpi());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_FIRST,result.getBasicInfo().getFirstName());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_LAST,result.getBasicInfo().getLastName());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ORG,result.getBasicInfo().getLastName());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_TAX_DESC,result.getTaxonomies().get(0).getDesc());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_TAX_CODE,result.getTaxonomies().get(0).getCode());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ADDR,result.getAddresses().get(0).getAddress1());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_CITY,result.getAddresses().get(0).getCity());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_STATE,result.getAddresses().get(0).getState());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_COUNTRY_NAME,result.getAddresses().get(0).getCountryName());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_COUNTRY_CODE,result.getAddresses().get(0).getCountryCode());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_POSTAL_CODE,result.getAddresses().get(0).getPostalCode());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_PHONE,result.getAddresses().get(0).getPhone());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ENUM_TYPE, result.getEnumType());
        values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_LAST_UPDATED,result.getBasicInfo().getLastUpdated());

        return values;
    }
}
