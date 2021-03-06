package com.sportsfire.exposure.androidwheel;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamDayBean implements Parcelable{

	private int color;
	private String time;
	private String weekday;


	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWd() {
		return weekday;
	}

	public void setWd(String weekday) {
		this.weekday = weekday;
	}

	@Override
	public String toString() {
		return "DayBean [color=" + color + ", time=" + time + ", weekday="
				+ weekday + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(weekday);
		dest.writeInt(color);
		dest.writeString(time);
	}
	
	public static final Parcelable.Creator<TeamDayBean> CREATOR = new Creator<TeamDayBean>() {  
        public TeamDayBean createFromParcel(Parcel source) {  
        	TeamDayBean day = new TeamDayBean();  
        	day.weekday = source.readString(); 
        	day.color = source.readInt(); 
        	day.time = source.readString(); 
 
            return day;  
        }  
        public TeamDayBean[] newArray(int size) {  
            return new TeamDayBean[size];  
        }  
    };  
}
