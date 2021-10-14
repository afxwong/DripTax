package components;

import javafx.scene.layout.Pane;
import entities.Player;
import entities.Tower;
import resources.Enums.Element;

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
        } else {
            System.out.println("Replaced tower with " + e + " tower at Pane #" + this.gridIndex[0] + "-" + this.gridIndex[1]);
        }
    }

    public void removeTower() {
        this.hasTower = false;
        this.tower = null;
    }
}
