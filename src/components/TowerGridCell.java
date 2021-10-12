package components;

import javafx.scene.layout.Pane;

public class TowerGridCell extends Pane {

    private boolean hasTower;

    public TowerGridCell(int i, int j) {
        this.hasTower = false;
        this.setMinSize(40, 50);
        if (j % 2 == 0) {
            initTowerCell(i, j);
        } else {
            initPathCell();
        }
    }

    private void initTowerCell(int i, int j) {
        this.setOnMouseEntered(mouseEvent -> this.setStyle("-fx-border-color: red"));
        this.setOnMouseExited(mouseEvent -> this.setStyle("-fx-border-color: black"));
        this.setOnMouseClicked(mouseEvent -> System.out.println("Pane #" + i + "-" + j + " clicked"));
        this.setStyle("-fx-border-color: black");
    }

    private void initPathCell() {

    }
}
