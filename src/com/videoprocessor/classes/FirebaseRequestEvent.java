package com.videoprocessor.classes;

import com.videoprocessor.interfaces.Event;
import com.videoprocessor.interfaces.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 13.04.2018.
 */
public class FirebaseRequestEvent implements Event {
    private List<EventListener> listeners;
    private FirebaseRequestEventData eventData;


    public FirebaseRequestEvent() {
        listeners = new ArrayList<>();
        eventData = new FirebaseRequestEventData();
    }

    public FirebaseRequestEvent(FirebaseRequestEvent event) {
        listeners = new ArrayList<>();
        eventData = event.getEventData();
    }


    public FirebaseRequestEventData getEventData() {
        return eventData;
    }

    public void setEventData(FirebaseRequestEventData eventData) {
        this.eventData = eventData;
    }


    @Override
    public void addEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void removeEventListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    @Override
    public void fireEvent() {
        for (int i = 0; i < listeners.size(); ++i)
            listeners.get(i).eventFired(this, eventData);
    }

    @Override
    public void fireEventWithAdditionalData(Object data) {

    }
}
