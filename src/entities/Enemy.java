package entities;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import resources.GameConfig;

import java.util.Timer;
import java.util.TimerTask;

public class Enemy {
    private int health;
    private int damage;
    private int x;
    private int y;
    private int speed;

    public ImageView getEnemysprite() {
        return enemysprite;
    }

    private ImageView enemysprite;
    private TranslateTransition transition;

    public Enemy(int health, int damage, int x, int y, int speed) {
        this.health = health;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.transition = new TranslateTransition();
        this.transition.setToX(-600);
        this.transition.setDuration(Duration.seconds(5));
        this.enemysprite = new ImageView(new Image("resources/arrow.png"));
        this.enemysprite.setX(x);
        this.enemysprite.setY(y);
        this.transition.setCycleCount(1);
        this.transition.setNode(this.enemysprite);
    }

    public void play() {
        this.transition.play();
        Timer timer = new Timer();
        this.transition.setOnFinished(actionEvent -> {

        });

        // will be for checking health
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(transition.currentTimeProperty().get());
                if (transition.currentTimeProperty().get().greaterThan(Duration.millis(2500))) {
                    transition.stop();
                    enemysprite.setVisible(false);
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }
}
