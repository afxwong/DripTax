package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import resources.Enums;
import resources.Enums.Element;
import resources.GameConfig;
import views.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    private Element element;

    private int health;
    private int damage;
    private int x;
    private int y;
    private boolean visible;
    private int number;
    private double speed;
    private int killReward;
    private GameScreen gameScreen;
    private Enums.EnemyLane lane;
    private ImageView enemysprite;
    private TranslateTransition transition;

    public Enemy(Element ele, int health, int damage, int x, int y,
                 double traveltime, GameScreen gameScreen, Enums.EnemyLane lane, int number) {
        this.element = ele;
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.visible = false;
        this.speed = traveltime;
        this.killReward = 10;
        this.gameScreen = gameScreen;
        this.transition = new TranslateTransition();
        this.transition.setToX(-600);
        this.transition.setDuration(Duration.seconds(traveltime));
        this.enemysprite = new ImageView(new Image("resources/"
                + ele.toString().toLowerCase() + "Car.png", 50, 50, false, false));
        this.enemysprite.setX(x);
        this.enemysprite.setY(y);
        this.transition.setCycleCount(1);
        this.transition.setNode(this.enemysprite);
        this.lane = lane;
        this.number = number;
        this.enemysprite.setVisible(false);
    }

    public void play() {
        this.visible = true;
        this.enemysprite.setVisible(true);
        this.transition.play();
        Timer timer = new Timer();

        // will be for checking health
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                System.out.println(transition.getNode().getTranslateX());
                if (health <= 0) {
                    transition.stop();
                    enemysprite.setVisible(false);
                    if (lane == Enums.EnemyLane.Top) {
                        GameScreen.enemyTop.remove(0);
                    } else {
                        GameScreen.enemyBottom.remove(0);
                    }
                    timer.cancel();
                    timer.purge();
                    // Gain rune of enemy's type on death
                    switch (element) {
                        case Fire:
                            GameConfig.addFrune(killReward);
                            break;
                        case Water:
                            GameConfig.addWrune(killReward);
                            break;
                        case Ground:
                            GameConfig.addGrune(killReward);
                            break;
                        case Air:
                            GameConfig.addArune(killReward);
                            break;
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);

        this.transition.setOnFinished(actionEvent -> {
            if (lane == Enums.EnemyLane.Top) {
                GameScreen.enemyTop.remove(0);
            } else {
                GameScreen.enemyBottom.remove(0);
            }
            GameConfig.setMonumentHealth(GameConfig.getMonumentHealth()
                    - GameConfig.getEnemyDamage());
            this.gameScreen.updateHealth();
            this.enemysprite.setVisible(false);
            task.cancel();
            timer.cancel();
            timer.purge();
        });
    }

    public ImageView getEnemysprite() {
        return enemysprite;
    }

    public TranslateTransition getTransition() {
        return transition;
    }

    public int getNumber() {
        return number;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public boolean isVisible() {
        return visible;
    }
}
