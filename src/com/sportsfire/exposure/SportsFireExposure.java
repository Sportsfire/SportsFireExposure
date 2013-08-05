package com.sportsfire.exposure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sportsfire.exposure.androidwheel.InputTeam;
import com.sportsfire.exposure.objects.Season;
import com.sportsfire.exposure.objects.SeasonList;
import com.sportsfire.exposure.objects.Squad;
import com.sportsfire.exposure.objects.SquadList;

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
		switch (v.getId()) {
		case R.id.button1:
			Season selected = seasons.getSeasonList().get(spinner1.getSelectedItemPosition());
			Squad chosen = squads.getSquadList().get(spinner2.getSelectedItemPosition());
			intent = new Intent(this, com.sportsfire.exposure.androidwheel.InputTeam.class);
			intent.putExtra(InputTeam.ARG_SEASON_ID, selected.getSeasonID());
			intent.putExtra(InputTeam.ARG_SQUAD, chosen);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(this, com.sportsfire.exposure.androidwheel.InputPlayer.class);
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
