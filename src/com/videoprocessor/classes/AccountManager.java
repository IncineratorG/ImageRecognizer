package com.videoprocessor.classes;

import com.videoprocessor.event_listeners.AccountChangedEventListener;
import com.videoprocessor.events.AccountChangedEvent;

/**
 * Created by Alexander on 14.04.2018.
 */
public class AccountManager {
    private volatile static AccountManager instance;
    private Account currentAccount;
    private AccountChangedEvent accountChangeEvent;


    private AccountManager() {
        currentAccount = new Account();
        accountChangeEvent = new AccountChangedEvent();
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null)
                    instance = new AccountManager();
            }
        }

        return instance;
    }


    public void setCurrentAccount(Account account) {
        currentAccount = account;

        accountChangeEvent.setEventData(account);
        accountChangeEvent.fireEvent();
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void addAccountChangedEventListener(AccountChangedEventListener accountChangedEventListener) {
        accountChangeEvent.addEventListener(accountChangedEventListener);
    }


//    @Override
//    public void addEventListener(EventListener eventListener) {
//        listeners.add(eventListener);
//    }
//
//    @Override
//    public void removeEventListener(EventListener eventListener) {
//        listeners.remove(eventListener);
//    }
//
//    @Override
//    public void fireEvent() {
//        for (int i = 0; i < listeners.size(); ++i)
//            listeners.get(i).eventFired(this);
//    }
}
