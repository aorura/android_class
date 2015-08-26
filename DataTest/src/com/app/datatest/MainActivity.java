package com.app.datatest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	DBHelper helper;
	SQLiteDatabase db;
	TextView txtResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtResult = (TextView) findViewById(R.id.txtResult);
		
		helper = new DBHelper(MainActivity.this, "item.db", null, 1);
		db = helper.getReadableDatabase();
		Cursor cursor = db.query("item", null, null, null, null, null, null);
		
		while (cursor.moveToNext()) {
			txtResult.append(cursor.getString(cursor.getColumnIndex("_id")));
			txtResult.append(" : ");
			txtResult.append(cursor.getString(cursor.getColumnIndex("itemname")));
			txtResult.append(" : ");
			txtResult.append(cursor.getString(cursor.getColumnIndex("price")));
		}
	}
}
