package com.mx3studios.npiregistry.npiDatabase;

import android.provider.BaseColumns;

/**
 * Created by Mario on 3/22/2016.
 */
public final class NpiReaderContract {

    public NpiReaderContract() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_FIRST = "first_name";
        public static final String COLUMN_NAME_LAST = "last_name";
        public static final String COLUMN_NAME_ORG = "organization_name";
        public static final String COLUMN_NAME_TAX_DESC = "taxonomy_desc";
        public static final String COLUMN_NAME_TAX_CODE = "taxonomy_code";
        public static final String COLUMN_NAME_ADDR = "address";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COUNTRY_NAME = "country_name";
        public static final String COLUMN_NAME_COUNTRY_CODE = "country_code";
        public static final String COLUMN_NAME_POSTAL_CODE = "postal_code";
        public static final String COLUMN_NAME_PHONE = "telephone";
        public static final String COLUMN_NAME_ENUM_TYPE = "enumType";
        public static final String COLUMN_NAME_LAST_UPDATED = "lastUpdated";
        public static final String COLUMN_NAME_NULLABLE = "NULL";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_FIRST + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_LAST + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_ORG + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_TAX_DESC + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_TAX_CODE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_ADDR + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CITY + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_STATE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_COUNTRY_CODE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_COUNTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_POSTAL_CODE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_ENUM_TYPE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_LAST_UPDATED + TEXT_TYPE + COMMA_SEP
             + ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static final String[] COLUMNS = {
            FeedEntry.COLUMN_NAME_ENTRY_ID,//0
            FeedEntry.COLUMN_NAME_FIRST,//1
            FeedEntry.COLUMN_NAME_LAST,//2
            FeedEntry.COLUMN_NAME_ORG,//3
            FeedEntry.COLUMN_NAME_TAX_DESC,//4
            FeedEntry.COLUMN_NAME_TAX_CODE,//5
            FeedEntry.COLUMN_NAME_ADDR,//6
            FeedEntry.COLUMN_NAME_CITY,//7
            FeedEntry.COLUMN_NAME_STATE,//8
            FeedEntry.COLUMN_NAME_COUNTRY_NAME,//9
            FeedEntry.COLUMN_NAME_COUNTRY_CODE,//10
            FeedEntry.COLUMN_NAME_POSTAL_CODE,//11
            FeedEntry.COLUMN_NAME_PHONE,//12
            FeedEntry.COLUMN_NAME_ENUM_TYPE,//13
            FeedEntry.COLUMN_NAME_LAST_UPDATED//14
    };
}
