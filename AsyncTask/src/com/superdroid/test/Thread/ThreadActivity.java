package com.superdroid.test.Thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThreadActivity extends Activity
{
    TextView            mDownloadStateTextView  = null;
    FileDownloadTask    mFileDownloadTask       = null; 
    
    private class FileDownloadTask extends AsyncTask<String, Integer, Boolean> 
    {
        @Override
        protected void onPreExecute()
        {
            mDownloadStateTextView.setText( "FileDownLoad..." );
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground( String... downloadInfos )
        {
            int totolCount = downloadInfos.length;
            
            for ( int i = 1 ; i <= totolCount ; i ++ )
            {
                publishProgress( i, totolCount );
                try { Thread.sleep( 1000 ); }
                catch ( InterruptedException e ) { return false; }
            }
            
            return true;
        }
        
        @Override
        protected void onProgressUpdate( Integer... downloadInfos )
        {
            int currentCount = downloadInfos[0];
            int totalCount   = downloadInfos[1];

            mDownloadStateTextView.setText( "Downloading : " + 
                                              currentCount + "/" + totalCount);
            super.onProgressUpdate( downloadInfos );
        }
        
        @Override
        protected void onCancelled( )
        {
            mDownloadStateTextView.setText( "Download cancel" );
            super.onCancelled();
        }
        
        @Override
        protected void onPostExecute( Boolean result )
        {
            if ( true == result )
            {
                mDownloadStateTextView.setText( "Download finish" );
            }
            else
            {
                mDownloadStateTextView.setText( "Download Fail" );
            }
            
            super.onPostExecute( result );
        }

    };
    
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_thread );
        mDownloadStateTextView = 
                       ( TextView )findViewById( R.id.download_state_textview);

        mFileDownloadTask = new FileDownloadTask();
        mFileDownloadTask.execute( "FileUrl_1", "FileUrl_2", "FileUrl_3", 
                                 "FileUrl_4", "FileUrl_5", "FileUrl_6",
                                 "FileUrl_7", "FileUrl_8", "FileUrl_9",
                                 "FileUrl_10");
    }
    
    public void onClick ( View v )
    {
        if ( mFileDownloadTask != null &&
             mFileDownloadTask.getStatus() != Status.FINISHED )
        {
            mFileDownloadTask.cancel( true );
        }
    }
}