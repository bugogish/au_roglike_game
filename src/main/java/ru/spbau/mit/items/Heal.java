package ru.spbau.mit.items;


import ru.spbau.mit.characters.Stats;

public class Heal extends Item {
    private static final String DEFAULT_NAME = "Heal";
    private static final char DEFAULT_ICON = '❤';
    private static final Stats DEFAULT_STATS = new Stats(10, 0, 0);

    public Heal(String name, String description, Stats changeToStats, char icon) {
        super(name, description, changeToStats, icon);
    }

    public Heal(String description, Stats changeToStats) {
        this(DEFAULT_NAME, description, changeToStats, DEFAULT_ICON);
    }

    public Heal() {
        this(DEFAULT_NAME, "", DEFAULT_STATS, DEFAULT_ICON);
    }
}
