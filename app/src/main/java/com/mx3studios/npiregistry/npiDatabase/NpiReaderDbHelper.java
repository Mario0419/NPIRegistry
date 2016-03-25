package com.mx3studios.npiregistry.npiDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mx3studios.npiregistry.npi.NpiResult;
import com.mx3studios.npiregistry.npiDatabase.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 3/22/2016.
 */
public class NpiReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NpiRegistry.db";
    private static final int SQLITE_ERROR = -1;

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

    public boolean addFavorite(NpiResult result) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = NPIResultDBHelper.toTableRow(result);
        return db.replace(NpiReaderContract.FeedEntry.TABLE_NAME,
                NpiReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,
                row) != SQLITE_ERROR;
    }

    public void saveFavorites(List<NpiResult> list) {

        for(NpiResult result : list) {

            addFavorite(result);
        }
    }

    public ArrayList<NpiResult> getAllFavorites() {
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
            resultList.add(result);
        }

        return resultList;
    }
}
