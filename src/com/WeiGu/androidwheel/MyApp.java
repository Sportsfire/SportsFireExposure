package com.WeiGu.androidwheel;

import android.app.Application;

public class MyApp extends Application{

	public int spinnerPosition;

	public int getSpinnerPosition() {
		return spinnerPosition;
	}

	public void setSpinnerPosition(int spinnerPosition) {
		this.spinnerPosition = spinnerPosition;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		spinnerPosition = 0;
	}
	
}
