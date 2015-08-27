package com.app.broadcastapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.btnBroadcast);
		btn.setOnClickListener(this);
				
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		i.setAction("com.app.receiverapp.MESSAGE");
		i.putExtra("arg", "BroadCasted");
		i.addFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);	// ��ġ �� ����� �ۿ���  ��ε�ĳ��Ʈ �޽��� ����
//		i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);	// ��ġ �� ���� �� �� �ۿ��� ��ε�ĳ��Ʈ �޽��� ����
		sendBroadcast(i);
	}
}
