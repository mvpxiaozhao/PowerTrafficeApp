package com.example.powertrafficeapp.util;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final String DB_NAME = "car.db";///
    private static final String DB_TABLE = "chongzhijiju";
    private static final int DB_VERSION = 1;//版本号
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_E_MAIL = "e_mail";
    private final Context context;
    private SQLiteDatabase db;//数据库的操作对象
    private DBOpenHelper dbOpenHelper;
    public DBAdapter(Context _context) {
        context = _context;
    }

    public void openDB() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    public long Insert(Carinformation people) {
        ContentValues newValue = new ContentValues();
        newValue.put(KEY_NAME, people.Name);
        newValue.put(KEY_PHONE_NUMBER, people.Money);
        newValue.put(KEY_ADDRESS, people.Time);
        newValue.put(KEY_E_MAIL, people.Shusi);
        return db.insert(DB_TABLE, null, newValue);
    }

    public long deleteAll() {
        return db.delete(DB_TABLE, null, null);
    }

    public long deleteByName(String name) {
        return db.delete(DB_TABLE, KEY_NAME + "=" + "'" + name + "'", null);
    }

    public long updateByName(Carinformation people, String name) {
        ContentValues updateValue = new ContentValues();
        updateValue.put(KEY_NAME, people.Name);
        updateValue.put(KEY_PHONE_NUMBER, people.Money);
        updateValue.put(KEY_ADDRESS, people.Time);
        updateValue.put(KEY_E_MAIL, people.Shusi);
        return db.update(DB_TABLE, updateValue, KEY_NAME + "=" + "'" + name + "'", null);
    }

    public Carinformation[] queryById(int id) {
        Cursor result = db.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_ADDRESS, KEY_E_MAIL}, KEY_ID + "=" + id, null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    public Carinformation[] queryByName(String name) {
        Cursor result = db.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_ADDRESS, KEY_E_MAIL}, KEY_NAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    public Carinformation[] qurryAll() {
        Cursor result = db.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_ADDRESS, KEY_E_MAIL}, null, null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    private Carinformation[] ConvertToPeopleInfo(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        Carinformation[] peoples = new Carinformation[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new Carinformation(cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)), cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)), cursor.getString(cursor.getColumnIndex(KEY_E_MAIL)));
            peoples[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            cursor.moveToNext();
        }
        return peoples;

    }

    private static class DBOpenHelper extends SQLiteOpenHelper {
        private static final String DB_CREATE = "create table " + DB_TABLE + "(" + KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text not null, " + KEY_PHONE_NUMBER + " text," + KEY_ADDRESS + " text," + KEY_E_MAIL + " text);";

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub
            arg0.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            arg0.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            arg0.close();
            onCreate(arg0);
        }
    }


}
