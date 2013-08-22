package com.sportsfire.exposure;

import com.sportsfire.exposure.R;
import com.sportsfire.exposure.androidwheel.Constants;
import com.sportsfire.exposure.sync.ExposureProvider;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class About extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
}
}
