package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.characters.Character;
import ru.spbau.mit.characters.Stats;

public abstract class Mob extends Character {
    public Mob(char icon, Stats baseStats) {
        super(icon, baseStats);
    }
}
