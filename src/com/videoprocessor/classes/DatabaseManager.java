package com.videoprocessor.classes;

/**
 * Created by Alexander on 08.04.2018.
 */
public class DatabaseManager  {
    private volatile static DatabaseManager instance;


    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null)
                    instance = new DatabaseManager();
            }
        }

        return instance;
    }
}
