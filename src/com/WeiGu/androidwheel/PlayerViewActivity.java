package com.WeiGu.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.WeiGu.SporysFireExposure.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
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

public class PlayerViewActivity extends Activity implements OnClickListener,
		OnLongClickListener {

	private String[] strs = { "S\n7", "M\n8", "T\n9", "W\n10", "Th\n11",
			"F\n12", "S\n13" };
	private int item_ids[] = { R.id.item1, R.id.item2,
			R.id.item3, R.id.item4, R.id.item5, R.id.item6,
			R.id.item7, R.id.item8, R.id.item9,
			R.id.item10, R.id.item11, R.id.item12,
			R.id.item13, R.id.item14 };
	private View clickedView;
	private List<ImageView> colors11 = new ArrayList<ImageView>();
	private List<ImageView> colors12 = new ArrayList<ImageView>();
	private List<ImageView> colors13 = new ArrayList<ImageView>();
	private List<ImageView> colors21 = new ArrayList<ImageView>();
	private List<ImageView> colors22 = new ArrayList<ImageView>();
	private List<ImageView> colors23 = new ArrayList<ImageView>();
	private List<LinearLayout> layouts = new ArrayList<LinearLayout>();
	private List<TextView> texts1 = new ArrayList<TextView>();
	private List<TextView> texts2 = new ArrayList<TextView>();
	private List<TextView> texts3 = new ArrayList<TextView>();
	private List<Boolean> isLongClickeds = new ArrayList<Boolean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.player_view_layout);

		initItems();

		Button btn_ok = (Button) findViewById(R.id.btn_allok);
		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String[] times1 = new String[texts1.size()];
				for (int i = 0; i < texts1.size(); i++) {
					times1[i] = texts1.get(i).getText().toString();
				}
				String[] times2 = new String[texts2.size()];
				for (int i = 0; i < texts2.size(); i++) {
					times2[i] = texts2.get(i).getText().toString();
				}
				String[] days = new String[texts3.size()];
				for (int i = 0; i < texts3.size(); i++) {
					days[i] = texts3.get(i).getText().toString();
				}
				int images11[] = new int[colors11.size()];
				for (int i = 0; i < colors11.size(); i++) {
					images11[i] = ((ColorDrawable) colors11.get(i)
							.getBackground()).getColor();
				}
				int images12[] = new int[colors12.size()];
				for (int i = 0; i < colors12.size(); i++) {
					images12[i] = ((ColorDrawable) colors12.get(i)
							.getBackground()).getColor();
				}
				int images13[] = new int[colors13.size()];
				for (int i = 0; i < colors13.size(); i++) {
					images13[i] = ((ColorDrawable) colors13.get(i)
							.getBackground()).getColor();
				}
				int images21[] = new int[colors21.size()];
				for (int i = 0; i < colors21.size(); i++) {
					images21[i] = ((ColorDrawable) colors21.get(i)
							.getBackground()).getColor();
				}
				int images22[] = new int[colors22.size()];
				for (int i = 0; i < colors22.size(); i++) {
					images22[i] = ((ColorDrawable) colors22.get(i)
							.getBackground()).getColor();
				}
				int images23[] = new int[colors23.size()];
				for (int i = 0; i < colors23.size(); i++) {
					images23[i] = ((ColorDrawable) colors23.get(i)
							.getBackground()).getColor();
				}
				boolean[] longClickeds = new boolean[isLongClickeds.size()];
				for (int i = 0; i < isLongClickeds.size(); i++) {

					longClickeds[i] = isLongClickeds.get(i).booleanValue();
				}
				Intent intent = new Intent();
				intent.putExtra(Constants.TIMES1, times1);
				intent.putExtra(Constants.TIMES2, times2);
				intent.putExtra(Constants.DAYS, days);
				intent.putExtra(Constants.COLORS11, images11);
				intent.putExtra(Constants.COLORS12, images12);
				intent.putExtra(Constants.COLORS13, images13);
				intent.putExtra(Constants.COLORS21, images21);
				intent.putExtra(Constants.COLORS22, images22);
				intent.putExtra(Constants.COLORS23, images23);
				intent.putExtra(Constants.ISLONGCLICKEDS, longClickeds);
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

	private void initItems() {
		for (int j = 0; j < item_ids.length; j++) {

			if (j % 2 == 0) {

				ImageView iv1 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color1);
				iv1.setOnClickListener(this);
				iv1.setOnLongClickListener(this);
				this.registerForContextMenu(iv1);
				colors11.add(iv1);
				ImageView iv2 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color2);
				iv2.setOnClickListener(this);
				iv2.setOnLongClickListener(this);
				this.registerForContextMenu(iv2);
				colors12.add(iv2);
				ImageView iv3 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color3);
				iv3.setOnClickListener(this);
				iv3.setOnLongClickListener(this);
				this.registerForContextMenu(iv3);
				colors13.add(iv3);
				isLongClickeds.add(false);

				TextView tv_day = (TextView) findViewById(item_ids[j])
						.findViewById(R.id.tv_weekday);
				tv_day.setText(strs[j / 2]);

				TextView tv_time = (TextView) findViewById(item_ids[j])
						.findViewById(R.id.tv_time);
				tv_time.setOnClickListener(this);
				texts1.add(tv_time);

			} else {

				TextView tv_day = (TextView) findViewById(item_ids[j])
						.findViewById(R.id.tv_weekday);
				tv_day.setText(" \n ");
				texts3.add(tv_day);
				LinearLayout layout = (LinearLayout) findViewById(item_ids[j]);
				layouts.add(layout);

				ImageView iv1 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color1);
				iv1.setOnClickListener(this);
				iv1.setBackgroundColor(0xFF8A2BE2);
				colors21.add(iv1);
				
				ImageView iv2 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color2);
				iv2.setOnClickListener(this);
				iv2.setBackgroundColor(0xFF8A2BE2);
				colors22.add(iv2);
				
				ImageView iv3 = (ImageView) findViewById(item_ids[j])
						.findViewById(R.id.iv_color3);
				iv3.setOnClickListener(this);
				iv3.setBackgroundColor(0xFF8A2BE2);
				colors23.add(iv3);

				TextView tv_time = (TextView) findViewById(item_ids[j])
						.findViewById(R.id.tv_time);
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
					return;
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
					return;
				}
			}

			for (int m = 0; m < colors11.size(); m++) {
				if (m + colors11.size() * 2 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors11.get(m).setBackgroundColor(value);
					return;
				}
			}
			for (int m = 0; m < colors12.size(); m++) {
				if (m + colors12.size() * 3 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors12.get(m).setBackgroundColor(value);
					return;
				}
			}
			for (int m = 0; m < colors13.size(); m++) {
				if (m + colors13.size() * 4 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors13.get(m).setBackgroundColor(value);
					return;
				}
			}

			for (int n = 0; n < colors21.size(); n++) {
				if (n + colors21.size() * 5 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors21.get(n).setBackgroundColor(value);
					return;
				}
			}
			for (int n = 0; n < colors22.size(); n++) {
				if (n + colors22.size() * 6 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors22.get(n).setBackgroundColor(value);
					return;
				}
			}
			for (int n = 0; n < colors23.size(); n++) {
				if (n + colors23.size() * 7 == requestCode) {

					int value = data.getIntExtra(Constants.COLOR, 0x0000000);
					colors23.get(n).setBackgroundColor(value);
					return;
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
				return;
			}
		}

		for (int j = 0; j < texts2.size(); j++) {
			if (arg0.equals(texts2.get(j))) {
				startActivityForResult(new Intent(this, WheelActivity.class), j
						+ texts2.size());
				return;
			}
		}

		for (int m = 0; m < colors11.size(); m++) {
			if (arg0.equals(colors11.get(m))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						m + colors11.size() * 2);
				return;
			}
		}
		for (int m = 0; m < colors12.size(); m++) {
			if (arg0.equals(colors12.get(m))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						m + colors12.size() * 3);
				return;
			}
		}
		for (int m = 0; m < colors13.size(); m++) {
			if (arg0.equals(colors13.get(m))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						m + colors13.size() * 4);
				return;
			}
		}

		for (int n = 0; n < colors21.size(); n++) {
			if (arg0.equals(colors21.get(n))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						n + colors21.size() * 5);
				return;
			}
		}
		
		for (int n = 0; n < colors22.size(); n++) {
			if (arg0.equals(colors22.get(n))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						n + colors22.size() * 6);
				return;
			}
		}
		
		for (int n = 0; n < colors23.size(); n++) {
			if (arg0.equals(colors23.get(n))) {
				startActivityForResult(
						new Intent(this, ColorGridActivity.class),
						n + colors23.size() * 7);
				return;
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
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		menu.add(0, 1, 0, "SPLIT");
		menu.add(0, 2, 0, "DOUBLE");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String str = "";
		switch (item.getItemId()) {
		case 1:
			str = "S\nP";
			break;
		case 2:
			str = "D\nO";
			break;
		}

		for (int i = 0; i < colors11.size(); i++) {
			if (clickedView.equals(colors11.get(i))) {
				layouts.get(i).setVisibility(View.VISIBLE);
				isLongClickeds.set(i, true);
				texts3.get(i).setText(str);
				return true;
			}
		}
		for (int i = 0; i < colors12.size(); i++) {
			if (clickedView.equals(colors12.get(i))) {
				layouts.get(i).setVisibility(View.VISIBLE);
				isLongClickeds.set(i, true);
				texts3.get(i).setText(str);
				return true;
			}
		}
		for (int i = 0; i < colors13.size(); i++) {
			if (clickedView.equals(colors13.get(i))) {
				layouts.get(i).setVisibility(View.VISIBLE);
				isLongClickeds.set(i, true);
				texts3.get(i).setText(str);
				return true;
			}
		}

		return false;
	}

}
