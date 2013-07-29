package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.sportsfire.exposure.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

	private Spinner mSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputteam);

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
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 0; i < longClickeds.length; i++) {

					if (longClickeds[i]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
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
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 7; i < 7 + longClickeds.length; i++) {

					if (longClickeds[i - 7]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
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
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 14; i < 14 + longClickeds.length; i++) {

					if (longClickeds[i - 14]) {
						layouts.get(i).setVisibility(View.VISIBLE);
					} else {
						layouts.get(i).setVisibility(View.GONE);
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
				boolean[] longClickeds = data
						.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
				for (int i = 21; i < 21 + longClickeds.length; i++) {

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
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, TeamViewActivity.class);
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, TeamViewActivity.class);
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, TeamViewActivity.class);
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
