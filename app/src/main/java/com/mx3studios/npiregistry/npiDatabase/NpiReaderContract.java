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

}
