package com.sportsfire.exposure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sportsfire.exposure.androidwheel.TeamMainActivity;
import com.sportsfire.objects.Season;
import com.sportsfire.objects.SeasonList;
import com.sportsfire.objects.Squad;
import com.sportsfire.objects.SquadList;

public class SportsFireExposure extends Activity {
	private Spinner spinner1;
	private Spinner spinner2;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	SeasonList seasons;
	SquadList squads;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		seasons = new SeasonList(this);
		squads = new SquadList(this);
		setContentView(R.layout.sportsfireexposure);
		spinner1 = (Spinner) findViewById(R.id.weekSpinner);
		spinner2 = (Spinner) findViewById(R.id.squadSpinner);
		adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seasons.getSeasonNameList());
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, squads.getSquadNameList());
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);
		spinner2.setAdapter(adapter2);
		spinner1.setVisibility(View.VISIBLE);
		spinner2.setVisibility(View.VISIBLE);

	}

	public void ButtonOnClick(View v) {
		Intent intent;
		Season selected = seasons.getSeasonList().get(spinner1.getSelectedItemPosition());
		Squad chosen = squads.getSquadList().get(spinner2.getSelectedItemPosition());
		switch (v.getId()) {

		case R.id.button1:
			intent = new Intent(this, com.sportsfire.exposure.androidwheel.TeamMainActivity.class);
			intent.putExtra(TeamMainActivity.ARG_SEASON_ID, selected.getSeasonID());
			intent.putExtra(TeamMainActivity.ARG_SQUAD, chosen);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(this, com.sportsfire.exposure.androidwheel.PlayerMainActivity.class);
			intent.putExtra(TeamMainActivity.ARG_SEASON_ID, selected.getSeasonID());
			intent.putExtra(TeamMainActivity.ARG_SQUAD, chosen);
			startActivity(intent);
			break;
		}
	}
}
