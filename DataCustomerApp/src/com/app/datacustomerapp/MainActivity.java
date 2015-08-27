package com.app.datacustomerapp;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String PROVIDER_URI = "content://com.app.ItemProvider";
	
	TextView txtResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtResult = (TextView) findViewById(R.id.txtResult);
		String[] columns = new String[] {"_id", "itemname", "price"};
		Cursor cursor = getContentResolver().query(Uri.parse(PROVIDER_URI), columns, null, null, null);
		
		if (cursor != null) {
			txtResult.append("\nID\tItem Name\tPrice\n-----------------------------------------\n");
			while (cursor.moveToNext()) {
				txtResult.append(cursor.getString(cursor.getColumnIndex("_id")));
				txtResult.append(" : ");
				txtResult.append(cursor.getString(cursor.getColumnIndex("itemname")));
				txtResult.append(" : ");
				txtResult.append(cursor.getString(cursor.getColumnIndex("price")));
				txtResult.append("\n");
			}
		} else {
			Toast.makeText(this, "cursor is null", Toast.LENGTH_SHORT).show();
		}
		if (cursor != null)
			cursor.close();
	}
}
