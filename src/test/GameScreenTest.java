package test;

import javafx.embed.swing.JFXPanel;
import resources.GameConfig;
import views.GameScreen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameScreenTest {
    @org.junit.jupiter.api.Test
    void TestRandomElement() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        assertTrue(gameScreen.randomElement() != null);
    }

    @org.junit.jupiter.api.Test
    void TestGameOver() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        GameConfig.setMonumentHealth(0);
        gameScreen.updateHealth();
        assertTrue(gameScreen.getToendscreen().isVisible());
    }

    @org.junit.jupiter.api.Test
    void TestGameContinue() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        GameConfig.setMonumentHealth(1);
        gameScreen.updateHealth();
        assertFalse(gameScreen.getToendscreen().isVisible());
    }
}
