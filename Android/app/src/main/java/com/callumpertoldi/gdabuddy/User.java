package com.callumpertoldi.gdabuddy;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.data;

/**
 * Created by Callum Pertoldi on 01/08/2017.
 */

public class User implements Parcelable
{
    private boolean bIsMale;
    private String ssName;

    private float fKCal;
    private float fFats;
    private float fSaturates;
    private float fSugars;
    private float fSalts;


    User()
    {
        ssName = "";
        bIsMale = true;
        fKCal = 0;
        fFats = 0;
        fSaturates = 0;
        fSugars = 0;
        fSalts = 0;
    }

    String Greeting()
    {
        return "Welcome Back, " + ssName.trim() + ".";
    }


    public User(Parcel in){

        float temp = 0;
        String[] data = new String[7];

        in.readStringArray(data);

        ssName = data[0];

        if (data[1].contains("true")) {
            setbIsMale(true);
        } else {
            setbIsMale(false);
        }

        temp = Float.parseFloat(data[2]);
        setfKCal(temp);

        temp = Float.parseFloat(data[3]);
        setfFats(temp);

        temp = Float.parseFloat(data[4]);
        setfSaturates(temp);

        temp = Float.parseFloat(data[5]);
        setfSugars(temp);

        temp = Float.parseFloat(data[6]);
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
                {this.ssName, (this.bIsMale + ""), (this.fKCal + ""), (this.fFats + ""), (this.fSaturates + ""), (this.fSugars + ""), (this.fSalts + "")
                });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        public User[] newArray(int size)
        {
            return new User[size];
        }
    };



    public void setbIsMale(Boolean bIsMale) {
        this.bIsMale = bIsMale;
    }

    public void setSsName(String ssName) {
        this.ssName = ssName;
    }

    void StartNewDay()
    {
        fKCal = 0;
        fFats = 0;
        fSaturates = 0;
        fSugars = 0;
        fSalts = 0;
    }

    void Reset() {
        ssName = "";

        fKCal = 0;
        fFats = 0;
        fSaturates = 0;
        fSugars = 0;
        fSalts = 0;
    }

    byte[] getBytes()
    {
        String temp = ssName + " \n" + bIsMale + " \n" + fKCal + " \n" + fFats + " \n" +  fSaturates + " \n" + fSugars + " \n" + fSalts;
        return temp.getBytes();
    }


    public void setbIsMale(boolean bIsMale) {
        this.bIsMale = bIsMale;
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


    public boolean isbIsMale() {
        return bIsMale;
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

 float GetKCalPercent()
    {
        if(fKCal == 0)
        {
            return 0;
        }
        else
        {
            if (bIsMale)
            {
                return ((fKCal / 2500) * 100);
            }
            else
            {
                return ((fKCal / 2000) * 100);
            }
        }
    }
 float GetFatsPercent()
{
    if(fFats == 0)
    {
        return 0;
    }
    else
    {
        if (bIsMale)
        {
            return ((fFats / 95) * 100);
        }
        else
        {
            return ((fFats / 70) * 100);
        }
    }

}

float GetSaturatesPercent()
{
    if(fSaturates == 0)
    {
        return 0;
    }
    else
    {
        if (bIsMale)
        {
            return ((fSaturates / 30) * 100);
        }
        else
        {
            return ((fSaturates / 20) * 100);
        }
    }
}

float GetSugarsPercent()
{
    if(fSugars == 0)
    {
        return 0;
    }
    else
    {
        if (bIsMale)
        {
            return ((fSugars / 120) * 100);
        }
        else
        {
            return ((fSugars / 90) * 100);
        }
    }
}

    float GetSaltsPercent()
{
    if(fSalts == 0)
    {
        return 0;
    }
    else
    {
        return ((fSalts / 6) * 100);
    }
}
}
