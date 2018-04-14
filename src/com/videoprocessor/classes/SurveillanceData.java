package com.videoprocessor.classes;

/**
 * Created by Alexander on 14.04.2018.
 */
public class SurveillanceData {
    private String stringData;


    public SurveillanceData() {
    }

    public SurveillanceData(String stringData) {

        this.stringData = stringData;
    }


    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
