package test;

import components.HotbarComponent;
import resources.Enums;
import resources.GameConfig;

import static components.HotbarComponent.hasSufficientRuins;
import static org.junit.jupiter.api.Assertions.*;

public class HotbarComponentTest {
    @org.junit.jupiter.api.Test
    void hasSufficientRunesTest() {
        GameConfig.setDifficulty(Enums.Difficulty.Easy);
        GameConfig config = new GameConfig();
        assertTrue(hasSufficientRuins(GameConfig.getFrune(), config.calculateTowerCost(config.getDifford())));
    }
}
