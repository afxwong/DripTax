package components;

import entities.Enemy;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import entities.Player;
import entities.Tower;
import javafx.util.Duration;
import resources.Enums.Element;
import javafx.scene.image.ImageView;
import resources.GameConfig;
import views.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class TowerGridCell extends Pane {

    private int[] gridIndex;
    private boolean selected;
    private boolean hasTower;
    private Tower tower;
    private ImageView projectile;

    public TowerGridCell(int i, int j) {
        this.gridIndex = new int[] {i, j};
        this.selected = false;
        this.hasTower = false;
        this.setMinSize(40, 50);
        if (j % 2 == 0) {
            initTowerCell(i, j);
        } else {
            initPathCell();
        }
    }

    private void initTowerCell(int i, int j) {
        this.setOnMouseEntered(mouseEvent -> {
            if (this.selected) {
                this.setStyle("-fx-border-color: white");
            } else {
                this.setStyle("-fx-border-color: red");
            }
        });
        this.setOnMouseExited(mouseEvent -> {
            if (this.selected) {
                this.setStyle("-fx-border-color: lawngreen");
            } else {
                this.setStyle("-fx-border-color: black");
            }
        });
        this.setOnMouseClicked(mouseEvent -> {
            //System.out.println("Pane #" + i + "-" + j + " clicked");
            int[] tile = new int[] {i, j};
            this.selected = Player.selectTile(tile);
            if (this.selected) {
                this.setStyle("-fx-border-color: white");
            } else {
                this.setStyle("-fx-border-color: red");
            }
        });
        this.setStyle("-fx-border-color: black");
    }

    private void initPathCell() {

    }
    public void select() {
        this.selected = true;
        this.setStyle("-fx-border-color: lawngreen");
    }

    public void deselect() {
        this.selected = false;
        this.setStyle("-fx-border-color: black");
    }

    public void placeTower(Element e) {
        if (!this.hasTower) {
            this.hasTower = true;
            this.tower = new Tower(e);
            System.out.println(e + " tower placed at Pane #"
                    + this.gridIndex[0] + "-" + this.gridIndex[1]);
            Element towerElem = tower.getElement();
            String pngName = "resources/" + towerElem.toString().toLowerCase() + "Tower.png";
            ImageView towerPic = new ImageView(new Image(pngName, 40, 45, false, false));
            this.projectile = new ImageView(new Image("resources/"
                    + towerElem.toString().toLowerCase() + "Bullet.png", 40, 45, false, false));
            this.getChildren().add(towerPic);
            startProjectiles();
        } else {
            // Shouldn't get here probably. Need to fix.
            System.out.println("Removed tower at Pane #"
                    + this.gridIndex[0] + "-" + this.gridIndex[1]);
            removeTower();
        }
    }

    public void removeTower() {
        this.getChildren().clear();
        this.hasTower = false;
        this.tower = null;
    }

    public boolean getHasTower() {
        return hasTower;
    }

    public void startProjectiles() {
//        this.projectile.setX(this.getLayoutX());
//        this.projectile.setY(this.getLayoutY());
        TranslateTransition transition = new TranslateTransition();
        this.getChildren().add(this.projectile);
        double locationX = this.getLayoutX();
//        transition.setFromX(this.getLayoutX());
//        System.out.println(this.getLayoutX());
//        System.out.println(this.getLayoutY());
//        transition.setFromY(this.getLayoutY());

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                transition.setDuration(Duration.millis(100));
                transition.setNode(projectile);
                transition.setCycleCount(1);
                Enemy target = GameScreen.enemyTop.get(0);
                System.out.println(GameScreen.enemyTop.get(0));
                System.out.println(target);
                System.out.println(target.getTransition().getNode().getTranslateX());
                double destinationX = GameScreen.calculateXPosition(
                        target.getTransition().getNode().getTranslateX(), 820, locationX);
                transition.setFromX(0);
                transition.setFromY(0);
                transition.setToX(destinationX);
                transition.setToY(-50);
                transition.play();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 300);

        transition.setOnFinished(actionEvent -> GameScreen.enemyTop.get(0)
                .setHealth(GameScreen.enemyTop.get(0).getHealth() - GameScreen.enemyTop.get(0).getDamage()));
    }
}
