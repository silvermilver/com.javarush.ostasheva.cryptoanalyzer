package com.javarush.ostasheva.cryptoanalyzer.controller;

import com.javarush.ostasheva.cryptoanalyzer.service.Encryptor;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CryptoController {

    File file;

    @FXML
    private TextField keyForEncrypt;

    @FXML
    private TextField keyForDecrypt;

    @FXML
    protected void onEncryptButtonClick() {
        String key = keyForEncrypt.getText();
        Encryptor.encryptFile(file, key);
    }

    @FXML
    protected void onDecryptButtonClick() {
        String key = keyForDecrypt.getText();
        Encryptor.decryptFile(file, key);
    }

    @FXML
    protected void onOpenFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("ОТКРЫТЬ ФАЙЛ");
        file = fileChooser.showOpenDialog(new Stage());
    }
}
