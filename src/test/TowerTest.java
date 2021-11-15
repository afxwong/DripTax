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

    @org.junit.jupiter.api.Test
    void testTowerAttack() {
        Tower test = new Tower(Element.Fire);
        assertNotEquals((double) test.getDamage(), null);
        assertNotEquals(test.getRange(), null);
        assertNotEquals((double) test.getSpeed(), null);
    }

    @org.junit.jupiter.api.Test
    void testTowerTypes() {
        Tower t1 = new Tower(Element.Fire);
        assertEquals(t1.getRange(), 200);
        assertEquals(t1.getDamage(), 25);
        assertEquals(t1.getSpeed(), 300);
        Tower t2 = new Tower(Element.Water);
        assertEquals(t2.getRange(), 300);
        assertEquals(t2.getDamage(), 25);
        assertEquals(t2.getSpeed(), 500);
        Tower t3 = new Tower(Element.Ground);
        assertEquals(t3.getRange(), 100);
        assertEquals(t3.getDamage(), 50);
        assertEquals(t3.getSpeed(), 800);
        Tower t4 = new Tower(Element.Air);
        assertEquals(t4.getRange(), 500);
        assertEquals(t4.getDamage(), 10);
        assertEquals(t4.getSpeed(), 100);
    }

    @org.junit.jupiter.api.Test
    void testTowerRange() {
        Tower t1 = new Tower(Element.Fire);
        assertNotEquals(t1.getRange(), null);
        Tower t2 = new Tower(Element.Water);
        assertNotEquals(t2.getRange(), null);
        Tower t3 = new Tower(Element.Ground);
        assertNotEquals(t3.getRange(), null);
        Tower t4 = new Tower(Element.Air);
        assertNotEquals(t4.getRange(), null);
    }

    @org.junit.jupiter.api.Test
    void testTowerDamage() {
        Tower t1 = new Tower(Element.Fire);
        assertNotEquals((double) t1.getDamage(), null);
        Tower t2 = new Tower(Element.Water);
        assertNotEquals((double) t2.getDamage(), null);
        Tower t3 = new Tower(Element.Ground);
        assertNotEquals((double) t3.getDamage(), null);
        Tower t4 = new Tower(Element.Air);
        assertNotEquals((double) t4.getDamage(), null);
    }

    @org.junit.jupiter.api.Test
    void testTowerSpeed() {
        Tower t1 = new Tower(Element.Fire);
        assertNotEquals((double) t1.getSpeed(), null);
        Tower t2 = new Tower(Element.Water);
        assertNotEquals((double) t2.getSpeed(), null);
        Tower t3 = new Tower(Element.Ground);
        assertNotEquals((double) t3.getSpeed(), null);
        Tower t4 = new Tower(Element.Air);
        assertNotEquals((double) t4.getSpeed(), null);
    }

    @org.junit.jupiter.api.Test
    void testTowerLevelUp() {
        Tower t = new Tower(Element.Fire);
        int prevLevel = t.getLevel();
        t.upgradeLevel();
        assertEquals(t.getLevel(), prevLevel+1);
    }


}
