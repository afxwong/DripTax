package components;

import entities.Enemy;
import entities.Player;
import entities.Tower;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import resources.Enums;
import resources.Enums.Element;
import views.GameScreen;

import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class TowerGridCell extends Pane {

    private int[] gridIndex;
    private boolean selected;
    private boolean hasTower;
    private Tower tower;
    private ImageView projectile;
    private Enums.TowerLane towerLane;

    public TowerGridCell(int i, int j) {
        this.gridIndex = new int[] {i, j};
        this.selected = false;
        this.hasTower = false;
        this.setMinSize(40, 50);
        this.towerLane = j == 0 ? Enums.TowerLane.Top : j == 2
                ? Enums.TowerLane.Middle : Enums.TowerLane.Bottom;
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
            // You can get here by trying to place a tower on a tile with a tower existing on it
            System.out.println("Replaced tower at Pane #"
                    + this.gridIndex[0] + "-" + this.gridIndex[1]);
            removeTower();
            placeTower(e);
        }
    }

    private Comparator<Enemy> enemyComparator = Comparator.comparingDouble(Enemy::getSortkey);

    public void removeTower() {
        this.getChildren().clear();
        this.hasTower = false;
        this.tower = null;
    }

    public boolean getHasTower() {
        return hasTower;
    }

    public void startProjectiles() {
        TranslateTransition transition = new TranslateTransition();
        this.getChildren().add(this.projectile);
        double locationX = this.getLayoutX();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (GameScreen.getIsRunning()) {
                    transition.setDuration(Duration.millis(100));
                    transition.setNode(projectile);
                    transition.setCycleCount(1);
                    transition.setFromX(0);
                    transition.setFromY(0);
                    GameScreen.getEnemyBottom().sort(enemyComparator);
                    GameScreen.getEnemyTop().sort(enemyComparator);
                    if (towerLane == Enums.TowerLane.Top) {
                        Enemy target = GameScreen.getEnemyTop().get(0);
                        double destinationX = GameScreen.calculateXPosition(
                                target.getTransition().getNode().getTranslateX(), 820, locationX);
                        transition.setToY(50);
                        transition.setToX(destinationX);
                        transition.play();
                    } else if (towerLane == Enums.TowerLane.Middle) {
                        Enemy targettop = GameScreen.getEnemyTop().get(0);
                        Enemy targetbottom = GameScreen.getEnemyBottom().get(0);
                        double destinationX = 0;
                        if (targetbottom.getTransition().getNode().getTranslateX()
                                < targettop.getTransition().getNode().getTranslateX()) {
                            destinationX = GameScreen.calculateXPosition(
                                    targetbottom.getTransition().getNode().getTranslateX(),
                                    820, locationX);
                            transition.setToY(50);
                        } else {
                            destinationX = GameScreen.calculateXPosition(
                                    targettop.getTransition().getNode().getTranslateX(),
                                    820, locationX);
                            transition.setToY(-50);
                        }
                        transition.setToX(destinationX);
                        transition.play();
                    } else {
                        Enemy target = GameScreen.getEnemyBottom().get(0);
                        double destinationX = GameScreen.calculateXPosition(
                                target.getTransition().getNode().getTranslateX(), 820, locationX);
                        transition.setToY(-50);
                        transition.setToX(destinationX);
                        transition.play();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, tower.getSpeed());

        transition.setOnFinished(actionEvent -> {
            GameScreen.getEnemyBottom().sort(enemyComparator);
            GameScreen.getEnemyTop().sort(enemyComparator);
            if (towerLane == Enums.TowerLane.Top) {
                GameScreen.getEnemyTop().get(0).setHealth(GameScreen.getEnemyTop()
                        .get(0).getHealth() - tower.getDamage());
                GameScreen.getEnemyTop().get(0).updateHealthLabel();
            } else if (towerLane == Enums.TowerLane.Middle) {
                if (GameScreen.getEnemyBottom().get(0).getTransition().getNode().getTranslateX()
                        < GameScreen.getEnemyTop().get(0).getTransition()
                        .getNode().getTranslateX()) {
                    GameScreen.getEnemyBottom().get(0).setHealth(GameScreen
                            .getEnemyBottom().get(0).getHealth()
                            - tower.getDamage());
                    GameScreen.getEnemyBottom().get(0).updateHealthLabel();
                } else {
                    GameScreen.getEnemyTop().get(0).setHealth(GameScreen
                            .getEnemyTop().get(0).getHealth()
                            - tower.getDamage());
                    GameScreen.getEnemyTop().get(0).updateHealthLabel();
                }
            } else {
                GameScreen.getEnemyBottom().get(0).setHealth(GameScreen
                        .getEnemyBottom().get(0).getHealth()
                        - tower.getDamage());
                GameScreen.getEnemyBottom().get(0).updateHealthLabel();
            }
        });
    }
}
