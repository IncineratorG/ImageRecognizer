package com.videoprocessor.classes;

import javafx.concurrent.Service;
import javafx.concurrent.Task;



public class VideoProcessorService extends Service<String> {
    private Task<String> task;


    public VideoProcessorService(Task<String> task) {
        this.task = task;
    }

    @Override
    protected Task<String> createTask() {
        return task;
    }
}
