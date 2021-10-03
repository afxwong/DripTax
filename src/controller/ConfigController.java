package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import views.GameScreen;

import java.io.IOException;

public class ConfigController {

    @FXML
    public TextField namefield;
    @FXML
    public ComboBox difficulty;
    public static Difficulty difficultysetting;

    @FXML
    public void btnclick(ActionEvent actionEvent) throws IOException {
        try {
            if (namefield.getText() == null || namefield.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Name Error");
                alert.setContentText("Please enter a valid name.");
                alert.showAndWait();
            } else if (difficulty.getValue() == null || difficulty.getValue().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Selection Error");
                alert.setContentText("Please select a difficulty.");
                alert.showAndWait();
            }
            if (difficulty.getValue().equals("Easy")) {
                difficultysetting = Difficulty.Easy;
            } else if (difficulty.getValue().equals("Medium")) {
                difficultysetting = Difficulty.Medium;
            } else {
                difficultysetting = Difficulty.Hard;
            }
        } catch (NullPointerException nullPointerException) {
            return;
        }
        initializeGameScreen();
    }

    public void initializeGameScreen() throws IOException {
        GameScreen gameScreen = new GameScreen();
        GameConfig gameConfig = new GameConfig();
        GameController gameController = new GameController();
        gameController.updateLabels();
        Controller.mainwindow.setScene(gameScreen.getGameScene());
        Controller.mainwindow.show();
    }

    public enum Difficulty { Easy, Medium, Hard;}
}
