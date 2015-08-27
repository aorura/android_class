package com.app.receiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class UserReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String str = intent.getStringExtra("arg");
		Toast.makeText(context, "Receiver : " + str,  Toast.LENGTH_SHORT).show();
	}

}
