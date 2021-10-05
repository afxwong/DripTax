package test;

import controller.Controller;
import resources.Enums;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    @org.junit.jupiter.api.Test
    void testNameIsNotValid() {
        Controller controller = new Controller();
        assertTrue(controller.nameIsNotValid(null));
        assertTrue(controller.nameIsNotValid("    "));
        assertTrue(controller.nameIsNotValid(""));
        assertFalse(controller.nameIsNotValid("This is a name."));
        assertFalse(controller.nameIsNotValid("Rohan"));
    }

    @org.junit.jupiter.api.Test
    void testDifficultyIsValid() {
        Controller controller = new Controller();
        assertFalse(controller.difficultyIsValid(null));
        assertTrue(controller.difficultyIsValid(Enums.Difficulty.Hard));
        assertTrue(controller.difficultyIsValid(Enums.Difficulty.Medium));
        assertTrue(controller.difficultyIsValid(Enums.Difficulty.Easy));
    }

    @org.junit.jupiter.api.Test
    void testInvalidConfig() {
        Controller controller = new Controller();
        assertNotEquals(controller.invalidConfig("", Enums.Difficulty.Hard), null);
        assertNotEquals(controller.invalidConfig("Rohan", null), null);
        assertNotEquals(controller.invalidConfig(" ", Enums.Difficulty.Medium), null);
        assertEquals(controller.invalidConfig("Rohan", Enums.Difficulty.Easy), null);
    }
}
