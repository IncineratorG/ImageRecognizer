package com.videoprocessor.classes;

/**
 * Created by Alexander on 13.04.2018.
 */
public class FirebaseRequestEventData {
    private String value = "";
    private FirebaseRequestEventStatus status;


    public FirebaseRequestEventData() {
        status = FirebaseRequestEventStatus.EMPTY;
    }

    public FirebaseRequestEventData(FirebaseRequestEventData data) {
        this.value = data.getValue();
        this.status = data.getStatus();
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        if (this.value == null || value.isEmpty())
            status = FirebaseRequestEventStatus.EMPTY;
        else
            status = FirebaseRequestEventStatus.HAS_DATA;
    }

    public FirebaseRequestEventStatus getStatus() {
        return status;
    }

    public void setStatus(FirebaseRequestEventStatus status) {
        this.status = status;
    }
}
