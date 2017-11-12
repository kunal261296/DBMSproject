package com.hp.technicalfest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 18-10-2017.
 */

public class CoreCommiteeDbHelper extends SQLiteOpenHelper {
    public  static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="sample";
    public static final String TABLE_NAME="workshop";
    public static final String COLUMN_WORKSHOP_ID="wid";
    public static final String COLUMN_WORKSHOP_NAME="workshopname";
    public static final String COLUMN_WORKSHOP_TYPE="workshoptype";
    public static final String COLUMN_WORKSHOP_VENUE="workshopvenue";
    public static final String COLUMN_WORKSHOP_TIME="workshoptime";
    public static final String COLUMN_WORKSHOP_PRICE="workshopprice";
    private String CREATE_STUDENT_TABLE="create table workshop(wid TEXT primary key,workshopname TEXT,workshoptype TEXT,workshopvenue TEXT,workshoptime TEXT,workshopprice INT);";

    private String DROP_STUDENT_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;
    public CoreCommiteeDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STUDENT_TABLE);
        onCreate(db);
    }
    public void addWorkshop(CoreCommiteeDbHelper ccdb,String wid,String workshopname,String workshoptype,String workshopvenue,String workshoptime,int workshopprice){
        SQLiteDatabase db=ccdb.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_WORKSHOP_ID,wid);
        values.put(COLUMN_WORKSHOP_NAME,workshopname);
        values.put(COLUMN_WORKSHOP_TYPE,workshoptype);
        values.put(COLUMN_WORKSHOP_VENUE,workshopvenue);
        values.put(COLUMN_WORKSHOP_TIME,workshoptime);
        values.put(COLUMN_WORKSHOP_PRICE,workshopprice);
        long k =db.insert(TABLE_NAME,null,values);

        db.close();


    }


    }


