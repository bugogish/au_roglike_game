package ru.spbau.mit.characters.mobs;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Class to create mobs by their type
 */
public final class MobsFactory {
    private MobsFactory() {};

    /**
     * @param type - type of mob to create, default is @code{Creep}
     * @return - new Mob of specified type
     */
    @NotNull
    public static Mob createMob(MobType type) {
        switch (type) {
            case CREEP:
                return new Creep();
            case MONSTER:
                return new Monster();
            case BOSS:
                return new Boss();
            default:
                return new Creep();
        }
    }

    /**
     * @return creates new Mob of random type
     */
    @NotNull
    public static Mob createRandomMob() {
        Random rand = new Random();
        MobType type = MobType.values()[rand.nextInt(MobType.values().length)];
        return createMob(type);
    }
}
