package com.app.apptest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
	private final String TAG = "park";
	TextView txtTitle;
	Button btnCall, btnFinish;
	private final int SUB_ACTIVITY = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.i(TAG, "onCreate()");
        
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnCall.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.btnCall : 
    		Intent i = new Intent(getApplicationContext(), SubActivity.class);
    		i.putExtra("arg", "argument");
//    		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity(i);
//    		startActivityForResult(i,SUB_ACTIVITY);
    		break;
    	case R.id.btnFinish : break;
    	}
    }
    
    

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == SUB_ACTIVITY) {
			if (resultCode == RESULT_OK) {
				txtTitle.append("\n" + data.getStringExtra("ret").toString());
			}
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "onStart()");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, "onRestart()");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "onResume()");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "onPause()");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "onStop()");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy()");
	}
    
    
}
