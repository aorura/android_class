package com.app.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class UserService extends Service {
	private int count = 0;
	
	// IPC service [[
//	private final IBinder remoteService = new RemoteService();
//	
//	public class RemoteService extends Binder {
	private final IRemoteService.Stub remoteService = new IRemoteService.Stub() {
		// user method
		public int calc(int numberOne, int numberTwo) {
			return (numberOne + numberTwo);
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return remoteService;	// when background service, return null
	}

	// ]] IPC service 
	
	// background service [[
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		while (count <= 100000000) {
			count++;
			Log.i("UserService", "count -> " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		return START_STICKY;// 재시작		// START_NOT_STICKY :  재시작 안함, START_REDELIVER_INTENT :  재시작과 함께  INTENT도 함께 받음
	}
	// ]] background service 
}
