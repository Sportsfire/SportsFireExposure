package com.WeiGu.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.WeiGu.SporysFireExposure.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

public class TeamMainActivity extends Activity implements OnClickListener {

	private final int REQUESTCODE1 = 0x01;
	private final int REQUESTCODE2 = 0x02;
	private final int REQUESTCODE3 = 0x03;
	private final int REQUESTCODE4 = 0x04;

	private MyApp myApp;

	private int layout_ids[] = { R.id.layout1, R.id.layout2, R.id.layout3,
			R.id.layout4 };
	private int item_ids[] = { R.id.item1, R.id.item2,
			R.id.item3, R.id.item4, R.id.item5, R.id.item6,
			R.id.item7, R.id.item8, R.id.item9,
			R.id.item10, R.id.item11, R.id.item12,
			R.id.item13, R.id.item14 };
	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<ImageView> colors1 = new ArrayList<ImageView>();
	private List<ImageView> colors2 = new ArrayList<ImageView>();
	private List<Boolean> isLongClickeds = new ArrayList<Boolean>();

	private Spinner mSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_layout);

		myApp = (MyApp) getApplication();

		String weeks[] = { "Week1-4", "Week5-8", "Week9-12", "Week13-16",
				"Week17-20", "Week21-24", "Week25-28", "Week29-32",
				"Week33-36", "Week37-40", "Week41-44", "Week45-48", "Week49-52" };
		mSpinner = (Spinner) findViewById(R.id.spinner_week);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, weeks);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mSpinner.setAdapter(adapter);
		mSpinner.setSelection(myApp.getSpinnerPosition(), true);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
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

		for (int i = 0; i < layout_ids.length; i++) {
			for (int j = 0; j < item_ids.length; j++) {

				if (j % 2 == 0) {

					TextView tv_time = (TextView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.tv_time);
					texts1.add(tv_time);
					isLongClickeds.add(false);

					ImageView iv = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color);
					colors1.add(iv);

				} else {

					LinearLayout layout = (LinearLayout) findViewById(
							layout_ids[i]).findViewById(item_ids[j]);
					layouts.add(layout);

					TextView tv_day = (TextView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.tv_weekday);
					tv_day.setText(" \n ");
					texts3.add(tv_day);

					ImageView iv = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color);
					colors2.add(iv);

					TextView tv_time = (TextView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.tv_time);
					texts2.add(tv_time);
				}
			}
		}

	}

	private void initItem() {

		for (int i = 0; i < layout_ids.length; i++) {

			for (int j = 0; j < item_ids.length; j++) {

				if (j % 2 == 0) {

					switch (j / 2) {
					case 0:
						TextView tv0 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv0.setText("S\n7");
						break;
					case 1:
						TextView tv1 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv1.setText("M\n8");

						break;
					case 2:
						TextView tv2 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv2.setText("T\n9");

						break;
					case 3:
						TextView tv3 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv3.setText("W\n10");

						break;
					case 4:
						TextView tv4 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv4.setText("Th\n11");

						break;
					case 5:
						TextView tv5 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv5.setText("F\n12");

						break;
					case 6:
						TextView tv6 = (TextView) findViewById(layout_ids[i])
								.findViewById(item_ids[j]).findViewById(
										R.id.tv_weekday);
						tv6.setText("S\n13");

						break;

					default:
						break;
					}

				} else {
					TextView tv = (TextView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
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
			
			ArrayList<TeamDayBean> days1 = data
					.getParcelableArrayListExtra("days1");
			ArrayList<TeamDayBean> days2 = data
					.getParcelableArrayListExtra("days2");

			if (requestCode == REQUESTCODE1) {
				

				for (int i = 0; i < Constants.SIZE; i++) {
					TeamDayBean day = days1.get(i);
					colors1.get(i).setBackgroundColor(day.getColor());
					texts1.get(i).setText(day.getTime());
				}

				for (int i = 0; i < Constants.SIZE; i++) {
					TeamDayBean day = days2.get(i);
					texts3.get(i).setText(day.getWd());
					colors2.get(i).setBackgroundColor(day.getColor());
					texts2.get(i).setText(day.getTime());
				}
				
//				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
//				for (int i = 0; i < times1.length; i++) {
//					texts1.get(i).setText(times1[i]);
//				}
//				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
//				for (int i = 0; i < images1.length; i++) {
//					colors1.get(i).setBackgroundColor(images1[i]);
//				}
//				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
//				for (int i = 0; i < times2.length; i++) {
//					texts2.get(i).setText(times2[i]);
//				}
//				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
//				for (int i = 0; i < images2.length; i++) {
//					colors2.get(i).setBackgroundColor(images2[i]);
//				}
//				String[] days = data.getStringArrayExtra(Constants.DAYS);
//				for (int i = 0; i < days.length; i++) {
//					texts3.get(i).setText(days[i]);
//					System.out.println(days[i]);
//				}
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 0; i < longClickeds.length; i++) {
					
					isLongClickeds.set(i, longClickeds[i]);

					if (longClickeds[i]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}
			} else if (requestCode == REQUESTCODE2) {

				for (int i = 7; i < 7 + Constants.SIZE; i++) {
					TeamDayBean day = days1.get(i - 7);
					colors1.get(i).setBackgroundColor(day.getColor());
					texts1.get(i).setText(day.getTime());
				}

				for (int i = 7; i < 7 + Constants.SIZE; i++) {
					TeamDayBean day = days2.get(i - 7);
					texts3.get(i).setText(day.getWd());
					colors2.get(i).setBackgroundColor(day.getColor());
					texts2.get(i).setText(day.getTime());
				}

//				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
//				for (int i = 7; i < 7 + times1.length; i++) {
//					texts1.get(i).setText(times1[i - 7]);
//				}
//				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
//				for (int i = 7; i < 7 + images1.length; i++) {
//					colors1.get(i).setBackgroundColor(images1[i - 7]);
//				}
//				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
//				for (int i = 7; i < 7 + times2.length; i++) {
//					texts2.get(i).setText(times2[i - 7]);
//				}
//				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
//				for (int i = 7; i < 7 + images2.length; i++) {
//					colors2.get(i).setBackgroundColor(images2[i - 7]);
//				}
//				String[] days = data.getStringArrayExtra(Constants.DAYS);
//				for (int i = 7; i < 7 + days.length; i++) {
//					texts3.get(i).setText(days[i - 7]);
//				}
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 7; i < 7 + longClickeds.length; i++) {
					
					isLongClickeds.set(i, longClickeds[i-7]);

					if (longClickeds[i - 7]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}

			} else if (requestCode == REQUESTCODE3) {
				
				for (int i = 14; i < 14 + Constants.SIZE; i++) {
					TeamDayBean day = days1.get(i - 14);
					colors1.get(i).setBackgroundColor(day.getColor());
					texts1.get(i).setText(day.getTime());
				}

				for (int i = 14; i < 14 + Constants.SIZE; i++) {
					TeamDayBean day = days2.get(i - 14);
					texts3.get(i).setText(day.getWd());
					colors2.get(i).setBackgroundColor(day.getColor());
					texts2.get(i).setText(day.getTime());
				}

//				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
//				for (int i = 14; i < 14 + times1.length; i++) {
//					texts1.get(i).setText(times1[i - 14]);
//				}
//				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
//				for (int i = 14; i < 14 + images1.length; i++) {
//					colors1.get(i).setBackgroundColor(images1[i - 14]);
//				}
//				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
//				for (int i = 14; i < 14 + times2.length; i++) {
//					texts2.get(i).setText(times2[i - 14]);
//				}
//				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
//				for (int i = 14; i < 14 + images2.length; i++) {
//					colors2.get(i).setBackgroundColor(images2[i - 14]);
//				}
//				String[] days = data.getStringArrayExtra(Constants.DAYS);
//				for (int i = 14; i < 14 + days.length; i++) {
//					texts3.get(i).setText(days[i - 14]);
//				}
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 14; i < 14 + longClickeds.length; i++) {
					
					isLongClickeds.set(i, longClickeds[i-14]);

					if (longClickeds[i - 14]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
					}

				}

			} else if (requestCode == REQUESTCODE4) {
				
				for (int i = 21; i < 21 + Constants.SIZE; i++) {
					TeamDayBean day = days1.get(i - 21);
					colors1.get(i).setBackgroundColor(day.getColor());
					texts1.get(i).setText(day.getTime());
				}

				for (int i = 21; i < 21 + Constants.SIZE; i++) {
					TeamDayBean day = days2.get(i - 21);
					texts3.get(i).setText(day.getWd());
					colors2.get(i).setBackgroundColor(day.getColor());
					texts2.get(i).setText(day.getTime());
				}

//				String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
//				for (int i = 21; i < 21 + times1.length; i++) {
//					texts1.get(i).setText(times1[i - 21]);
//				}
//				int[] images1 = data.getIntArrayExtra(Constants.COLORS11);
//				for (int i = 21; i < 21 + images1.length; i++) {
//					colors1.get(i).setBackgroundColor(images1[i - 21]);
//				}
//				String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
//				for (int i = 21; i < 21 + times2.length; i++) {
//					texts2.get(i).setText(times2[i - 21]);
//				}
//				int[] images2 = data.getIntArrayExtra(Constants.COLORS21);
//				for (int i = 21; i < 21 + images2.length; i++) {
//					colors2.get(i).setBackgroundColor(images2[i - 21]);
//				}
//				String[] days = data.getStringArrayExtra(Constants.DAYS);
//				for (int i = 21; i < 21 + days.length; i++) {
//					texts3.get(i).setText(days[i - 21]);
//				}
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 21; i < 21 + longClickeds.length; i++) {

					isLongClickeds.set(i, longClickeds[i-21]);
					if (longClickeds[i - 21]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
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
			ArrayList<TeamDayBean> days11 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days21 = new ArrayList<TeamDayBean>();
			
			for (int i = 0; i < Constants.SIZE; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i)
						.getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days11.add(day);
			}
			
			for (int i = 0; i < Constants.SIZE; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i)
						.getBackground()).getColor());
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
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days12 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days22 = new ArrayList<TeamDayBean>();
			
			for (int i = 7; i < Constants.SIZE + 7; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i).getBackground())
						.getColor());
				day.setTime(texts1.get(i).getText().toString());
				days12.add(day);
			}
			
			for (int i = 7; i < Constants.SIZE+7; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i)
						.getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days22.add(day);
			}
			boolean[] longClickeds2 = new boolean[Constants.SIZE];
			for (int i = 7; i < Constants.SIZE+7; i++) {

				longClickeds2[i-7] = isLongClickeds.get(i).booleanValue();
			}
			intent2.putExtra(Constants.ISLONGCLICKEDS, longClickeds2);
			intent2.putParcelableArrayListExtra("days1", days12);
			intent2.putParcelableArrayListExtra("days2", days22);
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days13 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days23 = new ArrayList<TeamDayBean>();
			
			for (int i = 14; i < Constants.SIZE+14; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i)
						.getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days13.add(day);
			}
			
			for (int i = 14; i < Constants.SIZE+14; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i)
						.getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days23.add(day);
			}
			boolean[] longClickeds3 = new boolean[Constants.SIZE];
			for (int i = 14; i < Constants.SIZE+14; i++) {

				longClickeds3[i-14] = isLongClickeds.get(i).booleanValue();
			}
			intent3.putExtra(Constants.ISLONGCLICKEDS, longClickeds3);
			intent3.putParcelableArrayListExtra("days1", days13);
			intent3.putParcelableArrayListExtra("days2", days23);
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, TeamViewActivity.class);
			ArrayList<TeamDayBean> days14 = new ArrayList<TeamDayBean>();
			ArrayList<TeamDayBean> days24 = new ArrayList<TeamDayBean>();
			
			for (int i = 21; i < Constants.SIZE+21; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setColor(((ColorDrawable) colors1.get(i)
						.getBackground()).getColor());
				day.setTime(texts1.get(i).getText().toString());
				days14.add(day);
			}
			
			for (int i = 21; i < Constants.SIZE+21; i++) {
				TeamDayBean day = new TeamDayBean();
				day.setWd(texts3.get(i).getText().toString());
				day.setColor(((ColorDrawable) colors2.get(i)
						.getBackground()).getColor());
				day.setTime(texts2.get(i).getText().toString());
				days24.add(day);
			}
			
			boolean[] longClickeds4 = new boolean[Constants.SIZE];
			for (int i = 21; i < Constants.SIZE+21; i++) {

				longClickeds4[i-21] = isLongClickeds.get(i).booleanValue();
			}
			intent4.putExtra(Constants.ISLONGCLICKEDS, longClickeds4);
			intent4.putParcelableArrayListExtra("days1", days14);
			intent4.putParcelableArrayListExtra("days2", days24);
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
