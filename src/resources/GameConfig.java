package resources;

public class GameConfig {

    private final int scaleFactorHealth = 1000;
    private final int scaleFactorRune = 100;
    private static int scaleFactorTowerCost = 10;
    private static int scaleFactorEnemyDamage = 50;
    private int difford;
    private static int monumentHealth;
    private static int towerCost;
    private static int enemyDamage;
    private static int frune;
    private static int wrune;
    private static int grune;
    private static int arune;
    private static Enums.Difficulty difficulty;

    public GameConfig() {
        this.difford = difficulty.ordinal() + 1;
        monumentHealth = calculateMonumentHealth(difford);
        frune = calculateRuneCount(difford);
        wrune = calculateRuneCount(difford);
        grune = calculateRuneCount(difford);
        arune = calculateRuneCount(difford);
        towerCost = calculateTowerCost(difford);
        enemyDamage = calculateEnemyDamage(difford);
    }

    public static int getEnemyCount() {
        return difficulty == Enums.Difficulty.Easy ? 10 : difficulty == Enums.Difficulty.Medium ? 15 : 20;
    }

    public int getDifford() {
        return difford;
    }

    public static int getTowerCost() {
        return towerCost;
    }

    public static void setTowerCost(int towerCost) {
        GameConfig.towerCost = towerCost;
    }

    public static int getScaleFactorEnemyDamage() {
        return scaleFactorEnemyDamage;
    }

    public static void setScaleFactorEnemyDamage(int scaleFactorEnemyDamage) {
        GameConfig.scaleFactorEnemyDamage = scaleFactorEnemyDamage;
    }

    public static int getEnemyDamage() {
        return enemyDamage;
    }

    public static int getMonumentHealth() {
        return monumentHealth;
    }

    public static void setMonumentHealth(int monumentHealth) {
        GameConfig.monumentHealth = monumentHealth;
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
        return difford * this.scaleFactorRune;
    }

    public int calculateMonumentHealth(int difford) {
        return difford * this.scaleFactorHealth;
    }

    public int calculateTowerCost(int difford) {
        if (difficulty == Enums.Difficulty.Easy) {
            return (difford - 2) * scaleFactorTowerCost;
        }
        return difficulty == Enums.Difficulty.Medium ? difford * scaleFactorTowerCost
                : (difford + 2) * scaleFactorTowerCost;
    }

    public static int calculateTowerCost(Enums.Difficulty diff) {
        if (diff == Enums.Difficulty.Easy) {
            return (Enums.Difficulty.Easy.ordinal() - 1) * scaleFactorTowerCost;
        }
        return diff == Enums.Difficulty.Medium ? (Enums.Difficulty.Medium.ordinal() + 1)
                * scaleFactorTowerCost
                : (Enums.Difficulty.Hard.ordinal() + 3) * scaleFactorTowerCost;
    }

    public int calculateEnemyDamage(int difford) {
        return difford * scaleFactorEnemyDamage;
    }
}
