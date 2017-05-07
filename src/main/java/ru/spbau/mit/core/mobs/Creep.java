package ru.spbau.mit.core.mobs;

import ru.spbau.mit.core.Stats;

public class Creep extends Mob {
    private static final char DEFAULT_ICON = '♞';
    private static final Stats DEFAULT_STATS = new Stats(10, 5, 2);
    private static final double ARMOR_POWER_DECREASE = 0.5;

    public Creep() {
        super(DEFAULT_ICON, DEFAULT_STATS);
    }

    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
