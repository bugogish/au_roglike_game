package ru.spbau.mit.core;

public class Stats {
    private int health;
    private int stamina;
    private int armor;

    public Stats() {
        health = 100;
        stamina = 100;
        armor = 1;
    }

    public Stats(int health, int stamina, int armor) {
        this.health = health;
        this.stamina = stamina;
        this.armor = armor;
    }

    public int getHealth() {
        return health;
    }

    public void addStats(Stats another) {
        this.health += another.health;
        this.stamina += another.stamina;
        this.armor += another.armor;
//        return new Stats(this.health + another.health,
//                        this.stamina + another.stamina,
//                        this.armor + another.armor);
    }

    public void decreaseHealth(int delta) {
        health -= delta;
    }
}
