package ru.spbau.mit.core;

public class Stats {
    // TODO : Remove placeholder default stats
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

    public int getStamina() {
        return stamina;
    }

    public int getArmor() {
        return armor;
    }

    public void decreaseHealth(int delta) {
        health -= delta;
    }

    public void subtractStats(Stats another) {
        this.health -= another.health;
        this.stamina -= another.stamina;
        this.armor -= another.armor;
    }

    public void addStats(Stats another) {
        this.health += another.health;
        this.stamina += another.stamina;
        this.armor += another.armor;
    }
}
