package ru.spbau.mit.items;

import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.characters.Stats;
import ru.spbau.mit.core.Cell;

/**
 * Class for an abstract game Item
 */
public abstract class Item extends Drawable {
    private final String itemName;
    private final String description;
    private Stats changeToStats;
    private boolean equipped = false;

    public Item(String name, String description, Stats changeToStats, char itemIcon) {
        super(itemIcon, new Cell(0, 0));

        itemName = name;
        this.description = description;
        this.changeToStats = changeToStats;
    }

    /**
     * @return true if this Item player has this Item equipped now
     */
    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public String getItemName() {
        return itemName;
    }

    public Stats getStats() {
        return changeToStats;
    }
}
