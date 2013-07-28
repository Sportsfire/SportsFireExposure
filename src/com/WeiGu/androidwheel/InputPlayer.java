package com.WeiGu.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.WeiGu.SporysFireExposure.R;

import android.app.Activity;
import android.content.Intent;
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

public class InputPlayer extends Activity implements OnClickListener {

	private final int REQUESTCODE1 = 0x01;
	private final int REQUESTCODE2 = 0x02;
	private final int REQUESTCODE3 = 0x03;
	private final int REQUESTCODE4 = 0x04;

	private MyApp myApp;

	private int layout_ids[] = { R.id.layout1, R.id.layout2, R.id.layout3,
			R.id.layout4 };
	private int item_ids[] = { R.id.item1, R.id.item2, R.id.item3, R.id.item4,
			R.id.item5, R.id.item6, R.id.item7, R.id.item8, R.id.item9,
			R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14 };
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

	private Spinner mWeekSpinner, mPlayerSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputplayer);

		myApp = (MyApp) getApplication();

		String weeks[] = { "Week1-4", "Week5-8", "Week9-12", "Week13-16",
				"Week17-20", "Week21-24", "Week25-28", "Week29-32",
				"Week33-36", "Week37-40", "Week41-44", "Week45-48", "Week49-52" };
		String players[] = { "Player1", "Player2" };
		mWeekSpinner = (Spinner) findViewById(R.id.spinner_week);
		mPlayerSpinner = (Spinner) findViewById(R.id.spinner_player);

		ArrayAdapter<String> weekAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, weeks);
		weekAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, players);
		playerAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		mWeekSpinner.setAdapter(weekAdapter);
		mPlayerSpinner.setAdapter(playerAdapter);
		mWeekSpinner.setSelection(myApp.getSpinnerPosition(), true);
		mWeekSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

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

					ImageView iv1 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color1);
					colors11.add(iv1);
					ImageView iv2 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color2);
					colors12.add(iv2);
					ImageView iv3 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color3);
					colors13.add(iv3);

				} else {

					LinearLayout layout = (LinearLayout) findViewById(
							layout_ids[i]).findViewById(item_ids[j]);
					layouts.add(layout);

					TextView tv_day = (TextView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.tv_weekday);
					tv_day.setText(" \n ");
					texts3.add(tv_day);

					ImageView iv1 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color1);
					iv1.setBackgroundColor(0xFF8A2BE2);
					colors21.add(iv1);
					ImageView iv2 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color2);
					iv2.setBackgroundColor(0xFF8A2BE2);
					colors22.add(iv2);
					ImageView iv3 = (ImageView) findViewById(layout_ids[i])
							.findViewById(item_ids[j]).findViewById(
									R.id.iv_color3);
					iv3.setBackgroundColor(0xFF8A2BE2);
					colors23.add(iv3);

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
		String[] times1 = data.getStringArrayExtra(Constants.TIMES1);
		for (int i = k; i < k + times1.length; i++) {
			texts1.get(i).setText(times1[i - k]);
		}
		int[] images11 = data.getIntArrayExtra(Constants.COLORS11);
		for (int i = k; i < k + images11.length; i++) {
			colors11.get(i).setBackgroundColor(images11[i - k]);
		}
		int[] images12 = data.getIntArrayExtra(Constants.COLORS12);
		for (int i = k; i < k + images12.length; i++) {
			colors12.get(i).setBackgroundColor(images12[i - k]);
		}
		int[] images13 = data.getIntArrayExtra(Constants.COLORS13);
		for (int i = k; i < k + images13.length; i++) {
			colors13.get(i).setBackgroundColor(images13[i - k]);
		}
		String[] times2 = data.getStringArrayExtra(Constants.TIMES2);
		for (int i = k; i < k + times2.length; i++) {
			texts2.get(i).setText(times2[i - k]);
		}
		int[] images21 = data.getIntArrayExtra(Constants.COLORS21);
		for (int i = k; i < k + images21.length; i++) {
			colors21.get(i).setBackgroundColor(images21[i - k]);
		}
		int[] images22 = data.getIntArrayExtra(Constants.COLORS22);
		for (int i = k; i < k + images22.length; i++) {
			colors22.get(i).setBackgroundColor(images22[i - k]);
		}
		int[] images23 = data.getIntArrayExtra(Constants.COLORS23);
		for (int i = k; i < k + images23.length; i++) {
			colors23.get(i).setBackgroundColor(images23[i - k]);
		}
		String[] days = data.getStringArrayExtra(Constants.DAYS);
		for (int i = k; i < k + days.length; i++) {
			texts3.get(i).setText(days[i - k]);
		}
		boolean[] longClickeds = data
				.getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
		for (int i = k; i < k + longClickeds.length; i++) {

			if (longClickeds[i - k]) {
				layouts.get(i).setVisibility(View.VISIBLE);
			} else {
				layouts.get(i).setVisibility(View.GONE);
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
			startActivityForResult(intent1, REQUESTCODE1);
			break;
		case R.id.layout2:
			Intent intent2 = new Intent(this, PlayerViewActivity.class);
			startActivityForResult(intent2, REQUESTCODE2);
			break;
		case R.id.layout3:
			Intent intent3 = new Intent(this, PlayerViewActivity.class);
			startActivityForResult(intent3, REQUESTCODE3);
			break;
		case R.id.layout4:
			Intent intent4 = new Intent(this, PlayerViewActivity.class);
			startActivityForResult(intent4, REQUESTCODE4);
			break;

		default:
			break;
		}
	}

}
