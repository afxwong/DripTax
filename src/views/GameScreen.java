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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

    private ArrayList<Enemy> enemy_Top;
    private ArrayList<Enemy> enemy_Bottom;

    public Button getToendscreen() {
        return toendscreen;
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.enemy_Top = new ArrayList<>();
        this.enemy_Bottom = new ArrayList<>();
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
        this.healthlbl = new Label(String.format("Wall Health: %s", GameConfig.getMonumentHealth()));
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

        for (int i = 0; i < GameConfig.getEnemyCount(); i++) {
            Enemy top = new Enemy(randomElement(), 1, 5, 820, 190, 5, this);
            Enemy bottom = new Enemy(randomElement(), 1, 5, 820, 290, 5, this);
            this.enemy_Top.add(top);
            this.enemy_Bottom.add(bottom);
            enemyAnchorPane.getChildren().addAll(top.getEnemysprite(), bottom.getEnemysprite());
        }

        // populate root and base layers
        baselayer.getChildren().addAll(infoPanel, imageboxes, healthlbl, enemyAnchorPane, clickableAnchorPane);

        // add button to end screen
        this.toendscreen = new Button("Proceed to End Screen");
        this.toendscreen.setVisible(false);


        root.getChildren().addAll(baselayer, this.toendscreen, this.startgame);
        return new Scene(root, this.width, this.height);
    }

    private void startGame() {
        this.startgame.setVisible(false);
        Timer timer = new Timer();
        final int[] i = {0};
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (i[0] < GameConfig.getEnemyCount() || GameConfig.getMonumentHealth() > 0) {
                    enemy_Top.get(i[0]).play();
                    enemy_Bottom.get(i[0]).play();
                    i[0]++;
                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(timerTask, 0, 2000);
    }

    public void updateHealth() {
        if (GameConfig.getMonumentHealth() <= 0) {
            this.toendscreen.setVisible(true);
        }
        this.healthlbl.setText(String.format("Wall Health: %s", GameConfig.getMonumentHealth()));
    }

    private Element randomElement() {
        Random rn = new Random();
        int randomInt = rn.nextInt(4);
        switch (randomInt) {
            case 0: return Element.Fire;
            case 1: return Element.Water;
            case 2: return Element.Air;
            case 3: return Element.Ground;
            default: return null;
        }
    }
}
