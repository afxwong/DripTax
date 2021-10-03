package resources;

public class GameConfig {

    private final int easyhealth = 3000;
    private final int easymoney = 3000;
    private final int medhealth = 2000;
    private final int medmoney = 2000;
    private final int hardhealth = 1000;
    private final int hardmoney = 1000;
    public static int towerhealth;
    public static int money;
    public static Enums.Difficulty difficulty;

    public GameConfig() {
        switch (difficulty) {
            case Easy:
                this.towerhealth = this.easyhealth;
                this.money = this.easymoney;
            case Medium:
                this.towerhealth = this.medhealth;
                this.money = this.medmoney;
            case Hard:
                this.towerhealth = this.hardhealth;
                this.money = this.hardmoney;
        }
    }
}
