package com.mx3studios.npiregistry.npiDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mx3studios.npiregistry.npi.NpiResult;
import com.mx3studios.npiregistry.npiDatabase.*;

import java.util.ArrayList;

/**
 * Created by Mario on 3/22/2016.
 */
public class NpiReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NpiRegistry.db";

    public NpiReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NpiReaderContract.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NpiReaderContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveFavorites(ArrayList<NpiResult> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<NpiResult> currList = getFavoriteResultList();
        ArrayList<NpiResult> newList = new ArrayList<>();
        for(int i = 0 ; i < list.size(); i++) {
            boolean inList = false;
            for(int j = 0; j < currList.size(); j++) {
                if(currList.get(j).getNpi() == list.get(i).getNpi()) {
                    inList = true;
                    break;
                }
            }
            if(!inList) {
                newList.add(list.get(i));
            }
        }
        for(int i = 0; i < newList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, newList.get(i).getNpi());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_FIRST,newList.get(i).getBasicInfo().getFirstName());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_LAST,newList.get(i).getBasicInfo().getLastName());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ORG,newList.get(i).getBasicInfo().getLastName());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_TAX_DESC,newList.get(i).getTaxonomies().get(0).getDesc());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_TAX_CODE,newList.get(i).getTaxonomies().get(0).getCode());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ADDR,newList.get(i).getAddresses().get(0).getAddress1());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_CITY,newList.get(i).getAddresses().get(0).getCity());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_STATE,newList.get(i).getAddresses().get(0).getState());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_COUNTRY_NAME,newList.get(i).getAddresses().get(0).getCountryName());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_COUNTRY_CODE,newList.get(i).getAddresses().get(0).getCountryCode());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_POSTAL_CODE,newList.get(i).getAddresses().get(0).getPostalCode());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_PHONE,newList.get(i).getAddresses().get(0).getPhone());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_ENUM_TYPE, newList.get(i).getEnumType());
            values.put(NpiReaderContract.FeedEntry.COLUMN_NAME_LAST_UPDATED,newList.get(i).getBasicInfo().getLastUpdated());
            long newRowId = db.insert(
                    NpiReaderContract.FeedEntry.TABLE_NAME,
                    NpiReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,
            values);
        }

    }

    public ArrayList<NpiResult> getFavoriteResultList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NpiResult> resultList = new ArrayList<>();
        Cursor cursor = db.query(NpiReaderContract.FeedEntry.TABLE_NAME,
                NpiReaderContract.COLUMNS,
                null, null, null, null, null);

        if(cursor == null) {
            return resultList;
        }
        while(cursor.moveToNext()) {
            NpiResult result = new NpiResult(cursor);
        }
        return resultList;
    }
}
