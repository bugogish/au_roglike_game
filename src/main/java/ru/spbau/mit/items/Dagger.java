package ru.spbau.mit.items;

import ru.spbau.mit.characters.Stats;

public class Dagger extends Item {
    private static final String DEFAULT_NAME = "Dagger";
    private static final char DEFAULT_ICON = '☭';
    private static final Stats DEFAULT_STATS = new Stats(0, 15, 0);

    public Dagger(String name, String description, Stats changeToStats, char icon) {
        super(name, description, changeToStats, icon);
    }

    public Dagger(String description, Stats changeToStats) {
        this(DEFAULT_NAME, description, changeToStats, DEFAULT_ICON);
    }

    public Dagger() {
        this(DEFAULT_NAME, "", DEFAULT_STATS, DEFAULT_ICON);
    }
}
