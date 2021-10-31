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
        assertEquals(GameConfig.calculateTowerCost(Enums.Difficulty.Easy), 10);
        assertEquals(GameConfig.calculateTowerCost(Enums.Difficulty.Medium), 20);
        assertEquals(GameConfig.calculateTowerCost(Enums.Difficulty.Hard), 30);
    }
}
