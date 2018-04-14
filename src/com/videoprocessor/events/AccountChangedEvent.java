package com.videoprocessor.events;

import com.videoprocessor.classes.Account;
import com.videoprocessor.interfaces.Event;
import com.videoprocessor.interfaces.EventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 14.04.2018.
 */
public class AccountChangedEvent implements Event {
    private List<EventListener> listeners;
    private Account accountData;


    public AccountChangedEvent() {
        listeners = new ArrayList<>();
        accountData = new Account();
    }


    public Account getAccountData() {
        return accountData;
    }

    public void setEventData(Account accountData) {
        this.accountData = accountData;
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
            listeners.get(i).eventFired(this);
    }
}
