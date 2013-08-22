/*package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.sportsfire.exposure.R;
import com.sportsfire.exposure.objects.ExposureData;
import com.sportsfire.exposure.objects.Squad;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
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

public class InputTeam extends Activity implements OnClickListener {

	private final int REQUESTCODE1 = 0x01;
	private final int REQUESTCODE2 = 0x02;
	private final int REQUESTCODE3 = 0x03;
	private final int REQUESTCODE4 = 0x04;

	private ArrayList<ArrayList<String>> dates = new ArrayList<ArrayList<String>>();
	private MyApp myApp;

	private int layout_ids[] = { R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4 };
	private int item_ids[] = { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,
			R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14 };
	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<ImageView> colors1 = new ArrayList<ImageView>();
	private List<ImageView> colors2 = new ArrayList<ImageView>();
	ExposureData dataGetter;
	private Spinner mSpinner;
	private Squad squad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputteam);
		myApp = (MyApp) getApplication();

		String weeks[] = { "Week1-4", "Week5-8", "Week9-12", "Week13-16", "Week17-20", "Week21-24", "Week25-28",
				"Week29-32", "Week33-36", "Week37-40", "Week41-44", "Week45-48", "Week49-52" };
		mSpinner = (Spinner) findViewById(R.id.spinner_week);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weeks);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mSpinner.setAdapter(adapter);
		mSpinner.setSelection(myApp.getSpinnerPosition(), true);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				myApp.setSpinnerPosition(arg2);
				Intent intent = getIntent();
				finish();
				startActivity(intent);

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

		View layout1 = findViewById(R.id.layout1);
		layout1.setOnClickListener(this);
		View layout2 = findViewById(R.id.layout2);
		layout2.setOnClickListener(this);
		View layout3 = findViewById(R.id.layout3);
		layout3.setOnClickListener(this);
		View layout4 = findViewById(R.id.layout4);
		layout4.setOnClickListener(this);

		int groupNum = (mSpinner.getSelectedItemPosition() * 4);
		for (int i = 0; i < layout_ids.length; i++) {
			for (int j = 0; j < item_ids.length; j++) {
				if (j % 2 == 0) {
					TextView tv_time = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.tv_time);
					texts1.add(tv_time);
					ImageView iv = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.iv_color);
					colors1.add(iv);

				} else {

					LinearLayout layout = (LinearLayout) findViewById(layout_ids[i]).findViewById(item_ids[j]);
					layouts.add(layout);

					TextView tv_day = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.tv_weekday);
					tv_day.setText(" \n ");
					texts3.add(tv_day);

					ImageView iv = (ImageView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.iv_color);
					colors2.add(iv);

					TextView tv_time = (TextView) findViewById(layout_ids[i]).findViewById(item_ids[j]).findViewById(
							R.id.tv_time);
					texts2.add(tv_time);
				}
				ArrayList<ArrayList<String>> exposure = dataGetter.getSquadExposure(squad.getID(), groupNum+i, j);
				dataGetter.logAll(squad.getID());
				if (exposure != null) {
					Log.e("SIZE", String.valueOf(exposure.size()));
					colors1.get(j).setBackgroundColor(Integer.valueOf(exposure.get(0).get(0)));
					texts1.get(j).setText(exposure.get(0).get(1));
					if (exposure.size() == 2) {
						colors2.get(j).setBackgroundColor(Integer.valueOf(exposure.get(1).get(0)));
						texts2.get(j).setText(exposure.get(1).get(1));
						String type = exposure.get(1).get(2);
						if (type.charAt(0) == 'S') {
							texts3.get(i).setText("S\nP");
						} else {
							texts3.get(i).setText("D\nO");
						}
					}
				}
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
					tv.setText(" \n ");
				}

			}

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			myApp.setSpinnerPosition(0);
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			if (requestCode == REQUESTCODE1) {
				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
				for (int i = 0; i < times1.length; i++) {
					texts1.get(i).setText(times1[i]);
				}
				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
				for (int i = 0; i < images1.length; i++) {
					colors1.get(i).setBackgroundColor(images1[i]);
				}
				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
				for (int i = 0; i < times2.length; i++) {
					texts2.get(i).setText(times2[i]);
				}
				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
				for (int i = 0; i < images2.length; i++) {
					colors2.get(i).setBackgroundColor(images2[i]);
				}
				String[] days = data.getStringArrayExtra(Constants.DAYS);
				for (int i = 0; i < days.length; i++) {
					texts3.get(i).setText(days[i]);
					System.out.println(days[i]);
				}
				int[] longClickeds = data.getIntArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 0; i < longClickeds.length; i++) {

					if (longClickeds[i] > 0) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}
				int weekNum = (mSpinner.getSelectedItemPosition() * 4);
				for (int i = 0; i < times1.length; i++) {
					if (longClickeds[i] == 0) {
						dataGetter.setSquadExposure(squad.getID(), "Single", times1[i], weekNum, i,
								String.valueOf(images1[i]));
					} else if (longClickeds[i] == 1) {
						dataGetter.setSquadExposure(squad.getID(), "Split 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Split 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					} else {
						dataGetter.setSquadExposure(squad.getID(), "Double 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Double 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					}
				}

			} else if (requestCode == REQUESTCODE2) {

				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
				for (int i = 7; i < 7 + times1.length; i++) {
					texts1.get(i).setText(times1[i - 7]);
				}
				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
				for (int i = 7; i < 7 + images1.length; i++) {
					colors1.get(i).setBackgroundColor(images1[i - 7]);
				}
				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
				for (int i = 7; i < 7 + times2.length; i++) {
					texts2.get(i).setText(times2[i - 7]);
				}
				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
				for (int i = 7; i < 7 + images2.length; i++) {
					colors2.get(i).setBackgroundColor(images2[i - 7]);
				}
				String[] days = data.getStringArrayExtra(Constants.DAYS);
				for (int i = 7; i < 7 + days.length; i++) {
					texts3.get(i).setText(days[i - 7]);
				}
				int[] longClickeds = data.getIntArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 7; i < 7 + longClickeds.length; i++) {

					if (longClickeds[i - 7] > 0) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}
				int weekNum = (mSpinner.getSelectedItemPosition() * 4) + 1;
				for (int i = 7; i < 7 + times1.length; i++) {
					if (longClickeds[i] == 0) {
						dataGetter.setSquadExposure(squad.getID(), "Single", times1[i], weekNum, i,
								String.valueOf(images1[i]));
					} else if (longClickeds[i] == 1) {
						dataGetter.setSquadExposure(squad.getID(), "Split 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Split 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					} else {
						dataGetter.setSquadExposure(squad.getID(), "Double 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Double 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					}
				}

			} else if (requestCode == REQUESTCODE3) {

				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
				for (int i = 14; i < 14 + times1.length; i++) {
					texts1.get(i).setText(times1[i - 14]);
				}
				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
				for (int i = 14; i < 14 + images1.length; i++) {
					colors1.get(i).setBackgroundColor(images1[i - 14]);
				}
				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
				for (int i = 14; i < 14 + times2.length; i++) {
					texts2.get(i).setText(times2[i - 14]);
				}
				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
				for (int i = 14; i < 14 + images2.length; i++) {
					colors2.get(i).setBackgroundColor(images2[i - 14]);
				}
				String[] days = data.getStringArrayExtra(Constants.DAYS);
				for (int i = 14; i < 14 + days.length; i++) {
					texts3.get(i).setText(days[i - 14]);
				}
				int[] longClickeds = data.getIntArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 14; i < 14 + longClickeds.length; i++) {

					if (longClickeds[i - 14] > 0) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}
				int weekNum = (mSpinner.getSelectedItemPosition() * 4) + 1;
				for (int i = 14; i < 14 + times1.length; i++) {
					if (longClickeds[i] == 0) {
						dataGetter.setSquadExposure(squad.getID(), "Single", times1[i], weekNum, i,
								String.valueOf(images1[i]));
					} else if (longClickeds[i] == 1) {
						dataGetter.setSquadExposure(squad.getID(), "Split 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Split 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					} else {
						dataGetter.setSquadExposure(squad.getID(), "Double 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Double 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					}
				}

			} else if (requestCode == REQUESTCODE4) {

				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
				for (int i = 21; i < 21 + times1.length; i++) {
					texts1.get(i).setText(times1[i - 21]);
				}
				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
				for (int i = 21; i < 21 + images1.length; i++) {
					colors1.get(i).setBackgroundColor(images1[i - 21]);
				}
				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
				for (int i = 21; i < 21 + times2.length; i++) {
					texts2.get(i).setText(times2[i - 21]);
				}
				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
				for (int i = 21; i < 21 + images2.length; i++) {
					colors2.get(i).setBackgroundColor(images2[i - 21]);
				}
				String[] days = data.getStringArrayExtra(Constants.DAYS);
				for (int i = 21; i < 21 + days.length; i++) {
					texts3.get(i).setText(days[i - 21]);
				}
				int[] longClickeds = data.getIntArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 21; i < 21 + longClickeds.length; i++) {

					if (longClickeds[i - 21] > 0) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}
				int weekNum = (mSpinner.getSelectedItemPosition() * 4) + 1;
				for (int i = 21; i < 21 + times1.length; i++) {
					if (longClickeds[i] == 0) {
						dataGetter.setSquadExposure(squad.getID(), "Single", times1[i], weekNum, i,
								String.valueOf(images1[i]));
					} else if (longClickeds[i] == 1) {
						dataGetter.setSquadExposure(squad.getID(), "Split 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Split 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					} else {
						dataGetter.setSquadExposure(squad.getID(), "Double 1", times1[i], weekNum, i,
								String.valueOf(images1[i]));
						dataGetter.setSquadExposure(squad.getID(), "Double 2", times2[i], weekNum, i,
								String.valueOf(images2[i]));
					}
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
			intent1.putExtra(TeamViewActivity.ARG_DATES, dates.get(0));
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, TeamViewActivity.class);
			intent2.putExtra(TeamViewActivity.ARG_DATES, dates.get(1));
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, TeamViewActivity.class);
			intent3.putExtra(TeamViewActivity.ARG_DATES, dates.get(2));
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, TeamViewActivity.class);
			intent4.putExtra(TeamViewActivity.ARG_DATES, dates.get(3));
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
*/