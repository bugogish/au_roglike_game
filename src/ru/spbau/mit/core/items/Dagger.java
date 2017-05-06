package ru.spbau.mit.core.items;

import ru.spbau.mit.core.Stats;

public class Dagger extends Item {
    private static final String DEFAULT_NAME = "Dagger";
    private static final char DEFAULT_ICON = '☭';

    public Dagger(String name, String description, Stats changeToStats, char icon) {
        super(name, description, changeToStats, icon);
    }

    public Dagger(String description, Stats changeToStats) {
        this(DEFAULT_NAME, description, changeToStats, DEFAULT_ICON);
    }

    public Dagger() {
        this(DEFAULT_NAME, "", Stats.DEFAULT_STATS, DEFAULT_ICON);
    }
}
