package com.app.threadapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
	TextView txtResult, txtResult2;
	Button btnResult, btnFinish;
	int sum = 0, sum2 = 0;
	Thread thread;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				txtResult2.setText(Integer.toString(sum2));
			}
		}
	};
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtResult = (TextView) findViewById(R.id.txtResult);
		txtResult2 = (TextView) findViewById(R.id.txtResult2);
		btnResult = (Button) findViewById(R.id.btnResult);
		btnFinish = (Button) findViewById(R.id.btnFinish);
		btnResult.setOnClickListener(this);
		btnFinish.setOnClickListener(this);

		thread = new Thread(new UserThread());
		thread.setDaemon(true); // kill when main thread exits.
		thread.start();
		
		this.runOnUiThread(new Runnable() {
			public void run() {
				sum2++;
				txtResult2.setText(Integer.toString(sum2));
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnResult:
			sum++;
			txtResult.setText(Integer.toString(sum));
			txtResult2.setText(Integer.toString(sum2));
			break;
		case R.id.btnFinish:
			break;
		}

	}

	class UserThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				sum2++;
//				handler.sendEmptyMessage(0);
				handler.post(new Runnable() {
					public void run() {
						txtResult2.setText(Integer.toString(sum2));
					}
				});
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
