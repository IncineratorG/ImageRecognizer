package com.videoprocessor.event_listeners;

import com.videoprocessor.interfaces.Event;
import com.videoprocessor.interfaces.EventListener;

/**
 * Created by Alexander on 14.04.2018.
 */
public interface AccountChangedEventListener extends EventListener {
    @Override
    void eventFired(Event eventObject);
}
