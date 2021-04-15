package com.example.travlog;

public class TripItem {
    private int tripid;
    private int mImageResource;
    private String mText1;
    private String mText2;
    public TripItem(int tripi,int imageResource,String text1, String text2){
        tripid=tripi;
        mImageResource=imageResource;
        mText1=text1;
        mText2=text2;
    }
    public int getImageResource(){
        return mImageResource;
    }
    public int getTripId(){
        return tripid;
    }
    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
