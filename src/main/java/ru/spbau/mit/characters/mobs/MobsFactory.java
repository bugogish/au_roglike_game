package ru.spbau.mit.characters.mobs;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class MobsFactory {
    private MobsFactory() {};

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

    @NotNull
    public static Mob createRandomMob() {
        Random rand = new Random();
        MobType type = MobType.values()[rand.nextInt(MobType.values().length)];
        return createMob(type);
    }
}
