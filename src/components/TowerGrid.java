package components;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import resources.Enums.Element;

public class TowerGrid extends GridPane {

    private final int griddimension_i = 15;
    private final int griddimension_j = 5;
    private static TowerGridCell[][] towerPanes;

    public Pane[][] getTowerpanes() {
        return towerPanes;
    }

    public void setTowerpanes(TowerGridCell[][] towerpanes) {
        this.towerPanes = towerpanes;
    }

    public TowerGrid() {
        this.towerPanes = new TowerGridCell[this.griddimension_i][this.griddimension_j];
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j++) {
                TowerGridCell towerGridCell = new TowerGridCell(i, j);
                this.add(towerGridCell, i, j);
                this.towerPanes[i][j] = towerGridCell;
            }
        }
        this.setLayoutX(227);
        this.setLayoutY(135);
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
}
