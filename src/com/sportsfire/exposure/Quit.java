package com.WeiGu.SportsFireExposure;

import java.io.Closeable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

	public class Quit extends Activity {
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			finish();
			
		}
	
		
	
		
		
		
		
	}
	
	



