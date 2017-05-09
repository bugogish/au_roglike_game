package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.characters.Stats;

/**
 * Class for the weakest mob in game
 */
public class Creep extends Mob {
    private static final char DEFAULT_ICON = '♞';
    private static final Stats DEFAULT_STATS = new Stats(10, 5, 2);
    private static final double ARMOR_POWER_DECREASE = 0.5;

    public Creep() {
        super(DEFAULT_ICON, new Stats(DEFAULT_STATS));
    }

    /**
     * @return a number to subtract from another @code{Character} health when fight occurs
     */
    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
