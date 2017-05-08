package ru.spbau.mit.mobs;

import ru.spbau.mit.core.Character;
import ru.spbau.mit.core.Stats;

public abstract class Mob extends Character {
    public Mob(char icon, Stats baseStats) {
        super(icon, baseStats);
    }
}
