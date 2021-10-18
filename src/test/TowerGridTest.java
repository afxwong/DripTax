package test;

import components.TowerGrid;

import static org.junit.jupiter.api.Assertions.*;

public class TowerGridTest {

    @org.junit.jupiter.api.Test
    void testGridLocation() {
        TowerGrid tg = new TowerGrid();
        assertEquals(tg.getGridX(), 227);
        assertEquals(tg.getGridY(), 135);
    }

}
