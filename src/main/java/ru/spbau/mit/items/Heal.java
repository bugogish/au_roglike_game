package ru.spbau.mit.items;


import ru.spbau.mit.characters.Stats;
import ru.spbau.mit.core.Cell;

/**
 * Class for a healing item
 */
public class Heal extends Item {
    private static final String DEFAULT_NAME = "Heal";
    private static final char DEFAULT_ICON = '❤';
    private static final Stats DEFAULT_STATS = new Stats(10, 0, 0);

    public Heal(String name, Stats changeToStats, char icon, Cell position) {
        super(name, changeToStats, icon, position);
    }

    public Heal(Stats changeToStats, Cell position) {
        this(DEFAULT_NAME, changeToStats, DEFAULT_ICON, position);
    }

    public Heal(Cell position) {
        this(DEFAULT_NAME, DEFAULT_STATS, DEFAULT_ICON, position);
    }
}
