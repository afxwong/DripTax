package entities;

import components.TowerGrid;
import components.HotbarComponent;
import views.GameScreen;

public class Player {

    private static int[] selectedTile = new int[] {-1, -1};
    private static boolean tileIsSelected;

    public Player() {
    }

    // Selects/deselects a tile and returns true if a tile is selected, false if a tile is no longer selected
    public static boolean selectTile(int[] tile) {
        //System.out.println("selectedTile: " + selectedTile[0] + "-" + selectedTile[1]);
        //System.out.println("tile: " + tile[0] + "-" + tile[1]);
        TowerGrid.deselectAll();
        // Select new tile
        if (!(selectedTile[0] == tile[0] && selectedTile[1] == tile[1])) {
            selectedTile = tile;
            tileIsSelected = true;
            // Add hotbar if only no hotbar showing
            if (GameScreen.clickableAnchorPane.getChildren().size() < 2) {
                GameScreen.clickableAnchorPane.getChildren().add(new HotbarComponent());
            }
        } else { // Deselect selected tile
            selectedTile = new int[] {-1, -1};
            tileIsSelected = false;
            // Remove hotbar
            GameScreen.clickableAnchorPane.getChildren().remove(1);
        }
        return tileIsSelected;
    }

    public static void deselectSelectedTile() {
        if (tileIsSelected) {
            selectTile(selectedTile);
        } else {
            System.out.println("No tile selected");
        }
    }

    public static int[] getSelectedTile() {
        return selectedTile;
    }

    public static boolean getTileIsSelected() {
        return tileIsSelected;
    }
}