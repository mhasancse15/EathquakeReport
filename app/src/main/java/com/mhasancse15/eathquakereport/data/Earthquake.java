package com.mhasancse15.eathquakereport.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private String mMagnitude;
    private String mLocationName;
    private String mDate;

    public Earthquake(double mMagnitude, String mLocationName, long mDate) {
        this.mMagnitude = formatMagnitude(mMagnitude);
        this.mLocationName = mLocationName;
        this.mDate = formatDate(mDate);
        ;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocationName() {
        return mLocationName;
    }

    public String getmDate() {
        return mDate;
    }

    /*--------------reformat date----------------*/
    public String formatDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String formatDate = simpleDateFormat.format(date);
        return formatDate;

    }

    /*-----------format magnitude-------------*/
    public String formatMagnitude(double magnitude) {
        return String.valueOf(magnitude);
    }
}
