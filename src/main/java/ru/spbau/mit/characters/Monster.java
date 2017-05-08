package ru.spbau.mit.characters;

public class Monster extends Mob {
    private static final char DEFAULT_ICON = '⏧';
    private static final Stats DEFAULT_STATS = new Stats(20, 10, 2);
    private static final double ARMOR_POWER_DECREASE = 0.4;

    public Monster() {
        super(DEFAULT_ICON, DEFAULT_STATS);
    }

    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
