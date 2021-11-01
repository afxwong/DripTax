package test;

import javafx.embed.swing.JFXPanel;
import views.GameScreen;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameScreenTest {
    @org.junit.jupiter.api.Test
    void TestRandomElement() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        if (gameScreen.randomElement() != null) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }
}
