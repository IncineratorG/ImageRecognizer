package com.videoprocessor.classes;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSettings implements Initializable {
    public Button backButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SettingsController is now loaded!");
    }


    public void onBackButtonClicked() {
        PaneLoader.getInstance().loadPane(
                PaneLoader.getInstance().getMainPane(),
                "main_pane_content.fxml"
        );
    }
}
