package test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import resources.GameConfig;
import views.EndScreen;
import views.GameScreen;

import static org.junit.jupiter.api.Assertions.*;

public class EndScreenTest {
    @org.junit.jupiter.api.Test
    void testConstructor() {
        JFXPanel fxPanel = new JFXPanel();
        EndScreen e = new EndScreen(50, 50);
        assertEquals(e.getWidth(), 50);
        assertEquals(e.getHeight(), 50);
        assertEquals(e.getEndText().getText(), "GAME OVER");
        assertEquals(e.getExitbutton().getText(), "Exit Game");
        assertEquals(e.getPlayagainbutton().getText(), "Play Again");

    }

    @org.junit.jupiter.api.Test
    void testGetEndScene() {
        JFXPanel fxPanel = new JFXPanel();
        EndScreen e = new EndScreen(50, 50);
        assertNotEquals(e.getEndScene(), null);
    }

    @org.junit.jupiter.api.Test
    void testChangeEndText() {
        JFXPanel fxPanel = new JFXPanel();
        EndScreen e = new EndScreen(50, 50);
        e.changeEndText("Test!");
        assertEquals(e.getEndText().getText(), "Test!");
    }
}