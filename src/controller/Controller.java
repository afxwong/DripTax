package controller;

import components.TowerGrid;
import entities.Player;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import resources.Enums;
import resources.GameConfig;
import views.ConfigScreen;
import views.EndScreen;
import views.GameScreen;
import views.StartScreen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller extends Application {

    private final int initwidth = 800;
    private final int initheight = 450;
    private final int gamewidth = 1000;
    private final int gameheight = 500;
    private EventHandler<KeyEvent> toconfigscreen;
    private GameConfig currentGameConfig;
    private StartScreen startScreen;
    private ConfigScreen configScreen;
    private GameScreen gameScreen;
    private EndScreen endScreen;
    private Stage mainwindow;
    private Player player;

    @Override
    public void start(Stage stage) throws Exception {
        mainwindow = stage;
        mainwindow.setTitle("DripTax");
        initializeStartScreen();
    }

    private void initializeStartScreen() {
        this.startScreen = new StartScreen(this.initwidth, this.initheight);
        mainwindow.setScene(this.startScreen.getStartScene());
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
        this.configScreen = new ConfigScreen(this.initwidth, this.initheight);
        mainwindow.setScene(this.configScreen.getConfigScene());
        Button togamescreen = this.configScreen.getTogamescreen();
        TextField textField = this.configScreen.getNamefield();
        ComboBox<Enums.Difficulty> comboBox = this.configScreen.getDifficultyComboBox();
        togamescreen.setOnAction(e -> {
            String name = textField.getText();
            Enums.Difficulty diff = comboBox.getValue();
            if (invalidConfig(name, diff) != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection Error");
                alert.setContentText(invalidConfig(name, diff));
                alert.showAndWait();
            } else {
                try {
                    GameConfig.setDifficulty(diff);
                    this.currentGameConfig = new GameConfig();
                    initializeGameScreen();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        mainwindow.show();
    }

    public boolean nameIsNotValid(String name) {
        return name == null || name.trim().isEmpty();
    }

    public boolean difficultyIsValid(Enums.Difficulty difficulty) {
        return difficulty != null;
    }

    public String invalidConfig(String name, Enums.Difficulty difficulty) {
        if (nameIsNotValid(name)) {
            return "Please enter a valid name.";
        } else if (!difficultyIsValid(difficulty)) {
            return "Please choose a difficulty.";
        } else {
            return null;
        }
    }

    private void initializeGameScreen() throws FileNotFoundException {
        this.gameScreen = new GameScreen(this.gamewidth, this.gameheight);
        mainwindow.setScene(this.gameScreen.getGameScene());
        this.gameScreen.getToendscreen().setOnAction(e -> {
            initializeEndScreen();
            GameScreen.setIsRunning(false);
        });
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Top: " + GameScreen.getEnemyTop().size());
                //System.out.println("Bottom: " + GameScreen.getEnemyBottom().size());
                //System.out.println("TopCopy: " + GameScreen.getEnemyTopCopy().size());
                //System.out.println("BottomCopy: " + GameScreen.getEnemyBottomCopy().size());
            }
        };
        timer.schedule(timerTask, 0, 1000);
        mainwindow.show();
    }

    public void initializeEndScreen() {
        this.endScreen = new EndScreen(this.gamewidth, this.gameheight);
        if (GameConfig.isGameWon()) {
            this.endScreen.changeEndText("YOU WON!");
        } else {
            this.endScreen.changeEndText("You Lost...");
        }
        mainwindow.setScene(this.endScreen.getEndScene());
        this.endScreen.getExitbutton().setOnAction(e -> System.exit(0));
        this.endScreen.getPlayagainbutton().setOnAction(e -> {
            GameConfig.setEnemiesKilled(0);
            GameScreen.getClickableAnchorPane().getChildren().clear();
            GameScreen.getInfoPanel().getChildren().clear();
            GameScreen.setEnemyBottom(new LinkedList<>());
            GameScreen.setEnemyTop(new LinkedList<>());
            GameScreen.setEnemyBottomCopy(new LinkedList<>());
            GameScreen.setEnemyTopCopy(new LinkedList<>());
            initializeStartScreen();
        });
        mainwindow.show();
    }
}
