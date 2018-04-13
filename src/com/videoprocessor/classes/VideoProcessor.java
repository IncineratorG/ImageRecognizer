package com.videoprocessor.classes;

import com.videoprocessor.interfaces.VideoProcessorInterface;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.opencv.videoio.VideoCapture;

import java.util.function.Consumer;



public class VideoProcessor implements VideoProcessorInterface {
    private VideoCapture mediaSource;
    private Consumer<InternalData> processedImageConsumer;
    private Task<String> videoProcessorTask;
    private Service<String> videoProcessorService;


    public VideoProcessor(String mediaSourcePath, Consumer<InternalData> processedImageConsumer) {
        mediaSource = new VideoCapture(mediaSourcePath);
        this.processedImageConsumer = processedImageConsumer;

        videoProcessorTask = new VideoProcessorTask(mediaSource, this.processedImageConsumer);
        videoProcessorService = new VideoProcessorService(videoProcessorTask);
    }

    public VideoProcessor(int videoDeviceIndex, Consumer<InternalData> processedImageConsumer) {
        mediaSource = new VideoCapture(videoDeviceIndex);
        this.processedImageConsumer = processedImageConsumer;

        videoProcessorTask = new VideoProcessorTask(mediaSource, this.processedImageConsumer);
        videoProcessorService = new VideoProcessorService(videoProcessorTask);
    }


    public void startProcessing() {
        videoProcessorService.start();
    }

    public void stopProcessing() {
        videoProcessorService.cancel();
    }
}
