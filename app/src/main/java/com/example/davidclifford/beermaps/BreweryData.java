package com.example.davidclifford.beermaps;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class BreweryData {

    public BreweryData() {}

    /* Inner class defining table contents */

    public static abstract class BreweryEntry implements BaseColumns {
        public static final String TABLE_NAME = "Breweries";
        public static final String COLUMN_NAME_ENTRY_ID = "_id";
        public static final String COLUMN_NAME_TITLE = "Brewery";
        public static final String COLUMN_NAME_X_LOCATION = "xLocation";
        public static final String COLUMN_NAME_Y_LOCATION = "yLocation";
        public static final String COLUMN_NAME_FLAGSHIP_BEER = "flagshipBeer"; /* this will eventually be a link to
        a table containing multiple beers as another ID, not just one beer */ // call it beerID
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BreweryEntry.TABLE_NAME + " (" +
                    BreweryEntry._ID + " INTEGER PRIMARY KEY," +
                    BreweryEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    BreweryEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    BreweryEntry.COLUMN_NAME_X_LOCATION + TEXT_TYPE + COMMA_SEP +
                    BreweryEntry.COLUMN_NAME_Y_LOCATION + TEXT_TYPE + COMMA_SEP +
                    BreweryEntry.COLUMN_NAME_FLAGSHIP_BEER + TEXT_TYPE + COMMA_SEP +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BreweryEntry.TABLE_NAME;

    public class BreweryDataReaderDbHelper extends SQLiteOpenHelper {

        public static final String DATABASE_PATH = "BeerMaps/app/src/main/assets/BreweryData.sqbpro"; //location of database in file.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "BreweryData.db";
        public SQLiteDatabase BreweryData;
        public final Context myContext;


        public BreweryDataReaderDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
            this.myContext = context;
        }

        public void createDataBase() throws IOException {
            boolean dbExist = checkDataBase();

            if (dbExist) {
                // do Nothing... DB exists
            } else {

                this.getReadableDatabase();

                try {

                    copyDataBase();

                } catch (IOException e) {

                    throw new Error("Error copying data");

                }
            }
        }

        public boolean checkDataBase() {
            SQLiteDatabase checkDB = null;

            try {
                String myPath = DATABASE_PATH + DATABASE_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            } catch (SQLiteException e) {
                // no database yet
            }

            if (checkDB != null) {
                checkDB.close();
            }

            return checkDB != null ? true : false;
        }

        public void copyDataBase() throws IOException{
            //Open DB input stream
            InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

            //path to the just created empty DB
            String outFileName = DATABASE_PATH + DATABASE_NAME;

            //open the new empty DB as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0,  length);
            }

            //Close the stream
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }

        public void openDataBase() throws IOException {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            BreweryData = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }

        @Override
        public synchronized void close() {
            if(BreweryData != null) {
                BreweryData.close();
            }
            super.close();
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}


