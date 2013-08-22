package com.sportsfire.exposure.androidwheel;

import com.sportsfire.exposure.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ColorGridActivity extends Activity {
	private GridView gridView;

	public final String TITLES[] = { "#BD7803", "#049FF1", "#72CFD7",
			"#A2B700", "#FF981F", "#3F813F", "#BEC0C2", "#F0F0F0" };
	public final int COLORS[] = { 0xFFBD7803, 0xFF049FF1, 0xFF72CFD7,
			0xFFA2B700, 0xFFFF981F, 0xFF3F813F, 0xFFBEC0C2, 0xFFF0F0F0 };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Please choose a color");
		setContentView(R.layout.color_layout);

		gridView = (GridView) findViewById(R.id.gridview);
		ColorAdapter adapter = new ColorAdapter(this, TITLES, COLORS);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent data = new Intent();
				data.putExtra(Constants.COLOR, COLORS[position]);
				setResult(RESULT_OK, data);
				finish();
			}
		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {

				return true;

			}

		});
	}
}
