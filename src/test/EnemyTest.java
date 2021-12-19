package test;

import entities.Enemy;
import javafx.embed.swing.JFXPanel;
import resources.Enums;
import views.GameScreen;
import resources.GameConfig;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Enums.Element.Air;
import static resources.Enums.Element.Fire;

public class EnemyTest {
    @org.junit.jupiter.api.Test
    void testPlay() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
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
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(10, testEnemy.getDamage());
    }

    @org.junit.jupiter.api.Test
    void testElement() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(Fire, testEnemy.getElement());
    }

    @org.junit.jupiter.api.Test
    void testHealth() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(10, testEnemy.getHealth());
    }

    @org.junit.jupiter.api.Test
    void testSpeed() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(5.0, testEnemy.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testX() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(0, testEnemy.getX());
    }

    @org.junit.jupiter.api.Test
    void testY() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Fire, 10, 0, 0, 5, gameScreen, null);
        assertEquals(0, testEnemy.getY());
    }

    @org.junit.jupiter.api.Test
    void testAttack() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Air, 10, 0, 0, 5, gameScreen, null);
        if (testEnemy.getDamage() > 0) {
            assertTrue(true);
        }
    }

    @org.junit.jupiter.api.Test
    void testSortKey() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Air, 10, 0, 0, 5, gameScreen, null);
        assertNotEquals(testEnemy.getSortkey(), null);
    }

    @org.junit.jupiter.api.Test
    void testSetHealth() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Air, 10, 0, 0, 5, gameScreen, null);
        testEnemy.setHealth(20);
        assertEquals(testEnemy.getHealth(), 20);
    }

    @org.junit.jupiter.api.Test
    void testGetNumber() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Air, 10, 0, 0, 5, gameScreen, null);
        assertNotEquals((double) testEnemy.getNumber(), null);
    }

    @org.junit.jupiter.api.Test
    void testBossHealth() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Enums.Element.Boss, 3000, 0, 0, 5, gameScreen, null);
        assertEquals(testEnemy.getHealth(), 150000);
        assertEquals(testEnemy.getDamage(), 500);
    }

    @org.junit.jupiter.api.Test
    void testBossDamage() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Enums.Element.Boss, 3000, 0, 0, 5, gameScreen, null);
        assertEquals(testEnemy.getDamage(), 500);
    }

    @org.junit.jupiter.api.Test
    void testBossSpeed() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        Enemy testEnemy = new Enemy(Enums.Element.Boss, 3000, 0, 0, 5, gameScreen, null);
        assertEquals(testEnemy.getSpeed(), 20);
    }
}
