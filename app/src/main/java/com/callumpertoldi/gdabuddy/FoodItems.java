package com.callumpertoldi.gdabuddy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Callum Pertoldi on 01/08/2017.
 */

public class FoodItems implements Parcelable
{
    private String ssName;
    private float fKCal;
    private float fFats;
    private float fSaturates;
    private float fSugars;
    private float fSalts;

    FoodItems()
    {
        ssName = "";
        fKCal = 0;
        fFats = 0;
        fSaturates = 0;
        fSugars = 0;
        fSalts = 0;
    }

    public FoodItems(String ssName, float fKCal, float fFats, float fSaturates, float fSugars, float fSalts)
    {
        this.ssName = ssName;
        this.fKCal = fKCal;
        this.fFats = fFats;
        this.fSaturates = fSaturates;
        this.fSugars = fSugars;
        this.fSalts = fSalts;
    }

    public boolean IsComplete()
    {
        if(ssName.length()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    byte[] getBytes()
    {
        String temp = ssName + " \n" + fKCal + " \n" + fFats + " \n" +  fSaturates + " \n" + fSugars + " \n" + fSalts + " \n";
        return temp.getBytes();
    }

    public FoodItems(Parcel in){

        float temp = 0;
        String[] data = new String[6];

        in.readStringArray(data);

        ssName = data[0];

        temp = Float.parseFloat(data[1]);
        setfKCal(temp);

        temp = Float.parseFloat(data[2]);
        setfFats(temp);

        temp = Float.parseFloat(data[3]);
        setfSaturates(temp);

        temp = Float.parseFloat(data[4]);
        setfSugars(temp);

        temp = Float.parseFloat(data[5]);
        setfSalts(temp);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray(new String[]
                {this.ssName, (this.fKCal + ""), (this.fFats + ""), (this.fSaturates + ""), (this.fSugars + ""), (this.fSalts + "")
                });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public FoodItems createFromParcel(Parcel in)
        {
            return new FoodItems(in);
        }

        public FoodItems[] newArray(int size)
        {
            return new FoodItems[size];
        }
    };



    public void setSsName(String ssName) {
        this.ssName = ssName;
    }

    public void setfKCal(float fKCal) {
        this.fKCal = fKCal;
    }

    public void setfFats(float fFats) {
        this.fFats = fFats;
    }

    public void setfSaturates(float fSaturates) {
        this.fSaturates = fSaturates;
    }

    public void setfSugars(float fSugars) {
        this.fSugars = fSugars;
    }

    public void setfSalts(float fSalts) {
        this.fSalts = fSalts;
    }

    public String getSsName() {

        return ssName;
    }

    public float getfKCal() {
        return fKCal;
    }

    public float getfFats() {
        return fFats;
    }

    public float getfSaturates() {
        return fSaturates;
    }

    public float getfSugars() {
        return fSugars;
    }

    public float getfSalts() {
        return fSalts;
    }
}
