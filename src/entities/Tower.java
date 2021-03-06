package entities;

import resources.Enums.Element;

public class Tower {
    private Element element;
    private int level;
    private double range;
    private int damage;
    private int speed;

    public Tower(Element e) {
        this.element = e;
        this.level = 0;
        switch (e) {
        case Fire:
            this.range = 200;
            this.damage = 25;
            this.speed = 300;
            break;
        case Water:
            this.range = 300;
            this.damage = 25;
            this.speed = 500;
            break;
        case Ground:
            this.range = 100;
            this.damage = 50;
            this.speed = 800;
            break;
        case Air:
            this.range = 500;
            this.damage = 10;
            this.speed = 100;
            break;
        default:
            break;
        }
    }
    public void upgradeTower(Element e) {
        upgradeLevel();
        this.element = e;
        switch (e) {
            case Fire:
                this.range = 200 + (50*this.level);
                this.damage = 25 + (25*this.level);
                this.speed = 300;
                break;
            case Water:
                this.range = 300;
                this.damage = 25 + (15*this.level);
                this.speed = 500;
                break;
            case Ground:
                this.range = 100 + (100*this.level);
                this.damage = 50 + (10*this.level);
                this.speed = 800;
                break;
            case Air:
                this.range = 500;
                this.damage = 10 + (30*this.level);
                this.speed = 100;
                break;
            default:
                break;

        }
    }
    // Various methods implemented now that will be used soon
    public void switchElement(Element e) {
        this.element = e;
    }

    public Element getElement() {
        return this.element;
    }

    public void upgradeLevel() {
        this.level++;
    }

    public double getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLevel() {
        return level;
    }
}
