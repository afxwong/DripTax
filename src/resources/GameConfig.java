package resources;

public class GameConfig {

    private final int scalefactorHealth = 1000;
    private final int scalefactorRuin = 1;

    public static int getTowerhealth() {
        return towerhealth;
    }

    public static void setTowerhealth(int towerhealth) {
        GameConfig.towerhealth = towerhealth;
    }

    public static int getFruin() {
        return fruin;
    }

    public static void setFruin(int fruin) {
        GameConfig.fruin = fruin;
    }

    public static int getWruin() {
        return wruin;
    }

    public static void setWruin(int wruin) {
        GameConfig.wruin = wruin;
    }

    public static int getGruin() {
        return gruin;
    }

    public static void setGruin(int gruin) {
        GameConfig.gruin = gruin;
    }

    public static int getAruin() {
        return aruin;
    }

    public static void setAruin(int aruin) {
        GameConfig.aruin = aruin;
    }

    public static Enums.Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Enums.Difficulty difficulty) {
        GameConfig.difficulty = difficulty;
    }

    private static int towerhealth;
    private static int fruin;
    private static int wruin;
    private static int gruin;
    private static int aruin;
    private static Enums.Difficulty difficulty;

    public GameConfig() {
        int difford = difficulty.ordinal() + 1;
        towerhealth = calculateTowerHealth(difford);
        fruin = calculateRuinCount(difford);
        wruin = calculateRuinCount(difford);
        gruin = calculateRuinCount(difford);
        aruin = calculateRuinCount(difford);
    }

    public int calculateRuinCount(int difford) {
        return difford * this.scalefactorRuin;
    }

    public int calculateTowerHealth(int difford) {
        return difford * this.scalefactorHealth;
    }
}
