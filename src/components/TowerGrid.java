package components;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TowerGrid extends GridPane {

    private final int griddimension_i = 15;
    private final int griddimension_j = 5;
    private TowerGridCell[][] towerpanes;

    public Pane[][] getTowerpanes() {
        return towerpanes;
    }

    public void setTowerpanes(TowerGridCell[][] towerpanes) {
        this.towerpanes = towerpanes;
    }

    public TowerGrid() {
        this.towerpanes = new TowerGridCell[this.griddimension_i][this.griddimension_j];
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j++) {
                TowerGridCell towerGridCell = new TowerGridCell(i, j);
                this.add(towerGridCell, i, j);
                this.towerpanes[i][j] = towerGridCell;
            }
        }
        this.setLayoutX(227);
        this.setLayoutY(135);
        this.prefWidth(390);
        this.prefHeight(157);
    }
}
