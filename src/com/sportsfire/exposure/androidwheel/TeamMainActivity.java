package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sportsfire.exposure.R;
import com.sportsfire.objects.Squad;

public class TeamMainActivity extends Activity implements OnClickListener {

	private final int REQUESTCODE1 = 0x01;
	private final int REQUESTCODE2 = 0x02;
	private final int REQUESTCODE3 = 0x03;
	private final int REQUESTCODE4 = 0x04;

	// private MyApp myApp;
	public static final String ARG_SEASON_ID = "argumentSeason";
	public static final String ARG_SQUAD = "argumentSquad";
	private int layout_ids[];

	private int item_ids[] = { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,
			R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14 };

	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<ImageView> colors1 = new ArrayList<ImageView>();
	private List<ImageView> colors2 = new ArrayList<ImageView>();
	private List<Boolean> isLongClickeds = new ArrayList<Boolean>();

	private Spinner mSpinner;
	private ExposureData dataGetter;
	private ArrayList<ArrayList<String>> dates = new ArrayList<ArrayList<String>>();
	private Squad squad;
	private Boolean spinnerInit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputteam);
		layout_ids = new int[] { R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4 };
		// myApp = (MyApp) getApplication();
		String seasonId = getIntent().getStringExtra(ARG_SEASON_ID);
		dataGetter = new ExposureData(this, seasonId);
		squad = getIntent().getParcelableExtra(ARG_SQUAD);
		String weeks[] = { "Week1-4", "Week5-8", "Week9-12", "Week13-16", "Week17-20", "Week21-24", "Week25-28",
				"Week29-32", "Week33-36", "Week37-40", "Week41-44", "Week45-48", "Week49-52" };
		mSpinner = (Spinner) findViewById(R.id.spinner_week);
		dataGetter.logAll(squad.getID());
		dataGetter.logAll3();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				weeks);
		// adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mSpinner.setAdapter(adapter);
		mSpinner.setSelection(getIntent().getIntExtra("WEEK", 0));
		// mSpinner.setSelection(myApp.getSpinnerPosition(), true);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (spinnerInit == true) {
					Intent intent = getIntent();
					intent.putExtra("WEEK", arg2);
					finish();
					startActivity(intent);
				}
				spinnerInit = true;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		initLayout();
		initItem();

	}

	private void initLayout() {
		int groupNum = (mSpinner.getSelectedItemPosition() * 4);
		for (int i = 0; i < layout_ids.length; i++) {
			findViewById(layout_ids[i]).setOnClickListener(this);
			for (int j = 0; j < item_ids.length; j = j + 2) {
				List<ArrayList<String>> exposure = dataGetter.getSquadExposure(squad.getID(), groupNum + i, j / 2);

				TextView tv_time = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.tv_time);

				ImageView iv = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
						R.id.iv_color);
				LinearLayout layout = (LinearLayout) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]);

				TextView tv_day = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.tv_weekday);

				ImageView iv2 = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.iv_color);
				TextView tv_time2 = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j + 1]).findViewById(
						R.id.tv_time);
				layouts.add(layout);
				tv_day.setText(" \n ");
				iv.setBackgroundColor(0);
				layout.setVisibility(View.GONE);
				Boolean showTwo = false;
				if (exposure != null) {
					try {
						tv_time.setText("  " + exposure.get(0).get(1) + "  ");
						iv.setBackgroundColor(Integer.valueOf(exposure.get(0).get(0)));
						if (exposure.size() > 1) {
							showTwo = true;
							layout.setVisibility(View.VISIBLE);
							if (exposure.get(1).get(2).startsWith("S")) {
								tv_day.setText("S\nP");
								tv_time2.setText("  " + exposure.get(1).get(1) + "  ");
								iv2.setBackgroundColor(Integer.valueOf(exposure.get(0).get(0)));
							} else {
								tv_day.setText("D\nO");
								tv_time2.setText("  " + exposure.get(1).get(1) + "  ");
								iv2.setBackground(iv.getBackground());
							}
						}
					} catch (NumberFormatException e) {
						iv.setBackgroundColor(0);
						iv2.setBackgroundColor(0);
						e.printStackTrace();
					}
				}
				isLongClickeds.add(showTwo);
				texts1.add(tv_time);
				colors1.add(iv);
				texts2.add(tv_time2);
				texts3.add(tv_day);
				colors2.add(iv2);
			}
		}

	}

	private void initItem() {

		for (int i = 0; i < layout_ids.length; i++) {
			ArrayList<String> dateList = dataGetter.getDaysOfWeek((mSpinner.getSelectedItemPosition() * 4) + i);
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

				saveChanges(data, 0);

			} else if (requestCode == REQUESTCODE2) {

				saveChanges(data, 7);

			} else if (requestCode == REQUESTCODE3) {

				saveChanges(data, 14);

			} else if (requestCode == REQUESTCODE4) {

				saveChanges(data, 21);

			}
		}
	}

	private void saveChanges(Intent data, int k) {
		int weekNum = (mSpinner.getSelectedItemPosition() * 4);
		ArrayList<TeamDayBean> days1 = data.getParcelableArrayListExtra("days1");
		ArrayList<TeamDayBean> days2 = data.getParcelableArrayListExtra("days2");
		boolean[] longClickeds = data.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
		for (int i = k; i < k + Constants.SIZE; i++) {
			TeamDayBean day1 = days1.get(i - k);
			TeamDayBean day2 = days2.get(i - k);
			colors1.get(i).setBackgroundColor(day1.getColor());
			texts1.get(i).setText(day1.getTime());
			texts3.get(i).setText(day2.getWd());
			colors2.get(i).setBackgroundColor(day2.getColor());
			texts2.get(i).setText(day2.getTime());
			isLongClickeds.set(i, longClickeds[i - k]);
			String value1 = String.valueOf(day1.getColor());
			String value2 = String.valueOf(day2.getColor());
			if (!longClickeds[i - k]) {
				layouts.get(i).setVisibility(View.GONE);
				dataGetter.setSquadExposure(squad.getID(), "Single", 1, day1.getTime(), weekNum, i, value1);
				dataGetter.deleteSquadExposure(squad.getID(), 2, weekNum, i);
			} else {
				layouts.get(i).setVisibility(View.VISIBLE);
				if (day2.getWd().contains("S")) {
					dataGetter.setSquadExposure(squad.getID(), "Split", 1, day1.getTime(), weekNum, i, value1);
					dataGetter.setSquadExposure(squad.getID(), "Split", 2, day2.getTime(), weekNum, i, value2);
				} else {
					dataGetter.setSquadExposure(squad.getID(), "Double", 1, day1.getTime(), weekNum, i, value1);
					dataGetter.setSquadExposure(squad.getID(), "Double", 2, day2.getTime(), weekNum, i, value1);
				}
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
			Intent intent1 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days11 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days21 = new ArrayList<TeamDayBean>();

			for (int i = 0; i < Constants.SIZE; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days11.add(day);
			}

			for (int i = 0; i < Constants.SIZE; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i).getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days21.add(day);
			}
			boolean[] longClickeds1 = new boolean[Constants.SIZE];
			for (int i = 0; i < Constants.SIZE; i++) {

				longClickeds1[i] = isLongClickeds.get(i).booleanValue();
			}
			intent1.putExtra(Constants.ISLONGCLICKEDS, longClickeds1);
			intent1.putParcelableArrayListExtra("days1", days11);
			intent1.putParcelableArrayListExtra("days2", days21);
			intent1.putExtra(TeamViewActivity.ARG_DATES, dates.get(0));
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days12 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days22 = new ArrayList<TeamDayBean>();

			for (int i = 7; i < Constants.SIZE + 7; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days12.add(day);
			}

			for (int i = 7; i < Constants.SIZE + 7; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i).getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days22.add(day);
			}
			boolean[] longClickeds2 = new boolean[Constants.SIZE];
			for (int i = 7; i < Constants.SIZE + 7; i++) {

				longClickeds2[i - 7] = isLongClickeds.get(i).booleanValue();
			}
			intent2.putExtra(Constants.ISLONGCLICKEDS, longClickeds2);
			intent2.putParcelableArrayListExtra("days1", days12);
			intent2.putParcelableArrayListExtra("days2", days22);
			intent2.putExtra(TeamViewActivity.ARG_DATES, dates.get(1));
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days13 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days23 = new ArrayList<TeamDayBean>();

			for (int i = 14; i < Constants.SIZE + 14; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days13.add(day);
			}

			for (int i = 14; i < Constants.SIZE + 14; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i).getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days23.add(day);
			}
			boolean[] longClickeds3 = new boolean[Constants.SIZE];
			for (int i = 14; i < Constants.SIZE + 14; i++) {

				longClickeds3[i - 14] = isLongClickeds.get(i).booleanValue();
			}
			intent3.putExtra(Constants.ISLONGCLICKEDS, longClickeds3);
			intent3.putParcelableArrayListExtra("days1", days13);
			intent3.putParcelableArrayListExtra("days2", days23);
			intent3.putExtra(TeamViewActivity.ARG_DATES, dates.get(2));
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days14 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days24 = new ArrayList<TeamDayBean>();

			for (int i = 21; i < Constants.SIZE + 21; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i).getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days14.add(day);
			}

			for (int i = 21; i < Constants.SIZE + 21; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i).getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days24.add(day);
			}

			boolean[] longClickeds4 = new boolean[Constants.SIZE];
			for (int i = 21; i < Constants.SIZE + 21; i++) {

				longClickeds4[i - 21] = isLongClickeds.get(i).booleanValue();
			}
			intent4.putExtra(Constants.ISLONGCLICKEDS, longClickeds4);
			intent4.putParcelableArrayListExtra("days1", days14);
			intent4.putParcelableArrayListExtra("days2", days24);
			intent4.putExtra(TeamViewActivity.ARG_DATES, dates.get(3));
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
