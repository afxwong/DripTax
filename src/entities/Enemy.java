package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import resources.Enums;
import resources.Enums.Element;
import resources.GameConfig;
import views.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Pane {
    private Element element;

    private int health;
    private int maxHealth;
    private int damage;
    private int x;
    private int y;
    private boolean visible;
    private int number;
    private double speed;
    private int killReward;
    private GameScreen gameScreen;
    private Enums.EnemyLane lane;
    private ImageView enemySprite;
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
        this.enemySprite = new ImageView(new Image("resources/"
                + ele.toString().toLowerCase() + "Car.png", 50, 50, false, false));
        this.enemySprite.setX(x);
        this.enemySprite.setY(y);
        this.transition.setCycleCount(1);
        this.transition.setNode(this.enemySprite);
        this.lane = lane;
        this.number = number;
        this.enemySprite.setVisible(false);
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
        }
        this.maxHealth = this.health;
        this.transition.setDuration(Duration.seconds(this.speed));
    }

    public void play() {
        this.visible = true;
        this.enemySprite.setVisible(true);
        this.transition.play();
        Timer timer = new Timer();

        // will be for checking health
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                System.out.println(transition.getNode().getTranslateX());
                if (health <= 0) {
                    transition.stop();
                    enemySprite.setVisible(false);
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
            this.enemySprite.setVisible(false);
            task.cancel();
            timer.cancel();
            timer.purge();
        });
    }

    public ImageView getEnemysprite() {
        return enemySprite;
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

    public void takeDamage(int damage) {
        health -= damage;
        // Display health after taking damage (DOES NOT CURRENTLY WORK)
        Text healthIndicator = new Text();
        healthIndicator.setText(health + "/" + maxHealth);
        healthIndicator.setStyle("-fx-text-fill: #FF0000;");
        healthIndicator.setX(10);
        healthIndicator.setY(10);
        this.getChildren().add(healthIndicator);
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

    public boolean isActive() {
        return visible;
    }
}
