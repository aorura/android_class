package com.superdroid.test.Thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThreadActivity extends Activity
{
    int      mCount = 0;
    TextView mCountTextView = null;
    Handler  mHandler = new Handler();
    
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        
        Log.d("superdroid", "onCreate()");
        
        setContentView( R.layout.activity_thread );
        mCountTextView = ( TextView )findViewById( R.id.count_textview );

        Thread countThread = new Thread("Count Thread")
        {
            public void run()
            {
                for ( int i = 0 ; i < 10 ; i ++ )
                {
                    mCount ++;
                    Runnable callback = new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Log.i("superdroid", "Count : " + mCount );
                            mCountTextView.setText( "Count : " + mCount );
                        }
                    };
                    Message message = Message.obtain( mHandler, callback );
                    mHandler.sendMessage( message );
                    
                    try
                    {
                        Thread.sleep( 1000 );
                    }
                    catch ( InterruptedException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        
        countThread.start();
    }
    
    public void onClick ( View v )
    {
        mCountTextView.setText( "Count : " + mCount );
    }
}

