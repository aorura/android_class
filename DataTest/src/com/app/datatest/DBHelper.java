package com.app.datatest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DB = "item.db";
	public static final String TABLE = "item";
	public static final int VERSION = 1;
	
	public static DBHelper mDBHelper = null;
	
	public static DBHelper getInstance(Context context) {
		if (mDBHelper == null) {
			mDBHelper = new DBHelper(context, DB, null, VERSION);
		}
		
		return mDBHelper;
	}

	private DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE item (" + 
					 " _id integer primary key autoincrement, " +
					 "itemname text, " +
					 "price integer);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS item";
		
		db.execSQL(sql);
		onCreate(db);
	}

	public long insert(ContentValues addRowValue) {
		return getWritableDatabase().insert(TABLE, null, addRowValue);
	}
	
	public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		return getReadableDatabase().query(TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	public int update(ContentValues updateRowValue, String whereClause, String[] whereArgs) {
		return getWritableDatabase().update(TABLE, updateRowValue, whereClause, whereArgs);
	}
	
	public int delete(String whereClause, String[] whereArgs) {
		return getWritableDatabase().delete(TABLE, whereClause, whereArgs);
	}
}
