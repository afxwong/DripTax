package entities;

import resources.Enums.Element;

public class Tower {
    private Element element;
    private int level;

    public Tower(Element e) {
        this.element = e;
        this.level = 0;
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
}
