package components;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import resources.Enums.Element;

public class TowerGrid extends GridPane {

    private final int griddimensionI = 15;
    private final int griddimensionJ = 5;
    private static TowerGridCell[][] towerPanes;
    private final double gridX = 227;
    private final double gridY = 135;

    public Pane[][] getTowerpanes() {
        return towerPanes;
    }

    public void setTowerpanes(TowerGridCell[][] towerpanes) {
        towerPanes = towerpanes;
    }

    public TowerGrid() {
        towerPanes = new TowerGridCell[this.griddimensionI][this.griddimensionJ];
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j++) {
                TowerGridCell towerGridCell = new TowerGridCell(i, j);
                this.add(towerGridCell, i, j);
                towerPanes[i][j] = towerGridCell;
            }
        }
        this.setLayoutX(gridX);
        this.setLayoutY(gridY);
        this.prefWidth(390);
        this.prefHeight(157);
    }

    public static void deselectAll() {
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j += 2) {
                towerPanes[i][j].deselect();
            }
        }
    }

    public static void placeTower(int[] tileIndex, Element e) {
        towerPanes[tileIndex[0]][tileIndex[1]].placeTower(e);
    }

    public double getGridX() {
        return gridX;
    }

    public double getGridY() {
        return gridY;
    }
}
