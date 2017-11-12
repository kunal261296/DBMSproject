package com.hp.technicalfest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by hp on 14-10-2017.
 */

public class StudentDbHelpher extends SQLiteOpenHelper {
  public  static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Main";
   public static final String TABLE_NAME="Student";
    public static final String COLUMN_STUDENT_USN="usn";
   public static final String COLUMN_STUDENT_FIRSTNAME="firstname";
    public static final String COLUMN_STUDENT_LASNTAME="lastname";
    public static final String COLUMN_STUDENT_EMAIL="email";
    public static final String COLUMN_STUDENT_CONTACT="contact";
    public static final String COLUMN_STUDENT_PASSWORD="password";

    private String CREATE_STUDENT_TABLE="create table Student(usn TEXT primary key,firstname TEXT,lastname TEXT,email TEXT,contact TEXT,password TEXT);";
    private String DROP_STUDENT_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;
    public StudentDbHelpher(Context context){
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
    public void addStudent(StudentDbHelpher sdb,String usn,String firstname,String lastname,String email,String contact,String password){
        SQLiteDatabase db=sdb.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_STUDENT_USN,usn);
        values.put(COLUMN_STUDENT_FIRSTNAME,firstname);
        values.put(COLUMN_STUDENT_LASNTAME,lastname);
        values.put(COLUMN_STUDENT_EMAIL,email);
        values.put(COLUMN_STUDENT_CONTACT,contact);
        values.put(COLUMN_STUDENT_PASSWORD,password);
       long k =db.insert(TABLE_NAME,null,values);
        db.close();

    }


        public boolean checkUser(String usn, String password) {


            String[] columns = {COLUMN_STUDENT_USN,COLUMN_STUDENT_PASSWORD};
            SQLiteDatabase db = this.getReadableDatabase();
            String selection = COLUMN_STUDENT_USN + " = ?" + " AND " + COLUMN_STUDENT_PASSWORD + " = ?";
            String[] selectionArgs = {usn, password};
            Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return true;
            }

            return false;
        }
    public boolean checkUser(String usn) {


        String[] columns = {COLUMN_STUDENT_USN};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_STUDENT_USN + " = ?";
        String[] selectionArgs = {usn};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;


    }
    public Cursor getData(String usn){
        String[] columns={COLUMN_STUDENT_USN,COLUMN_STUDENT_FIRSTNAME};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=COLUMN_STUDENT_USN+"=?";
        String[] selectionArgs={usn};

        Cursor c=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        return c;
    }
    }



