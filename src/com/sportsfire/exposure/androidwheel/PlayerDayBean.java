package com.sportsfire.exposure.androidwheel;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerDayBean implements Parcelable{

	private int color1;
	private int color2;
	private int color3;
	private String time;
	private String weekday;


	

	public int getColor1() {
		return color1;
	}

	public void setColor1(int color1) {
		this.color1 = color1;
	}

	public int getColor2() {
		return color2;
	}

	public void setColor2(int color2) {
		this.color2 = color2;
	}

	public int getColor3() {
		return color3;
	}

	public void setColor3(int color3) {
		this.color3 = color3;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public static Parcelable.Creator<PlayerDayBean> getCreator() {
		return CREATOR;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	@Override
	public String toString() {
		return "PlayerDayBean [color1=" + color1 + ", color2=" + color2
				+ ", color3=" + color3 + ", time=" + time + ", weekday="
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
		dest.writeInt(color1);
		dest.writeInt(color2);
		dest.writeInt(color3);
		dest.writeString(time);
	}
	
	public static final Parcelable.Creator<PlayerDayBean> CREATOR = new Creator<PlayerDayBean>() {  
        public PlayerDayBean createFromParcel(Parcel source) {  
        	PlayerDayBean day = new PlayerDayBean();  
        	day.weekday = source.readString(); 
        	day.color1 = source.readInt(); 
        	day.color2 = source.readInt(); 
        	day.color3 = source.readInt(); 
        	day.time = source.readString(); 
 
            return day;  
        }  
        public PlayerDayBean[] newArray(int size) {  
            return new PlayerDayBean[size];  
        }  
    };  
}
