package ru.spbau.mit.core.mobs;

import ru.spbau.mit.core.Stats;

public class Boss extends Mob {
    private static final char DEFAULT_ICON = '☠';
    private static final Stats DEFAULT_STATS = new Stats(50, 20, 5);
    private static final double FIGHT_COEFFICIENT = 1e-1;

    public Boss() {
        super(DEFAULT_ICON, DEFAULT_STATS);
    }

    @Override
    public int getFightPower() {
        return (int) Math.round(getStats().getStamina() * FIGHT_COEFFICIENT);
    }

}
