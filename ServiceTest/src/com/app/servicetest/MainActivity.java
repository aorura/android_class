package com.app.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends Activity implements View.OnClickListener {
	EditText edtNumber1, edtNumber2;
	TextView txtResult, txtResult2;
	Button btnStartService, btnStopService, btnFinish, btnCalc;
	IRemoteService mService;
	ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mService = IRemoteService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mService = null;
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtResult = (TextView) findViewById(R.id.txtResult);
		txtResult2 = (TextView) findViewById(R.id.txtResult2);
		edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
		edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
		btnStartService = (Button) findViewById(R.id.btnStartService);
		btnStopService = (Button) findViewById(R.id.btnStopService);
		btnFinish = (Button) findViewById(R.id.btnFinish);
		btnCalc = (Button) findViewById(R.id.btnCalc);
		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		btnCalc.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch (v.getId()) {
		case R.id.btnStartService :
			i = new Intent(getApplicationContext(), UserService.class);
//			startService(i);	// background service
			bindService(i, mConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnStopService :
//			i = new Intent(getApplicationContext(), UserService.class);	// background service
//			stopService(i);		// background service
			unbindService(mConnection);
			break;
		case R.id.btnFinish :
			finish();
			break;
		case R.id.btnCalc:
			int number1 = Integer.parseInt(edtNumber1.getText().toString());
			int number2 = Integer.parseInt(edtNumber2.getText().toString());
			int result=0;
			try {
				result = mService.calc(number1, number2);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			txtResult2.setText(Integer.toString(result));
			break;
		}
		
	}
}
