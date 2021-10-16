package test;

import components.TowerGridCell;
import components.TowerGrid;
import entities.Tower;
import javafx.embed.swing.JFXPanel;
import resources.Enums;
import resources.Enums.Element;

import static org.junit.jupiter.api.Assertions.*;

public class TowerGridCellTest {

    @org.junit.jupiter.api.Test
    void testPlaceTower() {
        JFXPanel fxPanel = new JFXPanel();
        TowerGridCell tgc = new TowerGridCell(0,0);
        tgc.placeTower(Element.Fire);
        assertTrue(tgc.getHasTower());
    }

    @org.junit.jupiter.api.Test
    void testRemoveTower() {
        JFXPanel fxPanel = new JFXPanel();
        TowerGridCell tgc = new TowerGridCell(0,0);
        tgc.placeTower(Element.Fire);
        tgc.removeTower();
        assertFalse(tgc.getHasTower());
    }
}
