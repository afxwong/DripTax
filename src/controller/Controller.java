package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.ConfigScreen;
import views.StartScreen;

import java.io.IOException;

public class Controller extends Application {

    private Stage mainwindow;

    @Override
    public void start(Stage stage) throws Exception {
        StartScreen scene = new StartScreen();
        mainwindow = stage;
        mainwindow.setScene(scene.getStartScene());
        mainwindow.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    initializeConfigController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mainwindow.show();
    }

    private void initializeConfigController() throws IOException {
        ConfigScreen configScreen = new ConfigScreen();
        mainwindow.setScene(configScreen.getScene());
        mainwindow.show();
    }
}
