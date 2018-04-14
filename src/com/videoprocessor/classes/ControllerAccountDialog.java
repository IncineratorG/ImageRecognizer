package com.videoprocessor.classes;

import com.videoprocessor.events.FirebaseRequestEvent;
import javafx.application.Platform;
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
public class ControllerAccountDialog implements Initializable {
    public AnchorPane mainPane;
    public TextField userNameTextField;
    public TextField passwordTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ControllerAccountDialog loaded!");
    }


    public void onOkButtonClicked() {
        Account account = new Account(userNameTextField.getText(), passwordTextField.getText());

        FirebaseManager.getInstance().checkAccount(account).addEventListener(eventObject -> {
            FirebaseRequestEvent event = (FirebaseRequestEvent) eventObject;
            if (event == null)
                return;

            if (event.getEventData().getStatus() == FirebaseRequestEventStatus.ACCOUNT_EXIST) {
                AccountManager.getInstance().setCurrentAccount(account);
                Platform.runLater(() -> closeDialog());
            }
        });
    }

    public void onCancelButtonClicked() {
        closeDialog();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER))
            onOkButtonClicked();
        else if (keyEvent.getCode().equals(KeyCode.ESCAPE))
            onCancelButtonClicked();
    }


    private void closeDialog() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }
}
