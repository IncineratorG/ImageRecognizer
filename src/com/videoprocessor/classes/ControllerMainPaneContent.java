package com.videoprocessor.classes;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.videoprocessor.interfaces.VideoProcessorInterface;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class ControllerMainPaneContent implements Initializable {
    public BorderPane borderPane;
    public ScrollPane scrollPane;
    public ImageView imageView;
    public Label label;
    public Label accountLabel;
    private VideoProcessorInterface videoProcessor;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ControllerMainPaneContent is now loaded!");
    }

    public void onChooseButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());

        if (selectedFile != null) {
            startVideo(selectedFile.getPath());
        } else
            System.out.println("NULL");
    }


    public void onButtonOneClicked() throws IOException {
        System.out.println("BUTTON_ONE_CLICKED");

        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            label.setText("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            label.setText(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void onButtonTwoClicked() {
        final String FIREBASE_URL = "https://surveillance-136a9.firebaseio.com/";

        Firebase firebase = new Firebase(FIREBASE_URL);
//        firebase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null)
//                    return;
//                if (dataSnapshot.getStringValue() == null)
//                    return;
//
//                System.out.println(dataSnapshot.getStringValue().toString());
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });


//        firebase.child("alexander").child("dorohov").child("a1").setStringValue("1");
//        firebase.child("alexander").child("dorohov").child("a2").setStringValue("2");
//        firebase.child("alexander").child("dorohov").child("a3").setStringValue("3");
//
//        firebase.child("mikhail").child("tabunov").child("m1").setStringValue("1");
//        firebase.child("mikhail").child("tabunov").child("m2").setStringValue("2");
//        firebase.child("mikhail").child("tabunov").child("m3").setStringValue("3");
//
//        firebase.child("kirill").child("kostychenko").child("k1").setStringValue("1");
//        firebase.child("kirill").child("kostychenko").child("k2").setStringValue("2");
//        firebase.child("kirill").child("kostychenko").child("k3").setStringValue("3");

//        String key = firebase.child("alexander").child("dorokhov").child("a1").toString();
//        System.out.println("KEY: " + key);
    }

    public void onSetAccountButtonClicked() {
        System.out.println("CLICKED");

        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../resources/account_dialog.fxml"));
//            pane.setStyle("-fx-background-color: #" + "2196F3");

            final Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle("Set account");
            stage.setMinHeight(320);
            stage.setMinWidth(200);
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();
            stage.toFront();

            PaneLoader.getInstance().getMainPane().getScene().getRoot().setEffect(new BoxBlur());

            stage.setOnCloseRequest(event -> PaneLoader.getInstance().getMainPane().getScene().getRoot().setEffect(null));
            stage.setOnHiding(event -> PaneLoader.getInstance().getMainPane().getScene().getRoot().setEffect(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startVideo(String mediaPath) {
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());

        Consumer<InternalData> consumer = data -> Platform.runLater(() -> imageView.setImage(data.getImage()));

        videoProcessor = new VideoProcessor(mediaPath, consumer);
        videoProcessor.startProcessing();
    }


    private void addImageToTilePane(String imagePath) {
        System.out.println(imagePath);

        Image image = null;
        try {
            image = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView.setImage(image);
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());

        scrollPane.setPannable(true);
    }
}
