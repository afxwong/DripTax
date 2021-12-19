package entities;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import resources.Enums;
import resources.Enums.Element;
import resources.GameConfig;
import views.GameScreen;

import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    private Element element;

    public void setHealth(int health) {
        this.health = health;
    }

    private int health;
    private int damage;
    private int x;
    private int y;
    private double sortkey;

    public int getNumber() {
        return number;
    }

    private int number;
    private double speed;
    private GameScreen gameScreen;
    private Enums.EnemyLane lane;

    public StackPane getStackPane() {
        return stackPane;
    }

    private StackPane stackPane;
    private Label healthLabel;

    public ImageView getEnemysprite() {
        return enemysprite;
    }

    private ImageView enemysprite;

    public TranslateTransition getTransition() {
        return transition;
    }

    private TranslateTransition transition;

    public Enemy(Element ele, int health, int x, int y,
                 double traveltime, GameScreen gameScreen, Enums.EnemyLane lane) {
        this.element = ele;
        this.health = health;
        this.damage = 10; // BASE DAMAGE
        this.x = x;
        this.y = y;
        this.speed = traveltime;
        this.gameScreen = gameScreen;
        this.transition = new TranslateTransition();
        this.transition.setToX(-600);
        this.enemysprite = new ImageView(new Image("resources/"
                + ele.toString().toLowerCase() + "Car.png", 50, 50, false, false));
        this.healthLabel = new Label(String.valueOf(this.health));
        this.healthLabel.setTextFill(Color.RED);
        this.stackPane = new StackPane();
        this.stackPane.setLayoutX(x);
        this.stackPane.setLayoutY(y);
        this.stackPane.getChildren().addAll(this.enemysprite, this.healthLabel);
        this.transition.setCycleCount(1);
        this.transition.setNode(this.stackPane);
        this.lane = lane;
        this.number = number;
        this.sortkey = this.transition.getNode().getTranslateX();
        switch (ele) {
        case Fire:
            this.health *= 1;
            this.damage *= 1;
            this.speed *= 1;
            break;
        case Water:
            this.health *= 2;
            this.damage *= 1;
            this.speed *= 2;
            break;
        case Ground:
            this.health *= 3;
            this.damage *= 3;
            this.speed *= 3;
            break;
        case Air:
            this.health *= 0.5;
            this.damage *= 0.5;
            this.speed *= 0.5;
            break;
        case Boss:
            this.health *= 50;
            this.damage *= 50;
            this.speed *= 4;
            break;
        default:
            break;
        }
        this.transition.setDuration(Duration.seconds(this.speed));
    }

    public void updateHealthLabel() {
        this.healthLabel.setText(String.valueOf(this.health));
    }

    private Comparator<Enemy> enemyComparator = Comparator.comparingDouble(Enemy::getSortkey);

    public void play() {
        this.transition.play();
        Timer timer = new Timer();

        // will be for checking health
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!GameScreen.getIsRunning()) {
                    this.cancel();
                    timer.cancel();
                    timer.purge();
                } else {
                    updateSortkey();
                    if (health <= 0) {
                        transition.stop();
                        stackPane.setVisible(false);
                        GameConfig.setEnemiesKilled(GameConfig.getEnemiesKilled() + 1);
                        GameScreen.getEnemyBottom().sort(enemyComparator);
                        GameScreen.getEnemyTop().sort(enemyComparator);
                        if (lane == Enums.EnemyLane.Top) {
                            switch (GameScreen.getEnemyTop().get(0).getElement()) {
                                case Fire:
                                    GameConfig.addFrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Water:
                                    GameConfig.addWrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Ground:
                                    GameConfig.addGrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Air:
                                    GameConfig.addArune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                default:
                                    break;
                            }
                            GameScreen.getEnemyTop().remove(0);
                        } else {
                            switch (GameScreen.getEnemyBottom().get(0).getElement()) {
                                case Fire:
                                    GameConfig.addFrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Water:
                                    GameConfig.addWrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Ground:
                                    GameConfig.addGrune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                case Air:
                                    GameConfig.addArune(GameConfig.getKillReward(
                                            GameConfig.getDifficulty()));
                                    break;
                                default:
                                    break;
                            }
                            GameScreen.getEnemyBottom().remove(0);
                        }
                        Platform.runLater(() -> GameScreen.getInfoPanel().updateInfo());
                        this.cancel();
                        timer.cancel();
                        timer.purge();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);

        this.transition.setOnFinished(actionEvent -> {
            if (lane == Enums.EnemyLane.Top && !GameScreen.getEnemyTop().isEmpty()) {
                GameScreen.getEnemyTop().remove(0);
            } else {
                if (!GameScreen.getEnemyBottom().isEmpty()) {
                    GameScreen.getEnemyBottom().remove(0);
                }
            }
            GameConfig.setMonumentHealth(GameConfig.getMonumentHealth()
                    - GameConfig.getEnemyDamage());
            this.gameScreen.updateHealth();
            this.stackPane.setVisible(false);
            task.cancel();
            timer.cancel();
            timer.purge();
        });
    }

    public int getDamage() {
        return damage;
    }

    public Element getElement() {
        return element;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemyDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public void updateSortkey() {
        this.sortkey = this.transition.getNode().getTranslateX();
    }

    public double getSortkey() {
        return this.sortkey;
    }
}
