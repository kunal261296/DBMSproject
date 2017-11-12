package com.hp.technicalfest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 25-10-2017.
 */

public class ParticipantsDbHelper extends SQLiteOpenHelper {
    public  static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="participants";
    public static final String TABLE_NAME="participantstable";
    public static final String COLUMN_PARTICIPANT_USN="usn";
    public static final String COLUMN_PARTICIAPANT_WORKSHOP_ID="wid";
    public static final String COLUMN_PARTICIPANT_TRANSACTIONID="transactionid";
    public static final String COLUMN_PARTICIPANT_STATUS="registerstatus";
    private String CREATE_PARTICIAPNT_TABLE="create table participantstable(usn TEXT,wid TEXT,transactionid TEXT,registerstatus INT);";
    private String DROP_PARTICIPANT_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;
    public ParticipantsDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PARTICIAPNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_PARTICIPANT_TABLE);
        onCreate(db);
    }
    public long addParticpants(ParticipantsDbHelper ccb,String usn,String wid,String transactionid,int status){
        SQLiteDatabase db=ccb.getWritableDatabase();
        db.beginTransaction();
        ContentValues values=new ContentValues();
        values.put(COLUMN_PARTICIPANT_USN,usn);
        values.put(COLUMN_PARTICIAPANT_WORKSHOP_ID,wid);
        values.put(COLUMN_PARTICIPANT_TRANSACTIONID,transactionid);
        values.put(COLUMN_PARTICIPANT_STATUS,status);

        long k=db.insert(TABLE_NAME,null,values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return k;

    }
    public Cursor getData(String wid){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={COLUMN_PARTICIPANT_USN};
        String whereclause=COLUMN_PARTICIAPANT_WORKSHOP_ID+"=?";
        String[] whereargs={wid};
        Cursor c=db.query(TABLE_NAME,columns,whereclause,whereargs,null,null,null);
      return c;

    }
}
