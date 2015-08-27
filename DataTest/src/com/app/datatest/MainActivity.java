package com.app.datatest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final int DBVERSION = 1;
	DBHelper helper;
	SQLiteDatabase db;
	TextView txtResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtResult = (TextView) findViewById(R.id.txtResult);
		
		helper = DBHelper.getInstance(this);
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("item", null, null, null, null, null, null);
		
		if (cursor.getCount() < 1) {
			ContentValues value = new ContentValues();
			value.put("itemname", "tv");
			value.put("price", 100000);
			db.insert("item",  null, value);
			
			value.put("itemname", "camera");
			value.put("price", 500000);
			db.insert("item",  null, value);
			
			value.put("itemname", "computer");
			value.put("price", 80000);
			db.insert("item",  null, value);
		}
		
		cursor = db.query("item", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			txtResult.append(cursor.getString(cursor.getColumnIndex("_id")));
			txtResult.append(" : ");
			txtResult.append(cursor.getString(cursor.getColumnIndex("itemname")));
			txtResult.append(" : ");
			txtResult.append(cursor.getString(cursor.getColumnIndex("price")));
			txtResult.append("\n");
		}
		cursor.close();
	}
}
