package com.hp.technicalfest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 24-10-2017.
 */

public class CoordinatorDbHelper extends SQLiteOpenHelper {
    public  static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="coordinatorhelper";
    public static final String TABLE_NAME="coordinator";
    public static final String COLUMN_COORDINATOR_ID="cid";
    public static final String COLUMN_COORDINATOR_WORKSHOP_ID="wid";
    public static final String COLUMN_COORDINATOR_NAME="coordinatorname";
    public static final String COLUMN_COORDINATOR_CONTACT="coordinatorcontact";
    public static final String COLUMN_COORDINATOR_PASSWORD="coordinatorpassword";
    public static final String COLUMN_COORDINATOR_STATUS="workshopstatus";
    private String CREATE_COORDINATOR_TABLE="create table coordinator(cid TEXT primary key,coordinatorname TEXT,coordinatorcontact TEXT,coordinatorpassword TEXT,workshopstatus INT,wid TEXT);";
    private String DROP_COORDINATOR_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;
    public CoordinatorDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COORDINATOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_COORDINATOR_TABLE);
        onCreate(db);
    }
    public void addCoordinator(CoordinatorDbHelper ccb,String cid,String wid,String name,String contact,String password,int status){
        SQLiteDatabase db=ccb.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_COORDINATOR_ID,cid);
        values.put(COLUMN_COORDINATOR_WORKSHOP_ID,wid);
        values.put(COLUMN_COORDINATOR_NAME,name);
        values.put(COLUMN_COORDINATOR_CONTACT,contact);
        values.put(COLUMN_COORDINATOR_PASSWORD,password);
        values.put(COLUMN_COORDINATOR_STATUS,status);

        long k =db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public boolean checkUser(String cid, String password) {


        String[] columns = {COLUMN_COORDINATOR_ID,COLUMN_COORDINATOR_PASSWORD};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_COORDINATOR_ID + " = ?" + " AND " + COLUMN_COORDINATOR_PASSWORD + " = ?";
        String[] selectionArgs = {cid, password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
