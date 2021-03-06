package views;

import components.InfoPanel;
import components.TowerGrid;
import components.TowerGridCell;
import entities.Enemy;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import resources.Enums;
import resources.Enums.Element;
import resources.GameConfig;

import java.util.*;

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

    public static List<Enemy> getEnemyTop() {
        return enemyTop;
    }

    public static void setEnemyTop(List<Enemy> enemyTop) {
        GameScreen.enemyTop = enemyTop;
    }

    public static List<Enemy> getEnemyBottom() {
        return enemyBottom;
    }

    public static void setEnemyBottom(List<Enemy> enemyBottom) {
        GameScreen.enemyBottom = enemyBottom;
    }

    public static boolean getIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        GameScreen.isRunning = isRunning;
    }

    private static List<Enemy> enemyTop;
    private static List<Enemy> enemyBottom;

    public static List<Enemy> getEnemyTopCopy() {
        return enemyTopCopy;
    }

    public static void setEnemyTopCopy(List<Enemy> enemyTopCopy) {
        GameScreen.enemyTopCopy = enemyTopCopy;
    }

    public static List<Enemy> getEnemyBottomCopy() {
        return enemyBottomCopy;
    }

    public static void setEnemyBottomCopy(List<Enemy> enemyBottomCopy) {
        GameScreen.enemyBottomCopy = enemyBottomCopy;
    }

    private static List<Enemy> enemyTopCopy;
    private static List<Enemy> enemyBottomCopy;
    private static double offsetX;
    private static double offsetY;
    private static boolean isRunning;

    public Button getToendscreen() {
        return toendscreen;
    }

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        enemyTop = new LinkedList<>();
        enemyBottom = new LinkedList<>();
        enemyTopCopy = new LinkedList<>();
        enemyBottomCopy = new LinkedList<>();
        clickableAnchorPane = new AnchorPane();
        infoPanel = new InfoPanel();
        enemyAnchorPane = new AnchorPane();
        isRunning = false;
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
        this.healthlbl = new Label(String.format("Wall Health: %s",
                GameConfig.getMonumentHealth()));
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
        for (Pane[] t : towergrid.getTowerpanes()) {
            for (Pane tgc : t) {
                TowerGridCell temp = (TowerGridCell) tgc;
                temp.setHasTower(false);
            }
        }
        clickableAnchorPane.getChildren().add(towergrid);
        offsetX = towergrid.getLayoutX();
        offsetY = towergrid.getLayoutY();

        for (int i = 0; i < GameConfig.getEnemyCount() - 1; i++) {
            Enemy top = new Enemy(randomElement(), 100,
                    GameConfig.getEnemyStartingX(), GameConfig.getEnemyTopStartingY(), 10,
                    this, Enums.EnemyLane.Top);
            Enemy bottom = new Enemy(randomElement(), 100,
                    GameConfig.getEnemyStartingX(), GameConfig.getEnemyBottomStartingY(), 10,
                    this, Enums.EnemyLane.Bottom);
            enemyTop.add(top);
            enemyBottom.add(bottom);
            enemyTopCopy.add(top);
            enemyBottomCopy.add(bottom);
            enemyAnchorPane.getChildren().addAll(top.getStackPane(), bottom.getStackPane());
        }
        Enemy topBoss = new Enemy(Element.Boss, 100, GameConfig.getEnemyStartingX(), GameConfig.getEnemyTopStartingY(), 10, this, Enums.EnemyLane.Top);
        enemyTop.add(topBoss);
        enemyTopCopy.add(topBoss);
        Enemy bottomBoss = new Enemy(Element.Boss, 100, GameConfig.getEnemyStartingX(), GameConfig.getEnemyBottomStartingY(), 10, this, Enums.EnemyLane.Bottom);
        enemyBottom.add(bottomBoss);
        enemyBottomCopy.add(bottomBoss);
        enemyAnchorPane.getChildren().addAll(topBoss.getStackPane(), bottomBoss.getStackPane());

        // populate root and base layers
        baselayer.getChildren().addAll(infoPanel, imageboxes,
                healthlbl, enemyAnchorPane, clickableAnchorPane);

        // add button to end screen
        this.toendscreen = new Button("Proceed to End Screen");
        this.toendscreen.setVisible(false);


        root.getChildren().addAll(baselayer, this.toendscreen, this.startgame);
        return new Scene(root, this.width, this.height);
    }

    private void startGame() {
        isRunning = true;
        GameConfig.setGameWon(false);
        this.startgame.setVisible(false);
        GameConfig.setStarttime(System.currentTimeMillis());
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        final int[] i = {0};
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (i[0] < GameConfig.getEnemyCount()
                        && GameConfig.getMonumentHealth() > 0) {
                    enemyTopCopy.get(i[0]).play();
                    enemyBottomCopy.get(i[0]).play();
                    i[0]++;
                } else {
                    this.cancel();
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(timerTask, 0, 2000);

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                if (enemyBottom.isEmpty() && enemyTop.isEmpty()) {
                    toendscreen.setVisible(true);
                    GameConfig.setEndtime(System.currentTimeMillis());
                    GameConfig.setGameWon(true);
                    this.cancel();
                    timer.purge();
                    timer.cancel();
                }
            }
        };
        timer1.schedule(timerTask1, 0, 100);
    }

    public void updateHealth() {
        if (GameConfig.getMonumentHealth() <= 0) {
            this.toendscreen.setVisible(true);
            GameConfig.setEndtime(System.currentTimeMillis());
        }
        this.healthlbl.setText(String.format("Wall Health: %s", GameConfig.getMonumentHealth()));
    }

    public Element randomElement() {
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

    public static double calculateXPosition(double translatedX, double startEnemyX,
                                            double projectileOriginX) {
        return startEnemyX + translatedX > projectileOriginX
                ? startEnemyX + translatedX - projectileOriginX - offsetX + 20
                : (startEnemyX + translatedX - projectileOriginX - offsetX - 50) * -1;
    }

    public static double calculateYPosition(double projectileOriginY, double startY) {
        return startY - projectileOriginY + 10;
    }
}
