package components;

import entities.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import resources.Enums.Element;
import resources.GameConfig;
import views.GameScreen;

public class HotbarComponent extends HBox {
    public HotbarComponent() {
        this.setStyle("-fx-background-color: #ddd");
        this.setMinSize(1000, 100);
        this.setMaxSize(1000, 100);
        this.setLayoutX(0);
        this.setLayoutY(400);
        ImageView FireTowerLabel = new ImageView(new Image("resources/fireTower.png", 100, 100, false, false));
        ImageView WaterTowerLabel = new ImageView(new Image("resources/waterTower.png", 100, 100, false, false));
        ImageView GroundTowerLabel = new ImageView(new Image("resources/groundTower.png", 100, 100, false, false));
        ImageView AirTowerLabel = new ImageView(new Image("resources/airTower.png", 100, 100, false, false));
        FireTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Fire));
        WaterTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Water));
        GroundTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Ground));
        AirTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Air));
        FireTowerLabel.setPickOnBounds(true);
        WaterTowerLabel.setPickOnBounds(true);
        GroundTowerLabel.setPickOnBounds(true);
        AirTowerLabel.setPickOnBounds(true);
        this.getChildren().addAll(FireTowerLabel, WaterTowerLabel, GroundTowerLabel, AirTowerLabel);
        this.setAlignment(Pos.CENTER);
    }

    private void placeTower(Element towerType) {
        boolean sufficentRunes = false;
        if (towerType == Element.Fire && GameConfig.getFrune() >= GameConfig.towerCost) {
            GameConfig.setFrune(GameConfig.getFrune() - 10);
            sufficentRunes = true;
        } else if (towerType == Element.Water && GameConfig.getWrune() >= GameConfig.towerCost) {
            GameConfig.setWrune(GameConfig.getWrune() - 10);
            sufficentRunes = true;
        } else if (towerType == Element.Ground && GameConfig.getGrune() >= GameConfig.towerCost) {
            GameConfig.setGrune(GameConfig.getGrune() - 10);
            sufficentRunes = true;
        } else if (towerType == Element.Air && GameConfig.getArune() >= GameConfig.towerCost) {
            GameConfig.setArune(GameConfig.getArune() - 10);
            sufficentRunes = true;
        }
        if (sufficentRunes) {
            GameScreen.infoPanel.updateInfo();
            int[] selectedTile = Player.getSelectedTile();
            TowerGrid.placeTower(selectedTile, towerType);
            Player.deselectSelectedTile();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient " + towerType + " Runes");
            alert.setContentText("10 " + towerType + " Runes are required to purchase this tower.");
            alert.showAndWait();
        }
    }
}
