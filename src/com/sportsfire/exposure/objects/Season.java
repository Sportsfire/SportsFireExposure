package com.sportsfire.exposure.objects;
import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Season implements Parcelable{
	private SquadList squadList;
    private ArrayList<String> weekList = new ArrayList<String>();
    private String name;
    private String id;
    
    public Season(String _name, String _id, Context context){
        name = _name;
        id = _id;
        //startDate = _start;
        squadList = new SquadList(context);
        for (int i = 1; i <= 52; i++) {
			weekList.add("Week " + Integer.toString(i));
		}     
    }
    public Season(Parcel in) {
		readFromParcel(in);
	}
	public SquadList getSquadList(){
        return squadList;
    }
    
    public ArrayList<String> getWeeklist(){
        return weekList;
    }
    
    public String getSeasonName(){
        return name;
    }

    public String getSeasonID(){
        return id;
    }

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static final Parcelable.Creator<Season> CREATOR = new Parcelable.Creator<Season>(){
		 public Season createFromParcel(Parcel in) {  
	            return new Season(in);  
	        }  
	   
	        public Season[] newArray(int size) {  
	            return new Season[size];  
	        }  
	};
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(id);
		
	}
	
	public void readFromParcel(Parcel in){
		
	}
}