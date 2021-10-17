package test;

import components.TowerGridCell;
import components.TowerGrid;
import entities.Tower;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;
import resources.Enums;
import resources.Enums.Element;

import java.awt.event.MouseEvent;
import java.util.Objects;

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

    @Test
    void testTileSelectColor() {
        JFXPanel fxPanel = new JFXPanel();
        TowerGridCell tgc = new TowerGridCell(0,0);
        String style1 = tgc.getStyle();
        tgc.select();
        String style2 = tgc.getStyle();
        assertEquals("-fx-border-color: black", style1);
        assertNotEquals("-fx-border-color: white", style1);
        assertNotEquals("-fx-border-color: red", style1);
        assertNotEquals("-fx-border-color: lawngreen", style1);
        assertNotEquals("-fx-border-color: black", style2);
        assertNotEquals("-fx-border-color: white", style2);
        assertNotEquals("-fx-border-color: red", style2);
        assertEquals("-fx-border-color: lawngreen", style2);

    }

}
