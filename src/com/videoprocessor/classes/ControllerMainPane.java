package com.videoprocessor.classes;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMainPane implements Initializable {
    public AnchorPane mainAnchorPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ControllerMainPane is now loaded!");

        PaneLoader.getInstance().setMainPane(mainAnchorPane);
        PaneLoader.getInstance().loadPane(mainAnchorPane, "../resources/main_pane_content.fxml");
    }
}
