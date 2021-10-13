package views;

import components.InfoPanel;
import components.TowerGrid;
import javafx.geometry.Insets;
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
    public static AnchorPane clickableAnchorPane = new AnchorPane();
    public static InfoPanel infoPanel = new InfoPanel();

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
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

        // Health label in top left
        Label healthlbl = new Label(String.format("Wall Health: %s", GameConfig.getTowerhealth()));
        healthlbl.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FF0000;");
        healthlbl.setPadding(new Insets(5, 5, 5, 5));

        // Directional arrows for lanes
        VBox imageboxes = new VBox();
        imageboxes.setLayoutX(this.arrowsetX);
        imageboxes.setLayoutY(this.arrowsetY);
        imageboxes.setSpacing(this.arrowsetspacing);
        for (int i = 1; i <= 2; i++) {
            ImageView imageView = new ImageView(new Image("resources/arrow.png"));
            imageboxes.getChildren().add(imageView);
        }

        // add grid
        TowerGrid towergrid = new TowerGrid();
        clickableAnchorPane.getChildren().add(towergrid);

        // Tower hotbar at bottom of screen
        // HotbarComponent towerHotbar = new HotbarComponent();
        //clickableAnchorPane.getChildren().add(towerHotbar);

        // populate root and base layers
        baselayer.getChildren().addAll(infoPanel, imageboxes, healthlbl);
        // clickableAnchorPane holds all clickable items, including the tower grid and the tower hotbar
        // This is because if you try to add another pane for clicking items, it will be on top of this pane
        root.getChildren().addAll(baselayer, clickableAnchorPane);
        return new Scene(root, this.width, this.height);
    }
}
