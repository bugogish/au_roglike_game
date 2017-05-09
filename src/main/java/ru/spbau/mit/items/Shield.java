package ru.spbau.mit.items;

import ru.spbau.mit.characters.Stats;

/**
 * Class for Defence Item in the game
 */
public class Shield extends Item {
    private static final String DEFAULT_NAME = "Shield";
    private static final char DEFAULT_ICON = 'Θ';
    private static final Stats DEFAULT_STATS = new Stats(0, -1, 20);

    public Shield(String name, String description, Stats changeToStats, char icon) {
        super(name, description, changeToStats, icon);
    }

    public Shield(String description, Stats changeToStats) {
        this(DEFAULT_NAME, description, changeToStats, DEFAULT_ICON);
    }

    public Shield() {
        this(DEFAULT_NAME, "", DEFAULT_STATS, DEFAULT_ICON);
    }
}
