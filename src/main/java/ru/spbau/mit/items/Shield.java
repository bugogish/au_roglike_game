package ru.spbau.mit.items;

import ru.spbau.mit.characters.Stats;
import ru.spbau.mit.core.Cell;

/**
 * Class for Defence Item in the game
 */
public class Shield extends Item {
    private static final String DEFAULT_NAME = "Shield";
    private static final char DEFAULT_ICON = 'Θ';
    private static final Stats DEFAULT_STATS = new Stats(0, -1, 20);

    public Shield(String name, Stats changeToStats, char icon, Cell position) {
        super(name, changeToStats, icon, position);
    }

    public Shield(Stats changeToStats, Cell position) {
        this(DEFAULT_NAME, changeToStats, DEFAULT_ICON, position);
    }

    public Shield(Cell position) {
        this(DEFAULT_NAME, DEFAULT_STATS, DEFAULT_ICON, position);
    }
}
