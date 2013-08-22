package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.sportsfire.exposure.R;
import com.sportsfire.exposure.objects.ExposureData;
import com.sportsfire.exposure.objects.Player;
import com.sportsfire.exposure.objects.Squad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class PlayerMainActivity extends Activity implements OnClickListener {

	private final int REQUESTCODE1 = 0x01;
	private final int REQUESTCODE2 = 0x02;
	private final int REQUESTCODE3 = 0x03;
	private final int REQUESTCODE4 = 0x04;

	public static final String ARG_SEASON_ID = "argumentSeason";
	public static final String ARG_SQUAD = "argumentSquad";

	private int layout_ids[] = { R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4 };
	private int item_ids[] = { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,
			R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14 };
	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<ImageView> colors11 = new ArrayList<ImageView>();
	private List<ImageView> colors12 = new ArrayList<ImageView>();
	private List<ImageView> colors13 = new ArrayList<ImageView>();
	private List<ImageView> colors21 = new ArrayList<ImageView>();
	private List<ImageView> colors22 = new ArrayList<ImageView>();
	private List<ImageView> colors23 = new ArrayList<ImageView>();
	private List<Boolean> isLongClickeds = new ArrayList<Boolean>();

	private Spinner mWeekSpinner, mPlayerSpinner;
	private ExposureData dataGetter;
	private Squad squad;
	private Player selectedPlayer;
	private ArrayList<ArrayList<String>> dates = new ArrayList<ArrayList<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputplayer);

		String seasonId = getIntent().getStringExtra(ARG_SEASON_ID);
		dataGetter = new ExposureData(this, seasonId);
		squad = getIntent().getParcelableExtra(ARG_SQUAD);

		String weeks[] = { "Week1-4", "Week5-8", "Week9-12", "Week13-16", "Week17-20", "Week21-24", "Week25-28",
				"Week29-32", "Week33-36", "Week37-40", "Week41-44", "Week45-48", "Week49-52" };

		mWeekSpinner = (Spinner) findViewById(R.id.spinner_week);
		mPlayerSpinner = (Spinner) findViewById(R.id.spinner_player);

		ArrayAdapter<String> weekAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weeks);
		weekAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				squad.getPlayerNameList());
		playerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mWeekSpinner.setAdapter(weekAdapter);
		mPlayerSpinner.setAdapter(playerAdapter);
		mPlayerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				initLayout();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		mWeekSpinner.setSelection(getIntent().getIntExtra("WEEK", 0));
		mWeekSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arg2 != mWeekSpinner.getSelectedItemPosition()) {
					Intent intent = getIntent();
					intent.putExtra("WEEK", arg2);
					finish();
					startActivity(intent);
				}
				initLayout();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		initLayout();
		initItem();
	}

	private void initLayout() {
		selectedPlayer = squad.getPlayerList().get(mPlayerSpinner.getSelectedItemPosition());
		int groupNum = (mWeekSpinner.getSelectedItemPosition() * 4);
		dataGetter.logAll2(selectedPlayer.getID());
		for (int i = 0; i < layout_ids.length; i++) {
			findViewById(layout_ids[i]).setOnClickListener(this);
			for (int j = 0; j < item_ids.length; j = j + 2) {
				List<ArrayList<String>> squadExposure = dataGetter.getSquadExposure(squad.getID(), groupNum + i, j/2);
				List<ArrayList<String>> exposure = dataGetter.getPlayerExposure(selectedPlayer.getID(), groupNum + i, j / 2);

				TextView tv_time = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.tv_time);
				texts1.add(tv_time);

				ImageView iv1 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.iv_color1);
				colors11.add(iv1);
				ImageView iv2 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.iv_color2);
				colors12.add(iv2);
				ImageView iv3 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.iv_color3);
				colors13.add(iv3);
				LinearLayout layout = (LinearLayout) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]);
				layouts.add(layout);

				TextView tv_day = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.tv_weekday);
				tv_day.setText(" \n ");
				texts3.add(tv_day);

				ImageView iv12 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.iv_color1);
				iv12.setBackgroundColor(0xFF8A2BE2);
				colors21.add(iv12);
				ImageView iv22 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.iv_color2);
				iv22.setBackgroundColor(0xFF8A2BE2);
				colors22.add(iv22);
				ImageView iv32 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.iv_color3);
				iv32.setBackgroundColor(0xFF8A2BE2);
				colors23.add(iv32);

				TextView tv_time2 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.tv_time);
				texts2.add(tv_time2);
				Boolean showTwo = false;
				iv1.setBackgroundColor(0);
				iv2.setBackgroundColor(0);
				iv3.setBackgroundColor(0);
				iv12.setBackgroundColor(0);
				iv22.setBackgroundColor(0);
				iv32.setBackgroundColor(0);
				layout.setVisibility(View.GONE);
				if (exposure != null) {
					tv_time.setText("  " + exposure.get(0).get(3) + "  ");
					iv1.setBackgroundColor(Integer.valueOf(exposure.get(0).get(0)));
					iv2.setBackgroundColor(Integer.valueOf(exposure.get(0).get(1)));
					iv3.setBackgroundColor(Integer.valueOf(exposure.get(0).get(2)));
					if (exposure.size() > 1) {
						showTwo = true;
						layout.setVisibility(View.VISIBLE);
						if (exposure.get(1).get(4).startsWith("S")) {
							tv_day.setText("S\nP");
							tv_time2.setText("  " + exposure.get(1).get(3) + "  ");
							iv12.setBackgroundColor(Integer.valueOf(exposure.get(1).get(0)));
							iv22.setBackgroundColor(Integer.valueOf(exposure.get(1).get(1)));
							iv32.setBackgroundColor(Integer.valueOf(exposure.get(1).get(2)));
						} else {
							tv_day.setText("D\nO");
							tv_time2.setText("  " + exposure.get(1).get(3) + "  ");
							iv12.setBackgroundColor(Integer.valueOf(exposure.get(1).get(0)));
							iv22.setBackgroundColor(Integer.valueOf(exposure.get(1).get(1)));
							iv32.setBackgroundColor(Integer.valueOf(exposure.get(1).get(2)));
						}
					}
				} else if (squadExposure != null){
					tv_time.setText("  " + squadExposure.get(0).get(1) + "  ");
					iv1.setBackgroundColor(Integer.valueOf(squadExposure.get(0).get(0)));
					iv2.setBackgroundColor(Integer.valueOf(squadExposure.get(0).get(0)));
					iv3.setBackgroundColor(Integer.valueOf(squadExposure.get(0).get(0)));
					if (squadExposure.size() > 1) {
						showTwo = true;
						layout.setVisibility(View.VISIBLE);
						if (squadExposure.get(1).get(2).startsWith("S")) {
							tv_day.setText("S\nP");
							tv_time2.setText("  " + squadExposure.get(1).get(1) + "  ");
							iv12.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
							iv22.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
							iv32.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
						} else {
							tv_day.setText("D\nO");
							tv_time2.setText("  " + squadExposure.get(1).get(1) + "  ");
							iv12.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
							iv22.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
							iv32.setBackgroundColor(Integer.valueOf(squadExposure.get(1).get(0)));
						}
					}
				}
				isLongClickeds.add(showTwo);

			}
		}

	}

	private void initItem() {

		for (int i = 0; i < layout_ids.length; i++) {
			ArrayList<String> dateList = dataGetter.getDaysOfWeek((mWeekSpinner.getSelectedItemPosition() * 4) + i);
			ArrayList<String> date = new ArrayList<String>();
			for (int j = 0; j < item_ids.length; j++) {

				if (j % 2 == 0) {

					switch (j / 2) {
					case 0:
						TextView tv0 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv0.setText("Su\n" + dateList.get(0));
						date.add(tv0.getText().toString());
						break;
					case 1:
						TextView tv1 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv1.setText("M\n" + dateList.get(1));
						date.add(tv1.getText().toString());
						break;
					case 2:
						TextView tv2 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv2.setText("T\n" + dateList.get(2));
						date.add(tv2.getText().toString());
						break;
					case 3:
						TextView tv3 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv3.setText("W\n" + dateList.get(3));
						date.add(tv3.getText().toString());
						break;
					case 4:
						TextView tv4 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv4.setText("Th\n" + dateList.get(4));
						date.add(tv4.getText().toString());
						break;
					case 5:
						TextView tv5 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv5.setText("F\n" + (dateList.get(5)));
						date.add(tv5.getText().toString());
						break;
					case 6:
						TextView tv6 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
								R.id.tv_weekday);
						tv6.setText("Sa\n" + (dateList.get(6)));
						date.add(tv6.getText().toString());
						break;

					default:
						break;
					}
					dates.add(date);
				} else {
					TextView tv = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.tv_weekday);
					// tv.setText(" \n ");
				}

			}

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			if (requestCode == REQUESTCODE1) {

				resultView(data, 0);

			} else if (requestCode == REQUESTCODE2) {

				resultView(data, 7);

			} else if (requestCode == REQUESTCODE3) {

				resultView(data, 14);

			} else if (requestCode == REQUESTCODE4) {

				resultView(data, 21);

			}
		}
	}

	private void resultView(Intent data, int k) {

		ArrayList<PlayerDayBean> days1 = data.getParcelableArrayListExtra("days1");
		ArrayList<PlayerDayBean> days2 = data.getParcelableArrayListExtra("days2");
		boolean[] longClickeds = data.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
		for (int i = k; i < k + longClickeds.length; i++) {

			isLongClickeds.set(i, longClickeds[i - k]);

			if (longClickeds[i - k]) {
				layouts.get(i).setVisibility(View.VISIBLE);
			} else {
				layouts.get(i).setVisibility(View.GONE);
			}

		}
		int weekNum = (mWeekSpinner.getSelectedItemPosition() * 4);
		for (int i = k; i < k + Constants.SIZE; i++) {
			PlayerDayBean day = days1.get(i - k);
			colors11.get(i).setBackgroundColor(day.getColor1());
			colors12.get(i).setBackgroundColor(day.getColor2());
			colors13.get(i).setBackgroundColor(day.getColor3());
			texts1.get(i).setText(day.getTime());
			PlayerDayBean day2 = days2.get(i - k);
			texts3.get(i).setText(day2.getWeekday());
			colors21.get(i).setBackgroundColor(day2.getColor1());
			colors22.get(i).setBackgroundColor(day2.getColor2());
			colors23.get(i).setBackgroundColor(day2.getColor3());
			texts2.get(i).setText(day2.getTime());
			if (!longClickeds[i - k]) {
				dataGetter.setPlayerExposure(selectedPlayer.getID(), "Single", 1, day.getTime(), weekNum, i, 
						String.valueOf(day.getColor1()), String.valueOf(day.getColor2()), String.valueOf(day.getColor3()));
				dataGetter.deletePlayerExposure(selectedPlayer.getID(), 2, weekNum, i);
			} else if (longClickeds[i - k] && (day2.getWeekday().contains("S"))) {
				dataGetter.setPlayerExposure(selectedPlayer.getID(), "Split", 1, day.getTime(), weekNum, i, 
						String.valueOf(day.getColor1()), String.valueOf(day.getColor2()), String.valueOf(day.getColor3()));
				dataGetter.setPlayerExposure(selectedPlayer.getID(), "Split", 2, day2.getTime(), weekNum, i, 
						String.valueOf(day2.getColor1()), String.valueOf(day2.getColor2()), String.valueOf(day2.getColor3()));
			} else {
				dataGetter.setPlayerExposure(selectedPlayer.getID(), "Double", 1, day.getTime(), weekNum, i, 
						String.valueOf(day.getColor1()), String.valueOf(day.getColor2()), String.valueOf(day.getColor3()));				
				dataGetter.setPlayerExposure(selectedPlayer.getID(), "Double", 2, day2.getTime(), weekNum, i, 
								String.valueOf(day2.getColor1()), String.valueOf(day2.getColor2()), String.valueOf(day2.getColor3()));
			}
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout1:
			Intent intent1 = new Intent(this, PlayerViewActivity.class);
			ArrayList<PlayerDayBean> days11 = new ArrayList<PlayerDayBean>();
			ArrayList<PlayerDayBean> days21 = new ArrayList<PlayerDayBean>();

			for (int i = 0; i < Constants.SIZE; i++) {
				PlayerDayBean day = new PlayerDayBean();
				day.setColor1(((ColorDrawable) colors11.get(i).getBackground()).getColor());
				day.setColor2(((ColorDrawable) colors12.get(i).getBackground()).getColor());
				day.setColor3(((ColorDrawable) colors13.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days11.add(day);
				PlayerDayBean day2 = new PlayerDayBean();
				day2.setWeekday(texts3.get(i).getText().toString());
				day2.setColor1(((ColorDrawable) colors21.get(i).getBackground()).getColor());
				day2.setColor2(((ColorDrawable) colors22.get(i).getBackground()).getColor());
				day2.setColor3(((ColorDrawable) colors23.get(i).getBackground()).getColor());
				day2.setTime(texts2.get(i).getText().toString());
				days21.add(day2);
			}
			boolean[] longClickeds1 = new boolean[Constants.SIZE];
			for (int i = 0; i < Constants.SIZE; i++) {

				longClickeds1[i] = isLongClickeds.get(i).booleanValue();
			}
			intent1.putExtra(Constants.ISLONGCLICKEDS, longClickeds1);
			intent1.putParcelableArrayListExtra("days1", days11);
			intent1.putParcelableArrayListExtra("days2", days21);
			intent1.putExtra(PlayerViewActivity.ARG_DATES, dates.get(0));
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, PlayerViewActivity.class);
			ArrayList<PlayerDayBean> days12 = new ArrayList<PlayerDayBean>();
			ArrayList<PlayerDayBean> days22 = new ArrayList<PlayerDayBean>();

			for (int i = 7; i < Constants.SIZE + 7; i++) {
				PlayerDayBean day = new PlayerDayBean();
				day.setColor1(((ColorDrawable) colors11.get(i).getBackground()).getColor());
				day.setColor2(((ColorDrawable) colors12.get(i).getBackground()).getColor());
				day.setColor3(((ColorDrawable) colors13.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days12.add(day);
				PlayerDayBean day2 = new PlayerDayBean();
				day2.setWeekday(texts3.get(i).getText().toString());
				day2.setColor1(((ColorDrawable) colors21.get(i).getBackground()).getColor());
				day2.setColor2(((ColorDrawable) colors22.get(i).getBackground()).getColor());
				day2.setColor3(((ColorDrawable) colors23.get(i).getBackground()).getColor());
				day2.setTime(texts2.get(i).getText().toString());
				days22.add(day2);
			}
			boolean[] longClickeds2 = new boolean[Constants.SIZE];
			for (int i = 7; i < Constants.SIZE + 7; i++) {

				longClickeds2[i - 7] = isLongClickeds.get(i).booleanValue();
			}
			intent2.putExtra(Constants.ISLONGCLICKEDS, longClickeds2);
			intent2.putParcelableArrayListExtra("days1", days12);
			intent2.putParcelableArrayListExtra("days2", days22);
			intent2.putExtra(PlayerViewActivity.ARG_DATES, dates.get(1));
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, PlayerViewActivity.class);
			ArrayList<PlayerDayBean> days13 = new ArrayList<PlayerDayBean>();
			ArrayList<PlayerDayBean> days23 = new ArrayList<PlayerDayBean>();

			for (int i = 14; i < Constants.SIZE + 14; i++) {
				PlayerDayBean day = new PlayerDayBean();
				day.setColor1(((ColorDrawable) colors11.get(i).getBackground()).getColor());
				day.setColor2(((ColorDrawable) colors12.get(i).getBackground()).getColor());
				day.setColor3(((ColorDrawable) colors13.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days13.add(day);
				PlayerDayBean day2 = new PlayerDayBean();
				day2.setWeekday(texts3.get(i).getText().toString());
				day2.setColor1(((ColorDrawable) colors21.get(i).getBackground()).getColor());
				day2.setColor2(((ColorDrawable) colors22.get(i).getBackground()).getColor());
				day2.setColor3(((ColorDrawable) colors23.get(i).getBackground()).getColor());
				day2.setTime(texts2.get(i).getText().toString());
				days23.add(day2);
			}
			boolean[] longClickeds3 = new boolean[Constants.SIZE];
			for (int i = 14; i < Constants.SIZE + 14; i++) {

				longClickeds3[i - 14] = isLongClickeds.get(i).booleanValue();
			}
			intent3.putExtra(Constants.ISLONGCLICKEDS, longClickeds3);
			intent3.putParcelableArrayListExtra("days1", days13);
			intent3.putParcelableArrayListExtra("days2", days23);
			intent3.putExtra(PlayerViewActivity.ARG_DATES, dates.get(2));
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, PlayerViewActivity.class);
			ArrayList<PlayerDayBean> days14 = new ArrayList<PlayerDayBean>();
			ArrayList<PlayerDayBean> days24 = new ArrayList<PlayerDayBean>();

			for (int i = 21; i < Constants.SIZE + 21; i++) {
				PlayerDayBean day = new PlayerDayBean();
				day.setColor1(((ColorDrawable) colors11.get(i).getBackground()).getColor());
				day.setColor2(((ColorDrawable) colors12.get(i).getBackground()).getColor());
				day.setColor3(((ColorDrawable) colors13.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days14.add(day);
				PlayerDayBean day2 = new PlayerDayBean();
				day2.setWeekday(texts3.get(i).getText().toString());
				day2.setColor1(((ColorDrawable) colors21.get(i).getBackground()).getColor());
				day2.setColor2(((ColorDrawable) colors22.get(i).getBackground()).getColor());
				day2.setColor3(((ColorDrawable) colors23.get(i).getBackground()).getColor());
				day2.setTime(texts2.get(i).getText().toString());
				days24.add(day2);
			}
			boolean[] longClickeds4 = new boolean[Constants.SIZE];
			for (int i = 21; i < Constants.SIZE + 21; i++) {

				longClickeds4[i - 21] = isLongClickeds.get(i).booleanValue();
			}
			intent4.putExtra(Constants.ISLONGCLICKEDS, longClickeds4);
			intent4.putParcelableArrayListExtra("days1", days14);
			intent4.putParcelableArrayListExtra("days2", days24);
			intent4.putExtra(PlayerViewActivity.ARG_DATES, dates.get(3));
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
