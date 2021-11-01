package test;

import entities.Enemy;
import javafx.embed.swing.JFXPanel;
import views.GameScreen;
import resources.GameConfig;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Enums.Element.Fire;

public class EnemyTest {
    @org.junit.jupiter.api.Test
    void TestPlay() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        int initialHealth = GameConfig.getMonumentHealth();
        testEnemy.play();
        assertEquals(GameConfig.getMonumentHealth(), initialHealth - GameConfig.getEnemyDamage() * testEnemy.getDamage());
    }

    @org.junit.jupiter.api.Test
    void TestDamage() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(10, testEnemy.getDamage());
    }

    @org.junit.jupiter.api.Test
    void TestElement() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(Fire, testEnemy.getElement());
    }

    @org.junit.jupiter.api.Test
    void TestHealth() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(10, testEnemy.getHealth());
    }

    @org.junit.jupiter.api.Test
    void TestSpeed() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(5.0, testEnemy.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void TestX() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(0, testEnemy.getX());
    }

    @org.junit.jupiter.api.Test
    void TestY() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 10, 0, 0, 5, gameScreen);
        assertEquals(0, testEnemy.getY());
    }
}
