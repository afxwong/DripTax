package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import resources.GameConfig;

public class GameScreen {

    private final int arrowsetX = 840;
    private final int arrowsetY = 190;
    private final int arrowsetspacing = 55;
    private int width;
    private int height;
    private Pane[][] towerpanes;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.towerpanes = new Pane[15][5];
    }

    public Scene getGameScene() {
        // root pane that has all panes stacked on it
        StackPane root = new StackPane();
        // base pane that has all m2 reqs
        AnchorPane baselayer = new AnchorPane();
        root.setBackground(new Background(new BackgroundImage(
                new Image("resources/mapbackground.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));
        VBox infopanel = new VBox();
        infopanel.setStyle("-fx-background-color: #808080");
        infopanel.setMinSize(150, 100);
        infopanel.prefWidth(150);
        infopanel.prefHeight(100);
        infopanel.setLayoutX(850);
        infopanel.setLayoutY(0);
        Label frunelbl = new Label(String.format("Fire rune: %s", GameConfig.getFrune()));
        Label wrunelbl = new Label(String.format("Water rune: %s", GameConfig.getWrune()));
        Label grunelbl = new Label(String.format("Ground rune: %s", GameConfig.getGrune()));
        Label arunelbl = new Label(String.format("Air rune: %s", GameConfig.getArune()));
        infopanel.getChildren().addAll(frunelbl, wrunelbl, grunelbl, arunelbl);
        infopanel.setAlignment(Pos.CENTER);

        Label healthlbl = new Label(String.format("Wall Health: %s", GameConfig.getTowerhealth()));
        healthlbl.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FF0000;");
        healthlbl.setPadding(new Insets(5, 5, 5, 5));

        VBox imageboxes = new VBox();
        imageboxes.setLayoutX(this.arrowsetX);
        imageboxes.setLayoutY(this.arrowsetY);
        imageboxes.setSpacing(this.arrowsetspacing);
        for (int i = 1; i <= 2; i++) {
            ImageView imageView = new ImageView(new Image("resources/arrow.png"));
            imageboxes.getChildren().add(imageView);
        }

        // add grid
        AnchorPane towergridanchorpane = new AnchorPane();
        GridPane towergrid = new GridPane();
//        towergrid.setGridLinesVisible(true);
        for (int i = 0; i <= 14; i++) {
            for (int j = 0; j <= 4; j++) {
                Pane p = new Pane();
                p.setMinSize(40, 50);
                if (j % 2 == 0) {
                    p.setOnMouseEntered(mouseEvent -> p.setStyle("-fx-border-color: red"));
                    p.setOnMouseExited(mouseEvent -> p.setStyle("-fx-border-color: black"));
                    p.setStyle("-fx-border-color: black");
                }
                towergrid.add(p, i, j);
                this.towerpanes[i][j] = p;
            }
        }

        towergrid.setLayoutX(227);
        towergrid.setLayoutY(135);
        towergrid.prefWidth(390);
        towergrid.prefHeight(157);
        towergridanchorpane.getChildren().add(towergrid);


        // populate root and base layers
        baselayer.getChildren().addAll(infopanel, imageboxes, healthlbl);
        root.getChildren().addAll(baselayer, towergridanchorpane);
        return new Scene(root, this.width, this.height);
    }
}
