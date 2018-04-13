package com.videoprocessor.classes;


import javafx.concurrent.Task;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import java.io.ByteArrayInputStream;
import java.util.function.Consumer;

public class VideoProcessorTask extends Task<String> {
    private VideoCapture mediaSource;
    private Consumer<InternalData> imageProcessorResults;


    public VideoProcessorTask(VideoCapture mediaSource, Consumer<InternalData> processedImageConsumer) {
        this.mediaSource = mediaSource;
        this.imageProcessorResults = processedImageConsumer;
    }


    @Override
    protected String call() throws Exception {
        Mat currentFrameData = new Mat();

        while (!isCancelled()) {
            mediaSource.read(currentFrameData);
            if (currentFrameData.empty())
                continue;

            imageProcessorResults.accept(new InternalData(toImage(currentFrameData)));
        }
        return null;
    }

    private Image toImage(Mat frameData) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".bmp", frameData, matOfByte);

        return new Image(new ByteArrayInputStream(matOfByte.toArray()));
    }
}
