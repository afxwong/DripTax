package resources;

public class GameConfig {

    private final int scalefactorHealth = 1000;
    private final int scalefactorrune = 100;
    public final int scalefactorTowerCost = 10;
    private int difford;
    private static int towerhealth;
    private static int towerCost;
    private static int frune;
    private static int wrune;
    private static int grune;
    private static int arune;
    private static Enums.Difficulty difficulty;

    public GameConfig() {
        this.difford = difficulty.ordinal() + 1;
        towerhealth = calculateTowerHealth(difford);
        frune = calculateRuneCount(difford);
        wrune = calculateRuneCount(difford);
        grune = calculateRuneCount(difford);
        arune = calculateRuneCount(difford);
        towerCost = calculateTowerCost(difford);
    }

    public int getDifford() { return difford; }

    public static int getTowerCost() {
        return towerCost;
    }

    public static void setTowerCost(int towerCost) {
        GameConfig.towerCost = towerCost;
    }

    public static int getTowerhealth() {
        return towerhealth;
    }

    public static void setTowerhealth(int towerhealth) {
        GameConfig.towerhealth = towerhealth;
    }

    public static int getFrune() {
        return frune;
    }

    public static void setFrune(int frune) {
        GameConfig.frune = frune;
    }

    public static int getWrune() {
        return wrune;
    }

    public static void setWrune(int wrune) {
        GameConfig.wrune = wrune;
    }

    public static int getGrune() {
        return grune;
    }

    public static void setGrune(int grune) {
        GameConfig.grune = grune;
    }

    public static int getArune() {
        return arune;
    }

    public static void setArune(int arune) {
        GameConfig.arune = arune;
    }

    public static Enums.Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Enums.Difficulty difficulty) {
        GameConfig.difficulty = difficulty;
    }

    public int calculateRuneCount(int difford) {
        return difford * this.scalefactorrune;
    }

    public int calculateTowerHealth(int difford) {
        return difford * this.scalefactorHealth;
    }

    public int calculateTowerCost(int difford) {
        if (difficulty == Enums.Difficulty.Easy) {
            return (difford - 2) * this.scalefactorTowerCost;
        }
        return difficulty == Enums.Difficulty.Medium ? difford * this.scalefactorTowerCost
                : (difford + 2) * this.scalefactorTowerCost;
    }
}
