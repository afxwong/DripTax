package views;

import components.InfoPanel;
import components.TowerGrid;
import entities.Enemy;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import resources.Enums.Element;
import resources.GameConfig;
import java.util.Random;

public class GameScreen {

    private final int arrowsetX = 840;
    private final int arrowsetY = 190;
    private final int arrowsetspacing = 55;
    private Button toendscreen;
    private Button startgame;
    private Label healthlbl;
    private int width;
    private int height;
    private static AnchorPane clickableAnchorPane;
    private static InfoPanel infoPanel;
    private static AnchorPane enemyAnchorPane;

    private Enemy enemy_Top;
    private Enemy enemy_Bottom;

    public Button getToendscreen() {
        return toendscreen;
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        clickableAnchorPane = new AnchorPane();
        infoPanel = new InfoPanel();
        enemyAnchorPane = new AnchorPane();
    }

    public static AnchorPane getClickableAnchorPane() {
        return clickableAnchorPane;
    }

    public static InfoPanel getInfoPanel() {
        return infoPanel;
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
        this.healthlbl = new Label(String.format("Wall Health: %s", GameConfig.getTowerHealth()));
        this.healthlbl.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FF0000;");
        this.healthlbl.setPadding(new Insets(5, 5, 5, 5));

        // Directional arrows for lanes
        VBox imageboxes = new VBox();
        imageboxes.setLayoutX(this.arrowsetX);
        imageboxes.setLayoutY(this.arrowsetY);
        imageboxes.setSpacing(this.arrowsetspacing);
        for (int i = 1; i <= 2; i++) {
            ImageView imageView = new ImageView(new Image("resources/arrow.png"));
            imageboxes.getChildren().add(imageView);
        }

        // Start Button
        this.startgame = new Button();
        this.startgame.setText("Start Game");
        this.startgame.setMinSize(100, 50);
        this.startgame.setLayoutX(450);
        this.startgame.setLayoutY(0);
        this.startgame.setOnAction(e -> startGame());

        // add grid
        TowerGrid towergrid = new TowerGrid();
        clickableAnchorPane.getChildren().add(towergrid);

        // Tower hotbar at bottom of screen
        // HotbarComponent towerHotbar = new HotbarComponent();
        //clickableAnchorPane.getChildren().add(towerHotbar);

        enemy_Top = new Enemy(randomElement(), 1, 1, 820, 190, 5, this);
        enemy_Bottom = new Enemy(randomElement(), 1, 1, 820, 290, 5, this);
        enemyAnchorPane.getChildren().addAll(enemy_Top.getEnemysprite(), enemy_Bottom.getEnemysprite());

        // populate root and base layers
        baselayer.getChildren().addAll(infoPanel, imageboxes, healthlbl, enemyAnchorPane, clickableAnchorPane);

        // add button to end screen
        this.toendscreen = new Button("Proceed to End Screen");
        this.toendscreen.setVisible(false);
        // TODO: make this button visible when game ends


        root.getChildren().addAll(baselayer, this.toendscreen, this.startgame);
        return new Scene(root, this.width, this.height);
    }

    private void startGame() {
        this.startgame.setVisible(false);
        enemy_Top.play();
        enemy_Bottom.play();
    }

    public void updateHealth() {
        this.healthlbl.setText(String.format("Wall Health: %s", GameConfig.getTowerHealth()));
    }

    private Element randomElement() {
        Random rn = new Random();
        int randomInt = rn.nextInt(4) + 1;
        switch (randomInt) {
            case 0: return Element.Fire;
            case 1: return Element.Water;
            case 2: return Element.Air;
            case 3: return Element.Ground;
            default: return null;
        }
    }
}
