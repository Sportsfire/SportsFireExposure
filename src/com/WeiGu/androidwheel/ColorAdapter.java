package com.WeiGu.androidwheel;

import java.util.ArrayList;
import java.util.List;

import com.WeiGu.SporysFireExposure.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ColorAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ColorBean> colorList;

	public ColorAdapter(Context context, String[] titles, int[] colors) {
		super();
		colorList = new ArrayList<ColorBean>();
		inflater = LayoutInflater.from(context);
		for (int i = 0; i < colors.length; i++) {
			ColorBean picture = new ColorBean(titles[i], colors[i]);
			colorList.add(picture);
		}
	}

	@Override
	public int getCount() {
		if (null != colorList) {
			return colorList.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return colorList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.color_item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.color);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(colorList.get(position).getTitle());
		viewHolder.image.setBackgroundColor(colorList.get(position).getColor());
		return convertView;
	}

}

class ViewHolder {
	public TextView title;
	public ImageView image;
}
