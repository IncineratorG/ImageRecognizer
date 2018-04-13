package com.videoprocessor.classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class PaneLoader {
    private static PaneLoader instance;
    private Pane mainPane;


    private PaneLoader() {}

    public static PaneLoader getInstance() {
        if (instance == null)
            instance = new PaneLoader();

        return instance;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }


    public void loadPane(Pane parentPane, String panePath) {
        if (parentPane == null)
            return;

        try {
            Pane pane = FXMLLoader.load(getClass().getResource(panePath));

            parentPane.getChildren().clear();
            parentPane.getChildren().addAll(pane);

            pane.prefWidthProperty().bind(parentPane.widthProperty());
            pane.prefHeightProperty().bind(parentPane.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
