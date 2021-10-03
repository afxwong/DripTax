package controller;

import entities.Player;
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

    private GameConfig currentGameConfig;
    public static Stage mainwindow;
    public static Player player;

    @Override
    public void start(Stage stage) throws Exception {
        StartScreen scene = new StartScreen();
        mainwindow = stage;
        mainwindow.setScene(scene.getStartScene());
        mainwindow.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    initializeConfigScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mainwindow.show();
    }

    private void initializeConfigScreen() throws IOException {
        ConfigScreen configScreen = new ConfigScreen();
        mainwindow.setScene(configScreen.getConfigScene());
        mainwindow.show();
    }
}
