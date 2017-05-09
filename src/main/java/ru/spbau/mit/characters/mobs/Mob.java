package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.characters.Character;
import ru.spbau.mit.characters.Stats;

/**
 * Class for an abstract Mob
 */

public abstract class Mob extends Character {
    public Mob(char icon, Stats baseStats) {
        super(icon, baseStats);
    }
}
