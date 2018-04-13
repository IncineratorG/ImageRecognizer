package com.videoprocessor.classes;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Alexander on 13.04.2018.
 */
public class FirebaseManager {
    private volatile static FirebaseManager instance;
    private static Firebase firebaseDatabase;
    private static final String FIREBASE_URL = "https://surveillance-136a9.firebaseio.com/";


    private FirebaseManager() {
        firebaseDatabase = new Firebase(FIREBASE_URL);
    }

    public static FirebaseManager getInstance() {
        if (instance == null) {
            synchronized (FirebaseManager.class) {
                if (instance == null)
                    instance = new FirebaseManager();
            }
        }

        return instance;
    }


    public void closeConnection() {
        if (firebaseDatabase != null)
            firebaseDatabase.goOffline();
    }

    public FirebaseRequestEvent checkAccount(Account account) {
        FirebaseRequestEvent event = new FirebaseRequestEvent();

        firebaseDatabase.child(account.getName()).child(account.getPassword()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null || dataSnapshot.getValue() == null) {
                    event.setEventData(new FirebaseRequestEventData());
                    event.fireEvent();
                    return;
                }

                if (account.isIncomplete()) {
                    event.setEventData(new FirebaseRequestEventData());
                    event.fireEvent();
                    return;
                }


                FirebaseRequestEventData eventData = new FirebaseRequestEventData();
                eventData.setValue(dataSnapshot.getValue().toString());

                event.setEventData(eventData);
                event.fireEvent();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return event;
    }

    public FirebaseRequestEvent createAccount(Account account) {
        FirebaseRequestEvent resultEvent = new FirebaseRequestEvent();
        FirebaseRequestEventData resultEventData = new FirebaseRequestEventData();

        // Проверяем, присутсвует ли такой аккаунт на сервере.
        // Если присутсвует - ничего не делаем. Иначе - добавляем новый аккаунт.
        checkAccount(account).addEventListener((eventObject, eventData) -> {
            // Проверяем, присутсвует ли уже такой аккаунт.
            FirebaseRequestEventData checkAccountRequestEventData = (FirebaseRequestEventData) eventData;
            if (checkAccountRequestEventData != null || checkAccountRequestEventData.getStatus() == FirebaseRequestEventStatus.HAS_DATA) {
                resultEventData.setStatus(FirebaseRequestEventStatus.ACCOUNT_EXIST);

                resultEvent.setEventData(resultEventData);
                resultEvent.fireEvent();
                return;
            }

            // Добавляем новый аккаунт.
            firebaseDatabase.child(account.getName()).child(account.getPassword()).child("c").setValue("1");

            // Проверяем успешность создания нового аккаунта.
            firebaseDatabase.child(account.getName()).child(account.getPassword()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot == null || dataSnapshot.getValue() == null) {
                        resultEventData.setStatus(FirebaseRequestEventStatus.ACCOUNT_NOT_CREATED);

                        resultEvent.setEventData(resultEventData);
                        resultEvent.fireEvent();
                        return;
                    }

                    resultEventData.setStatus(FirebaseRequestEventStatus.ACCOUNT_CREATED);

                    resultEvent.setEventData(resultEventData);
                    resultEvent.fireEvent();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        });

        return resultEvent;
    }
}
