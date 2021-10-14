package components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import resources.GameConfig;

public class InfoPanel extends VBox {

    public InfoPanel() {
        this.setStyle("-fx-background-color: #808080");
        this.setMinSize(150, 100);
        this.prefWidth(150);
        this.prefHeight(100);
        this.setLayoutX(850);
        this.setLayoutY(0);
        updateInfo();
        this.setAlignment(Pos.CENTER);
    }

    public void updateInfo() {
        Label frunelbl = new Label(String.format("Fire rune: %s", GameConfig.getFrune()));
        Label wrunelbl = new Label(String.format("Water rune: %s", GameConfig.getWrune()));
        Label grunelbl = new Label(String.format("Ground rune: %s", GameConfig.getGrune()));
        Label arunelbl = new Label(String.format("Air rune: %s", GameConfig.getArune()));
        this.getChildren().clear();
        this.getChildren().addAll(frunelbl, wrunelbl, grunelbl, arunelbl);
    }
}
