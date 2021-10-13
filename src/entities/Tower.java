package entities;

import resources.Enums.Element;

public class Tower {
    private Element element;
    private int level;

    public Tower(Element e) {
        element = e;
        level = 0;
    }

    // Various methods implemented now that will be used soon
    public void switchElement(Element e) {
        element = e;
    }

    public void upgradeLevel() {
        this.level++;
    }
}
