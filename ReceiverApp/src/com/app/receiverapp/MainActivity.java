package com.app.receiverapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	BroadcastReceiver mReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_layout);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.app.receiverapp.MESSAGE");

		mReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				String str = intent.getStringExtra("arg");
				Toast.makeText(context, "Receiver : " + str, Toast.LENGTH_SHORT).show();
			}
		};
		registerReceiver(mReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(mReceiver);
		
		super.onDestroy();
	}
	
	
}
