package com.sportsfire.exposure.androidwheel;
import com.sportsfire.exposure.R;
import com.sportsfire.exposure.widget.ArrayWheelAdapter;
import com.sportsfire.exposure.widget.OnWheelChangedListener;
import com.sportsfire.exposure.widget.OnWheelScrollListener;
import com.sportsfire.exposure.widget.WheelView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WheelActivity extends Activity {

	private String[] nums;

	// Time changed flag
	private boolean timeChanged = false;

	// Wheel Scrolled flag
	private boolean timeScrolled = false;

	private int time = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wheel_layout);

		nums = new String[18];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = String.valueOf((i + 1) * 5);
		}

		final WheelView mins = (WheelView) findViewById(R.id.mins);
		mins.setAdapter(new ArrayWheelAdapter<String>(nums));
		mins.setLabel("mins");
		mins.setCyclic(true);

		mins.setCurrentItem(1);

		// add listeners
		addChangingListener(mins, "mins");

		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				if (!timeScrolled) {
//					timeChanged = true;
//					timeChanged = false;
				}
			}
		};

		mins.addChangingListener(wheelListener);

		OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				timeScrolled = true;
			}

			public void onScrollingFinished(WheelView wheel) {
				timeScrolled = false;
				timeChanged = true;
				//timeChanged = false;
			}
		};

		mins.addScrollingListener(scrollListener);
		
		Button btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if(!timeChanged){
					time = 10;
				}
				intent.putExtra(Constants.TIME, time);
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

	/**
	 * Adds changing listener for wheel that updates the wheel label
	 * 
	 * @param wheel
	 *            the wheel
	 * @param label
	 *            the wheel label
	 */
	private void addChangingListener(final WheelView wheel, final String label) {
		wheel.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				time = 5 + newValue * 5;
			}
		});
	}

}
