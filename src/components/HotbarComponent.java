package components;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
        FireTowerLabel.setOnMouseClicked(mouseEvent -> System.out.println("FireTowerLabel clicked"));
        WaterTowerLabel.setOnMouseClicked(mouseEvent -> System.out.println("WaterTowerLabel clicked"));
        GroundTowerLabel.setOnMouseClicked(mouseEvent -> System.out.println("GroundTowerLabel clicked"));
        AirTowerLabel.setOnMouseClicked(mouseEvent -> System.out.println("AirTowerLabel clicked"));
        FireTowerLabel.setPickOnBounds(true);
        WaterTowerLabel.setPickOnBounds(true);
        GroundTowerLabel.setPickOnBounds(true);
        AirTowerLabel.setPickOnBounds(true);
        this.getChildren().addAll(FireTowerLabel, WaterTowerLabel, GroundTowerLabel, AirTowerLabel);
        this.setAlignment(Pos.CENTER);
    }
}
