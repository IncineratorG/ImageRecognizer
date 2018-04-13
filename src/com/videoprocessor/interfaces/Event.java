package com.videoprocessor.interfaces;

public interface Event {
    void addEventListener(EventListener eventListener);
    void removeEventListener(EventListener eventListener);
    void fireEventWithAdditionalData(Object data);
    void fireEvent();
}
