package ru.spbau.mit.core;

public class Stats {
    // TODO : Remove placeholder default stats
    public static final Stats DEFAULT_STATS = new Stats(100, 100, 1);
    private int health;
    private int stamina;
    private int armor;

    public Stats(int health, int stamina, int armor) {
        this.health = health;
        this.stamina = stamina;
        this.armor = armor;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int delta) {
        health -= delta;
    }

    public void addStats(Stats another) {
        this.health += another.health;
        this.stamina += another.stamina;
        this.armor += another.armor;
//        return new Stats(this.health + another.health,
//                        this.stamina + another.stamina,
//                        this.armor + another.armor);
    }
}
