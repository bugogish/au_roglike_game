package ru.spbau.mit.core.items;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.core.Stats;
import ru.spbau.mit.core.GUI.Drawable;

import java.io.IOException;
import java.util.Random;

public abstract class Item extends Drawable {
    private final String itemName;
    private final String description;
    private Stats changeToStats;

    public Item(String name, String description, Stats changeToStats, char itemIcon) {
        super(itemIcon, new TerminalPosition(0, 0));

        itemName = name;
        this.description = description;
        this.changeToStats = changeToStats;
    }

    public String getItemName() {
        return itemName;
    }
}
