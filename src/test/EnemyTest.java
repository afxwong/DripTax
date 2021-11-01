package test;

import entities.Enemy;
import javafx.embed.swing.JFXPanel;
import views.GameScreen;
import resources.GameConfig;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Enums.Element.Fire;

public class EnemyTest {
    @org.junit.jupiter.api.Test
    void testPlay() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        int initialHealth = GameConfig.getMonumentHealth();
        testEnemy.play();
        assertEquals(GameConfig.getMonumentHealth(),
                initialHealth - GameConfig.getEnemyDamage() * testEnemy.getDamage());
    }

    @org.junit.jupiter.api.Test
    void testDamage() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(10, testEnemy.getDamage());
    }

    @org.junit.jupiter.api.Test
    void testElement() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(Fire, testEnemy.getElement());
    }

    @org.junit.jupiter.api.Test
    void testHealth() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(10, testEnemy.getHealth());
    }

    @org.junit.jupiter.api.Test
    void testSpeed() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(5.0, testEnemy.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testX() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(0, testEnemy.getX());
    }

    @org.junit.jupiter.api.Test
    void testY() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(0, testEnemy.getY());
    }
}
