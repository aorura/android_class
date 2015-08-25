package com.app.apptest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends Activity implements View.OnClickListener{
	TextView txtSubTitle;
	Button btnSubCall, btnSubFinish;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		
		txtSubTitle = (TextView) findViewById(R.id.txtSubTitle);
		btnSubCall = (Button) findViewById(R.id.btnSubCall);
		btnSubFinish = (Button) findViewById(R.id.btnSubFinish);
		
		btnSubCall.setOnClickListener(this);
		btnSubFinish.setOnClickListener(this);
		
		Intent i = getIntent();
		txtSubTitle.setText(i.getStringExtra("arg").toString());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	switch (v.getId()) {
    	case R.id.btnSubCall : 
    		Intent ii = new Intent(getApplicationContext(), SubActivity.class);
//    		ii.setAction(Intent.ACTION_VIEW);
//    		ii.setData(Uri.parse("http://www.google.com"));
//    		ii.setAction("com.app.secondapp.ACTION_USER");
//    		ii.setAction(Intent.ACTION_VIEW);
//    		ii.setType("image/png");
//    		ii.setDataAndType(Uri.parse("http://www.lge.com:80/files/second.png"), "image/png");
    		ii.putExtra("arg", "park");
    		startActivity(ii);
    		break;
    	case R.id.btnSubFinish : 
    		Intent i = getIntent();
    		i.putExtra("ret", "return value");
    		setResult(RESULT_OK, i);
    		finish(); 
    		break;
    	}
	}
}
