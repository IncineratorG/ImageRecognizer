package com.videoprocessor.classes;

import javafx.scene.image.Image;



public class InternalData {
    private Image image;

    public InternalData(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
