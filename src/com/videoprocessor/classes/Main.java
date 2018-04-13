package com.videoprocessor.classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private static final String OPEN_CV_FOLDER = "C:\\OpenCV_3.3.0\\build\\java\\x64\\opencv_java330.dll";


    public static void main(String[] args) {
        System.load(OPEN_CV_FOLDER);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/main_pane.fxml"));

        primaryStage.setTitle("Video Processor");
        primaryStage.setMinHeight(480);
        primaryStage.setMinWidth(640);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        FirebaseManager.getInstance().closeConnection();
        super.stop();
    }
}
