package ru.spbau.mit.core.items;

import ru.spbau.mit.core.Stats;

public class Shield extends Item {
    private static final String DEFAULT_NAME = "Shield";
    private static final char DEFAULT_ICON = 'Θ';

    public Shield(String name, String description, Stats changeToStats, char icon) {
        super(name, description, changeToStats, icon);
    }

    public Shield(String description, Stats changeToStats) {
        this(DEFAULT_NAME, description, changeToStats, DEFAULT_ICON);
    }

    public Shield() {
        this(DEFAULT_NAME, "", Stats.DEFAULT_STATS, DEFAULT_ICON);
    }
}
