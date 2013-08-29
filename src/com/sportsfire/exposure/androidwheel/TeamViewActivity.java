package com.sportsfire.exposure.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.sportsfire.exposure.R;
import com.sportsfire.exposure.colour.ColorGridActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamViewActivity extends Activity implements OnClickListener, OnLongClickListener {

	public static final String ARG_DATES = "argumentDates";
	private int item_ids[] = { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7,
			R.id.item8, R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14 };
	private View clickedView;
	private List<ImageView> colors1 = new ArrayList<ImageView>();
	private List<ImageView> colors2 = new ArrayList<ImageView>();
	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<Boolean> isLongClickeds = new ArrayList<Boolean>();
	private List<String> dateStrings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.team_view_layout);
		dateStrings = getIntent().getStringArrayListExtra(ARG_DATES);
		initItems();

		ArrayList<TeamDayBean> days1 = getIntent().getParcelableArrayListExtra("days1");
		ArrayList<TeamDayBean> days2 = getIntent().getParcelableArrayListExtra("days2");
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
		boolean[] longClickeds = getIntent().getBooleanArrayExtra(Constants.ISLONGCLICKEDS);
		for (int i = 0; i < longClickeds.length; i++) {

			isLongClickeds.set(i, longClickeds[i]);

			if (longClickeds[i]) {
				layouts.get(i).setVisibility(View.VISIBLE);
			} else {
				layouts.get(i).setVisibility(View.GONE);
			}

		}

		Button btn_ok = (Button) findViewById(R.id.btn_allok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ArrayList<TeamDayBean> days1 = new ArrayList<TeamDayBean>();
				ArrayList<TeamDayBean> days2 = new ArrayList<TeamDayBean>();

				for (int i = 0; i < Constants.SIZE; i++) {
					TeamDayBean day = new TeamDayBean();
					day.setColor(((ColorDrawable) colors1.get(i).getBackground()).getColor());
					day.setTime(texts1.get(i).getText().toString());
					days1.add(day);
				}

				for (int i = 0; i < Constants.SIZE; i++) {
					TeamDayBean day = new TeamDayBean();
					day.setWd(texts3.get(i).getText().toString());
					day.setColor(((ColorDrawable) colors2.get(i).getBackground()).getColor());
					day.setTime(texts2.get(i).getText().toString());
					days2.add(day);
				}

				boolean[] longClickeds = new boolean[isLongClickeds.size()];
				for (int i = 0; i < isLongClickeds.size(); i++) {

					longClickeds[i] = isLongClickeds.get(i).booleanValue();
				}

				Intent intent = new Intent();
				intent.putParcelableArrayListExtra("days1", days1);
				intent.putParcelableArrayListExtra("days2", days2);
				intent.putExtra(Constants.ISLONGCLICKEDS, longClickeds);
				setResult(RESULT_OK, intent);
				finish();

			}
		});

	}

	private void initItems() {
		for (int j = 0; j < item_ids.length; j++) {

			if (j % 2 == 0) {

				ImageView iv = (ImageView) findViewById(item_ids[j]).findViewById(R.id.iv_color);
				iv.setOnClickListener(this);
				iv.setOnLongClickListener(this);
				this.registerForContextMenu(iv);
				colors1.add(iv);
				isLongClickeds.add(false);

				TextView tv_day = (TextView) findViewById(item_ids[j]).findViewById(R.id.tv_weekday);
				tv_day.setText(dateStrings.get(j/2));

				TextView tv_time = (TextView) findViewById(item_ids[j]).findViewById(R.id.tv_time);
				tv_time.setOnClickListener(this);
				texts1.add(tv_time);

			} else {

				TextView tv_day = (TextView) findViewById(item_ids[j]).findViewById(R.id.tv_weekday);
				tv_day.setText(" \n ");
				texts3.add(tv_day);
				LinearLayout layout = (LinearLayout) findViewById(item_ids[j]);
				layouts.add(layout);

				ImageView iv = (ImageView) findViewById(item_ids[j]).findViewById(R.id.iv_color);
				iv.setOnClickListener(this);
				iv.setBackgroundColor(0xFF8A2BE2);
				colors2.add(iv);

				TextView tv_time = (TextView) findViewById(item_ids[j]).findViewById(R.id.tv_time);
				tv_time.setOnClickListener(this);
				texts2.add(tv_time);

			}

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {

			for (int i = 0; i < texts1.size(); i++) {
				if (i == requestCode) {
					int time = data.getIntExtra(Constants.TIME, 5);
					String temp = "";
					if (time < 10) {
						temp = "0" + time;
					} else {
						temp = String.valueOf(time);
					}
					texts1.get(i).setText("  " + temp + "  ");
				}
			}

			for (int j = 0; j < texts2.size(); j++) {
				if (j + texts2.size() == requestCode) {
					int time = data.getIntExtra(Constants.TIME, 5);
					String temp = "";
					if (time < 10) {
						temp = "0" + time;
					} else {
						temp = String.valueOf(time);
					}
					texts2.get(j).setText("  " + temp + "  ");
				}
			}

			for (int m = 0; m < colors1.size(); m++) {
				if (m + colors1.size() * 2 == requestCode) {
					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors1.get(m).setBackgroundColor(value);
					if (texts3.get(m).getText().charAt(0) == 'D'){
						colors2.get(m).setBackgroundColor(value);
					}
				}
			}

			for (int n = 0; n < colors2.size(); n++) {
				if (n + colors2.size() * 3 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors2.get(n).setBackgroundColor(value);
					if (texts3.get(n).getText().charAt(0) == 'D'){
						colors1.get(n).setBackgroundColor(value);
					}
				}
			}
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < texts1.size(); i++) {
			if (arg0.equals(texts1.get(i))) {
				startActivityForResult(new Intent(this, WheelActivity.class), i);
			}
		}

		for (int j = 0; j < texts2.size(); j++) {
			if (arg0.equals(texts2.get(j))) {
				startActivityForResult(new Intent(this, WheelActivity.class), j + texts2.size());
			}
		}

		for (int m = 0; m < colors1.size(); m++) {
			if (arg0.equals(colors1.get(m))) {
				startActivityForResult(new Intent(this, ColorGridActivity.class), m + colors1.size() * 2);
			}
		}

		for (int n = 0; n < colors2.size(); n++) {
			if (arg0.equals(colors2.get(n))) {
				startActivityForResult(new Intent(this, ColorGridActivity.class), n + colors2.size() * 3);
			}
		}
	}

	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub

		clickedView = arg0;

		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

		menu.add(0, 1, 0, "SPLIT");
		menu.add(0, 2, 0, "DOUBLE");
		menu.add(0, 3, 0, "SINGLE");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String str = "";
		switch (item.getItemId()) {
		case 1:
			str = "S\nP";
			for (int i = 0; i < colors1.size(); i++) {
				if (clickedView.equals(colors1.get(i))) {
					layouts.get(i).setVisibility(View.VISIBLE);
					colors2.get(i).setBackground(colors1.get(i).getBackground());
					isLongClickeds.set(i, true);
					texts3.get(i).setText(str);
				}
			}
			break;
		case 2:
			str = "D\nO";
			for (int i = 0; i < colors1.size(); i++) {
				if (clickedView.equals(colors1.get(i))) {
					layouts.get(i).setVisibility(View.VISIBLE);
					colors2.get(i).setBackground(colors1.get(i).getBackground());
					isLongClickeds.set(i, true);
					texts3.get(i).setText(str);
				}
			}
			break;
		case 3:
			for (int i = 0; i < colors1.size(); i++) {
				if (clickedView.equals(colors1.get(i))) {
					isLongClickeds.set(i, false);
					layouts.get(i).setVisibility(View.GONE);
					return true;
				}
			}
		}

		return true;
	}

}
