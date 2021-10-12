package components;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TowerGrid extends GridPane {

    private final int griddimension_i = 15;
    private final int griddimension_j = 5;

    public Pane[][] getTowerpanes() {
        return towerpanes;
    }

    public void setTowerpanes(Pane[][] towerpanes) {
        this.towerpanes = towerpanes;
    }

    private Pane[][] towerpanes;

    public TowerGrid() {
        this.towerpanes = new Pane[this.griddimension_i][this.griddimension_j];
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j++) {
                Pane p = new Pane();
                p.setMinSize(40, 50);
                if (j % 2 == 0) {
                    p.setOnMouseEntered(mouseEvent -> p.setStyle("-fx-border-color: red"));
                    p.setOnMouseExited(mouseEvent -> p.setStyle("-fx-border-color: black"));
                    int finalI = i; // This is here because
                    int finalJ = j; // "Variable used in lambda expression should be final or effectively final"
                    p.setOnMouseClicked(mouseEvent -> System.out.println("Pane #" + finalI + "-" + finalJ + " clicked"));
                    p.setStyle("-fx-border-color: black");
                }
                this.add(p, i, j);
                this.towerpanes[i][j] = p;
            }
        }
        this.setLayoutX(227);
        this.setLayoutY(135);
        this.prefWidth(390);
        this.prefHeight(157);
    }
}
