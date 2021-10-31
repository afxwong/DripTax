package views;

import components.InfoPanel;
import components.TowerGrid;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import resources.GameConfig;

public class GameScreen {

    private final int arrowsetX = 840;
    private final int arrowsetY = 190;
    private final int arrowsetspacing = 55;
    private Button toendscreen;
    private int width;
    private int height;
    private static AnchorPane clickableAnchorPane;
    private static InfoPanel infoPanel;

    public Button getToendscreen() {
        return toendscreen;
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        clickableAnchorPane = new AnchorPane();
        infoPanel = new InfoPanel();
    }

    public static AnchorPane getClickableAnchorPane() {
        return clickableAnchorPane;
    }

    public static InfoPanel getInfoPanel() {
        return infoPanel;
    }

    private void startGame() {
        // Remove startButton
        clickableAnchorPane.getChildren().remove(1);
        // Add cars movement functionality here
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

        // Start Button
        Button startButton = new Button();
        startButton.setText("Start Game");
        startButton.setMinSize(100, 50);
        startButton.setLayoutX(450);
        startButton.setOnAction(e -> {startGame();});
        clickableAnchorPane.getChildren().add(startButton);

        // populate root and base layers
        baselayer.getChildren().addAll(infoPanel, imageboxes, healthlbl);

        // add button to end screen
        this.toendscreen = new Button("Proceed to End Screen");
        this.toendscreen.setVisible(false);
        // TODO: make this button visible when game ends

        root.getChildren().addAll(baselayer, clickableAnchorPane, this.toendscreen);
        return new Scene(root, this.width, this.height);
    }
}
