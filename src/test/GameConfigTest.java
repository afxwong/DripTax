package test;

import resources.Enums;
import resources.GameConfig;

import static org.junit.jupiter.api.Assertions.*;

public class GameConfigTest {
    @org.junit.jupiter.api.Test
    void testCalculateRuneCount() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertEquals(config.calculateRuneCount(1), 1);
        assertEquals(config.calculateRuneCount(2), 2);
        assertEquals(config.calculateRuneCount(3), 3);
    }

    @org.junit.jupiter.api.Test
    void testCalculateTowerHealth() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertEquals(config.calculateTowerHealth(1), 1000);
        assertEquals(config.calculateTowerHealth(2), 2000);
        assertEquals(config.calculateTowerHealth(3), 3000);
    }
}
