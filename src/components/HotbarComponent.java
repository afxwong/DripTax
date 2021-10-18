package components;

import entities.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
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

        ImageView fireTowerLabel = new ImageView(new Image(
                "resources/fireTower.png", 100, 100, false, false));
        ImageView waterTowerLabel = new ImageView(new Image(
                "resources/waterTower.png", 100, 100, false, false));
        ImageView groundTowerLabel = new ImageView(new Image(
                "resources/groundTower.png", 100, 100, false, false));
        ImageView airTowerLabel = new ImageView(new Image(
                "resources/airTower.png", 100, 100, false, false));

        Tooltip.install(fireTowerLabel, new Tooltip(
                "Fire Tower\nDeals damage over time by applying Burn status effect"));
        Tooltip.install(waterTowerLabel, new Tooltip(
                "Water Tower\nSlows enemies, ice upgrade that freezes enemies"));
        Tooltip.install(groundTowerLabel, new Tooltip(
                "Ground Tower\nSplash damage of ground type"));
        Tooltip.install(airTowerLabel, new Tooltip(
                "Air Tower\nFast attack, low damage attack of air type"));
        fireTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Fire));
        waterTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Water));
        groundTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Ground));
        airTowerLabel.setOnMouseClicked(mouseEvent -> placeTower(Element.Air));
        fireTowerLabel.setPickOnBounds(true);
        waterTowerLabel.setPickOnBounds(true);
        groundTowerLabel.setPickOnBounds(true);
        airTowerLabel.setPickOnBounds(true);
        this.getChildren().addAll(fireTowerLabel, waterTowerLabel, groundTowerLabel, airTowerLabel);
        this.setAlignment(Pos.CENTER);
    }

    private void placeTower(Element towerType) {
        boolean sufficientRunes = false;
        if (towerType == Element.Fire && hasSufficientRuins(
                GameConfig.getFrune(), GameConfig.getTowerCost())) {
            GameConfig.setFrune(GameConfig.getFrune() - GameConfig.getTowerCost());
            sufficientRunes = true;
        } else if (towerType == Element.Water && hasSufficientRuins(
                GameConfig.getWrune(), GameConfig.getTowerCost())) {
            GameConfig.setWrune(GameConfig.getWrune() - GameConfig.getTowerCost());
            sufficientRunes = true;
        } else if (towerType == Element.Ground && hasSufficientRuins(
                GameConfig.getGrune(), GameConfig.getTowerCost())) {
            GameConfig.setGrune(GameConfig.getGrune() - GameConfig.getTowerCost());
            sufficientRunes = true;
        } else if (towerType == Element.Air && hasSufficientRuins(
                GameConfig.getArune(), GameConfig.getTowerCost())) {
            GameConfig.setArune(GameConfig.getArune() - GameConfig.getTowerCost());
            sufficientRunes = true;
        }
        if (sufficientRunes) {
            GameScreen.getInfoPanel().updateInfo();
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

    public static boolean hasSufficientRuins(int runes, int cost) {
        return runes >= cost;
    }
}
