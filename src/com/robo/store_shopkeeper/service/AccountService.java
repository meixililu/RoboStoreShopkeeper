package com.robo.store_shopkeeper.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.robo.store_shopkeeper.util.Authenticator;

public class AccountService extends Service {

	private Authenticator authenticator;
	
	@Override
    public void onCreate() {
        super.onCreate();
        authenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }

}
