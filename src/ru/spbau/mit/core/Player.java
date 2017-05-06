package ru.spbau.mit.core;

import ru.spbau.mit.core.items.Dagger;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.items.Shield;

import java.util.ArrayList;

public class Player extends Character {
    private static final Stats baseStats = new Stats(100, 100, 1);;
    private Inventory mInventory = new Inventory();
    private Item weaponEquipped;
    private Item defenceEquipped;
    private static final double FIGHT_COEFFICIENT = 0.01;

    public Player() {
        super('Δ', baseStats);
    }

    public void pickUp(Item item) {
        mInventory.add(item);
    }

    public void equipItem(Item item) {
        if (item instanceof Dagger) {
            weaponEquipped = item;
        }
        if (item instanceof Shield) {
            defenceEquipped = item;
        }
    }

    public Inventory getmInventory() {
        return mInventory;
    }

    @Override
    public int getFightPower() {
        return (int) Math.round(getStats().getStamina() * FIGHT_COEFFICIENT);
    }
}
