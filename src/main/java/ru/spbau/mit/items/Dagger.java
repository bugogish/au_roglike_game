package ru.spbau.mit.items;

import ru.spbau.mit.characters.Stats;
import ru.spbau.mit.core.Cell;

/**
 * Class for game's weapon
 */
public class Dagger extends Item {
    private static final String DEFAULT_NAME = "Dagger";
    private static final char DEFAULT_ICON = '☭';
    private static final Stats DEFAULT_STATS = new Stats(0, 15, 0);

    public Dagger(String name, Stats changeToStats, char icon, Cell position) {
        super(name, changeToStats, icon, position);
    }

    public Dagger(Stats changeToStats, Cell position) {
        this(DEFAULT_NAME, changeToStats, DEFAULT_ICON, position);
    }

    public Dagger(Cell position) {
        this(DEFAULT_NAME, DEFAULT_STATS, DEFAULT_ICON, position);
    }
}
