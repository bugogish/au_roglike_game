package ru.spbau.mit.core.mobs;

import ru.spbau.mit.core.Stats;

public class Creep extends Mob {
    private static final char DEFAULT_ICON = '♞';
    private static final Stats DEFAULT_STATS = new Stats(10, 5, 1);
    private static final double FIGHT_COEFFICIENT = 1e-3;

    public Creep() {
        super(DEFAULT_ICON, DEFAULT_STATS);
    }

    @Override
    public int getFightPower() {
        return (int) Math.round(getStats().getStamina() * FIGHT_COEFFICIENT);
    }
}
