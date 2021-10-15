package test;

import resources.Enums;
import resources.GameConfig;

import static org.junit.jupiter.api.Assertions.*;

public class GameConfigTest {
    @org.junit.jupiter.api.Test
    void testCalculateRuneCount() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertEquals(config.calculateRuneCount(1), 100);
        assertEquals(config.calculateRuneCount(2), 200);
        assertEquals(config.calculateRuneCount(3), 300);
    }

    @org.junit.jupiter.api.Test
    void testCalculateTowerHealth() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertEquals(config.calculateTowerHealth(1), 1000);
        assertEquals(config.calculateTowerHealth(2), 2000);
        assertEquals(config.calculateTowerHealth(3), 3000);
    }

    @org.junit.jupiter.api.Test
    void testCalculateTowerCost() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertEquals(config.calculateTowerCost(3), 10);
        GameConfig.setDifficulty(Enums.Difficulty.Medium);
        assertEquals(config.calculateTowerCost(2), 20);
        GameConfig.setDifficulty(Enums.Difficulty.Hard);
        assertEquals(config.calculateTowerCost(1), 30);
    }
}
