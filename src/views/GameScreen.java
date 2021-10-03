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

    private int width;
    private int height;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getGameScene() {
        StackPane root = new StackPane();
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
        imageboxes.setLayoutX(780);
        imageboxes.setLayoutY(10);
        for (int i = 1; i <= 5; i++) {
            ImageView imageView = new ImageView(new Image("resources/arrow.png"));
            imageboxes.getChildren().add(imageView);
        }
        baselayer.getChildren().addAll(infopanel, imageboxes, healthlbl);
        root.getChildren().addAll(baselayer);
        return new Scene(root, this.width, this.height);
    }
}
