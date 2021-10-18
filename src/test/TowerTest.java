package test;

import resources.Enums.Element;
import entities.Tower;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    @org.junit.jupiter.api.Test
    void testSwitchElement() {
        Tower tower  = new Tower(Element.Fire);
        tower.switchElement(Element.Water);
        assertEquals(tower.getElement(), Element.Water);
    }

    @org.junit.jupiter.api.Test
    void testGetElement() {
        Tower tower1 = new Tower(Element.Fire);
        assertEquals(tower1.getElement(), Element.Fire);
        Tower tower2 = new Tower(Element.Water);
        assertEquals(tower2.getElement(), Element.Water);
        Tower tower3 = new Tower(Element.Air);
        assertEquals(tower3.getElement(), Element.Air);
        Tower tower4 = new Tower(Element.Ground);
        assertEquals(tower4.getElement(), Element.Ground);
    }
}
