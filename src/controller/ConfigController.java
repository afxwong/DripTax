package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConfigController {

    public TextField namefield;
    public ComboBox difficulty;

    @FXML
    public void btnclick(ActionEvent actionEvent) {
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
    }

    public enum Difficulty { Easy, Medium, Hard }
}
