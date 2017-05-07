package ru.spbau.mit.core.mobs;

import java.util.Random;

public final class MobsFactory {
    private MobsFactory() {};

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

    public static Mob createRandomMob() {
        Random rand = new Random();
        MobType type = MobType.values()[rand.nextInt(MobType.values().length)];
        return createMob(type);
    }
}
