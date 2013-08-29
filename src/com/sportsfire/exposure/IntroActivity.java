package com.sportsfire.exposure;

import java.util.Timer;
import java.util.TimerTask;

import com.sportsfire.exposure.R;
import com.sportsfire.unique.Provider;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class IntroActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.intro_activity);
		Account[] s = AccountManager.get(this).getAccountsByType(com.sportsfire.sync.Constants.ACCOUNT_TYPE);
		if (s.length > 0){
			ContentResolver.requestSync(s[0], Provider.AUTHORITY, new Bundle());
		}
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				Intent myIntent = new Intent(IntroActivity.this, MainActivity.class);
				IntroActivity.this.startActivity(myIntent);
				finish();
			}
		}, 1500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
