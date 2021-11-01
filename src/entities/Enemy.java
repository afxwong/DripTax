package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import resources.Enums.Element;
import resources.GameConfig;
import views.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    private int health;
    private int damage;
    private int x;
    private int y;
    private double speed;
    private GameScreen gameScreen;

    public ImageView getEnemysprite() {
        return enemysprite;
    }

    private ImageView enemysprite;
    private TranslateTransition transition;

    public Enemy(Element ele, int health, int damage, int x, int y, double speed, GameScreen gameScreen) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.gameScreen = gameScreen;
        this.transition = new TranslateTransition();
        this.transition.setToX(-600);
        this.transition.setDuration(Duration.seconds(speed));
        this.enemysprite = new ImageView(new Image("resources/" + ele.toString() + "Car.png", 50, 50, false, false));
        this.enemysprite.setX(x);
        this.enemysprite.setY(y);
        this.transition.setCycleCount(1);
        this.transition.setNode(this.enemysprite);
    }

    public void play() {
        this.transition.play();
        Timer timer = new Timer();
        this.transition.setOnFinished(actionEvent -> {
            System.out.println(GameConfig.getTowerHealth());
            GameConfig.setTowerHealth(GameConfig.getTowerHealth() - GameConfig.getEnemyDamage() * damage);
            this.gameScreen.updateHealth();
            this.enemysprite.setVisible(false);
        });

        // will be for checking health
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                if (transition.currentTimeProperty().get().greaterThan(Duration.millis(2500))) {
//                    transition.stop();
//                    enemysprite.setVisible(false);
//                    timer.cancel();
//                    timer.purge();
//                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }
}
