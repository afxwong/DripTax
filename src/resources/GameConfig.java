package resources;

public class GameConfig {

    private final int SCALEFACTOR_health = 1000;
    private final int SCALEFACTOR_ruin = 1;
    public static int towerhealth;
    public static int fruin;
    public static int wruin;
    public static int gruin;
    public static int aruin;
    public static Enums.Difficulty difficulty;

    public GameConfig() {
        int difford = difficulty.ordinal() + 1;
        towerhealth = calculateTowerHealth(difford);
        fruin = calculateRuinCount(difford);
        wruin = calculateRuinCount(difford);
        gruin = calculateRuinCount(difford);
        aruin = calculateRuinCount(difford);
    }

    private int calculateRuinCount(int difford) {
        return difford * this.SCALEFACTOR_ruin;
    }

    private int calculateTowerHealth(int difford) {
        return difford * this.SCALEFACTOR_health;
    }
}
