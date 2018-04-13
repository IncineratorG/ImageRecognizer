package com.videoprocessor.classes;

import com.videoprocessor.interfaces.Event;
import com.videoprocessor.interfaces.EventListener;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 08.04.2018.
 */
public class ControllerAccountDialog implements Initializable, EventListener {
    public AnchorPane mainPane;
    public TextField userNameTextField;
    public TextField passwordTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ControllerAccountDialog loaded!");
    }


    public void onOkButtonClicked() {
        Account account = new Account(userNameTextField.getText(), passwordTextField.getText());

        FirebaseManager.getInstance().checkAccount(account).addEventListener(((eventObject, eventData) -> {
            if (eventData == null) {
                System.out.println("EVENT_DATA_IS_NULL");
                return;
            }

            FirebaseRequestEventData data = (FirebaseRequestEventData) eventData;
            if (data.getStatus() == FirebaseRequestEventStatus.HAS_DATA)
                System.out.println("HAS_DATA");
            else
                System.out.println("NO_DATA");
        }));
    }

    public void onCancelButtonClicked() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }



    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
            onOkButtonClicked();
        else if (keyEvent.getCode().equals(KeyCode.ESCAPE))
            onCancelButtonClicked();
    }


    @Override
    public void eventFired(Event eventObject, Object eventData) {

    }
}
