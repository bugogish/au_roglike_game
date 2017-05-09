package ru.spbau.mit.characters;

public class Stats {
    private int health;
    private int stamina;
    private int armor;

    public Stats(int health, int stamina, int armor) {
        this.health = health;
        this.stamina = stamina;
        this.armor = armor;
    }
    public Stats(Stats stats) {
        this.health = stats.getHealth();
        this.stamina = stats.getStamina();
        this.armor = stats.getArmor();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        return health == stats.health && stamina == stats.stamina && armor == stats.armor;
    }

    @Override
    public int hashCode() {
        int result = health;
        result = 31 * result + stamina;
        result = 31 * result + armor;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Health: %s, Stamina: %s, Armor: %s", health, stamina, armor);
    }
}
