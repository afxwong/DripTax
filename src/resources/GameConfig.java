package resources;

import java.time.Instant;

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
    private static int enemyStartingX = 820;
    private static int enemyTopStartingY = 190;
    private static int enemyBottomStartingY = 290;
    private static int enemiesKilled;
    private static long starttime;
    private static long endtime;

    public static long getStarttime() {
        return starttime;
    }

    public static void setStarttime(long starttime) {
        GameConfig.starttime = starttime;
    }

    public static long getEndtime() {
        return endtime;
    }

    public static void setEndtime(long endtime) {
        GameConfig.endtime = endtime;
    }

    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    public static void setEnemiesKilled(int enemiesKilled) {
        GameConfig.enemiesKilled = enemiesKilled;
    }

    public static boolean isGameWon() {
        return gameWon;
    }

    public static void setGameWon(boolean gameWon) {
        GameConfig.gameWon = gameWon;
    }

    private static boolean gameWon = false;


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
        return difficulty == Enums.Difficulty.Easy ? 11
                : difficulty == Enums.Difficulty.Medium ? 16 : 21;
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

    public static void setFrune(int amount) {
        GameConfig.frune = amount;
    }

    public static void addFrune(int amount) {
        GameConfig.frune += amount;
    }

    public static int getWrune() {
        return wrune;
    }

    public static void setWrune(int amount) {
        GameConfig.wrune = amount;
    }

    public static void addWrune(int amount) {
        GameConfig.wrune += amount;
    }

    public static int getGrune() {
        return grune;
    }

    public static void setGrune(int amount) {
        GameConfig.grune = amount;
    }

    public static void addGrune(int amount) {
        GameConfig.grune += amount;
    }

    public static int getArune() {
        return arune;
    }

    public static void setArune(int amount) {
        GameConfig.arune = amount;
    }

    public static void addArune(int amount) {
        GameConfig.arune += amount;
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
        if (difficulty == Enums.Difficulty.Easy) {
            return (difford - 2) * scaleFactorEnemyDamage;
        }
        return difficulty == Enums.Difficulty.Medium ? difford * scaleFactorTowerCost
                : (difford + 2) * scaleFactorEnemyDamage;
    }

    public static int getEnemyStartingX() {
        return enemyStartingX;
    }

    public static int getEnemyTopStartingY() {
        return enemyTopStartingY;
    }

    public static int getEnemyBottomStartingY() {
        return enemyBottomStartingY;
    }

    public static int getKillReward(Enums.Difficulty diff) {
        if (diff == Enums.Difficulty.Easy) {
            return 15;
        }
        if (diff == Enums.Difficulty.Medium) {
            return 10;
        }
        return 5;
    }
}
