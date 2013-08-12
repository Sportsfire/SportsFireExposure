package com.sportsfire.exposure;

import com.sportsfire.exposure.R;

import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SportsFireExposure extends Activity {
	private Spinner spinner1;
	private Spinner spinner2;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private static final String[] m1 = { "Season 1", "Season 2", "Season 3",
			"Season 4", };
	private static final String[] m2 = { "First Team", "Academy" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.sportsfireexposure);
		spinner1 = (Spinner) findViewById(R.id.weekSpinner);
		spinner2 = (Spinner) findViewById(R.id.squadSpinner);
		adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m1);
		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m2);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		spinner2.setAdapter(adapter2);
		spinner1.setVisibility(View.VISIBLE);
		spinner2.setVisibility(View.VISIBLE);

	}

	public void ButtonOnClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent(this,
					com.sportsfire.exposure.androidwheel.TeamMainActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(this,com.sportsfire.exposure.androidwheel.PlayerMainActivity.class);
			startActivity(intent);
			break;
		/*
		 * case R.id.button2: intent = new Intent(this,
		 * AnalysisPageActivity.class);
		 * intent.putExtra(AnalysisPageActivity.ARG_ITEM_SEASON_ID,
		 * selected.getSeasonID()); startActivity(intent); break;
		 */

		}
	}
}
