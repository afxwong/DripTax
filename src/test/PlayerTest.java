package test;

import components.TowerGrid;
import entities.Player;
import javafx.embed.swing.JFXPanel;
import views.GameScreen;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    // Selects upper left tile and tests to see if it is selected,
    // then it selects the tile again, and tests if it is
    // deselected
    @org.junit.jupiter.api.Test
    void testSelectTile() {
        // JFXPanel must be initialized or else a
        // will occur due to Labels being initialized before JavaFX is loaded
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        TowerGrid towerGrid = new TowerGrid();
        int[] tile = new int[] {0, 0};
        assertTrue(Player.selectTile(tile));
        assertArrayEquals(Player.getSelectedTile(), tile);
        assertFalse(Player.selectTile(tile));
        int[] unselectedTile = new int[] {-1, -1};
        assertArrayEquals(Player.getSelectedTile(), unselectedTile);
    }

    // Selects the upper left tile, deselects the selected tile,
    // tests if the selected tile is not what was selected
    // before, and tests if the selected tile is the flag value for an unselected tile
    @org.junit.jupiter.api.Test
    void testDeselectTile() {
        JFXPanel fxPanel = new JFXPanel();
        GameScreen gameScreen = new GameScreen(1000, 500);
        gameScreen.getGameScene();
        TowerGrid towerGrid = new TowerGrid();
        int[] tile = new int[] {0, 0};
        Player.selectTile(tile);
        Player.deselectSelectedTile();
        assertNotEquals(Player.getSelectedTile(), tile);
        int[] unselectedTile = new int[] {-1, -1};
        assertArrayEquals(Player.getSelectedTile(), unselectedTile);
    }
}
