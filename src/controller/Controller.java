package controller;

import entities.Player;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import resources.Enums;
import resources.GameConfig;
import views.ConfigScreen;
import views.GameScreen;
import views.StartScreen;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller extends Application {

    private final int width = 800;
    private final int height = 450;
    private EventHandler<KeyEvent> toconfigscreen;
    private GameConfig currentGameConfig;
    public Stage mainwindow;
    public Player player;

    @Override
    public void start(Stage stage) throws Exception {
        StartScreen scene = new StartScreen(this.width, this.height);
        mainwindow = stage;
        mainwindow.setScene(scene.getStartScene());
        this.toconfigscreen = keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    initializeConfigScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mainwindow.addEventFilter(KeyEvent.KEY_PRESSED, this.toconfigscreen);
        mainwindow.show();
    }

    private void initializeConfigScreen() throws IOException {
        mainwindow.removeEventFilter(KeyEvent.KEY_PRESSED, this.toconfigscreen);
        ConfigScreen configScreen = new ConfigScreen(this.width, this.height);
        mainwindow.setScene(configScreen.getConfigScene());
        Button togamescreen = configScreen.getTogamescreen();
        TextField textField = configScreen.getNamefield();
        ComboBox<Enums.Difficulty> comboBox = configScreen.getDifficultyComboBox();
        togamescreen.setOnAction(e -> {
            String name = textField.getText();
            Enums.Difficulty diff = comboBox.getValue();
            if (name == null || name.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Name Error");
                alert.setContentText("Please enter a valid name.");
                alert.showAndWait();
            } else if (diff == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection Error");
                alert.setContentText("Please select a difficulty.");
                alert.showAndWait();
            } else {
                try {
                    initializeGameScreen();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        mainwindow.show();
    }

    private void initializeGameScreen() throws FileNotFoundException {
        GameScreen gameScreen = new GameScreen(1000, 250);
        mainwindow.setScene(gameScreen.getGameScene());
        mainwindow.show();
    }
}
