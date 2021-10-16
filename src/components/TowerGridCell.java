package components;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import entities.Player;
import entities.Tower;
import resources.Enums.Element;
import javafx.scene.image.ImageView;

public class TowerGridCell extends Pane {

    private int[] gridIndex;
    private boolean selected;
    private boolean hasTower;
    private Tower tower;

    public TowerGridCell(int i, int j) {
        this.gridIndex = new int[] {i, j};
        this.selected = false;
        this.hasTower = false;
        this.setMinSize(40, 50);
        if (j % 2 == 0) {
            initTowerCell(i, j);
        } else {
            initPathCell();
        }
    }

    private void initTowerCell(int i, int j) {
        this.setOnMouseEntered(mouseEvent -> {
            if (this.selected) {
                this.setStyle("-fx-border-color: white");
            } else {
                this.setStyle("-fx-border-color: red");
            }
        });
        this.setOnMouseExited(mouseEvent -> {
            if (this.selected) {
                this.setStyle("-fx-border-color: lawngreen");
            } else {
                this.setStyle("-fx-border-color: black");
            }
        });
        this.setOnMouseClicked(mouseEvent -> {
            //System.out.println("Pane #" + i + "-" + j + " clicked");
            int[] tile = new int[] {i, j};
            this.selected = Player.selectTile(tile);
            if (this.selected) {
                this.setStyle("-fx-border-color: white");
            } else {
                this.setStyle("-fx-border-color: red");
            }
        });
        this.setStyle("-fx-border-color: black");
    }

    private void initPathCell() {

    }

    public void deselect() {
        this.selected = false;
        this.setStyle("-fx-border-color: black");
    }

    public void placeTower(Element e) {
        if (!this.hasTower) {
            this.hasTower = true;
            this.tower = new Tower(e);
            System.out.println(e + " tower placed at Pane #" + this.gridIndex[0] + "-" + this.gridIndex[1]);
            Element towerElem = tower.getElement();
            String pngName = "resources/" + towerElem.toString().toLowerCase() + "Tower.png";
            ImageView towerPic = new ImageView(new Image(pngName, 40, 45, false, false));
            this.getChildren().add(towerPic);
        } else {
            // TODO: currently removes tower
            System.out.println("Removed tower at Pane #" + this.gridIndex[0] + "-" + this.gridIndex[1]);
            removeTower();
        }
    }

    public void removeTower() {
        this.getChildren().clear();
        this.hasTower = false;
        this.tower = null;
    }

    public boolean getHasTower() {
        return hasTower;
    }
}
