package ru.spbau.mit.core.mobs;

import ru.spbau.mit.core.Stats;

public class Monster extends Mob {
    private static final char DEFAULT_ICON = '⏧';
    private static final Stats DEFAULT_STATS = new Stats(20, 10, 2);
    private static final double FIGHT_COEFFICIENT = 1e-2;

    public Monster() {
        super(DEFAULT_ICON, DEFAULT_STATS);
    }

    @Override
    public int getFightPower() {
        return (int) Math.round(getStats().getStamina() * FIGHT_COEFFICIENT);
    }
}
