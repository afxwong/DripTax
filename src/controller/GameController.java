package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {

    @FXML
    public Label healthlbl;
    @FXML
    public Label moneylbl;

    public GameController() {
        this.healthlbl = new Label(Integer.toString(GameConfig.towerhealth));
        this.moneylbl = new Label(Integer.toString(GameConfig.money));
    }

    @FXML
    public void updateLabels() {

    }
}
