package ru.spbau.mit.core;

import ru.spbau.mit.core.items.Dagger;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.items.Shield;

import java.util.ArrayList;

public class Player extends Character {
    private static final char DEFAULT_ICON = 'Δ';
    private static final Stats baseStats = new Stats(100, 100, 1);
    private Inventory mInventory = new Inventory();
    private static final double ARMOR_POWER_DECREASE = 0.2;
    private Item weaponEquipped = null;
    private Item defenceEquipped = null;
    private static final double FIGHT_COEFFICIENT = 0.01;

    public Player() {
        super(DEFAULT_ICON, baseStats);
    }

    public void pickUp(Item item) {
        mInventory.add(item);
    }

    public Inventory getInventory() {
        return mInventory;
    }

    public void equipItem(Item item) {
        if (item instanceof Dagger) {
            if (weaponEquipped != null) {
                getStats().subtractStats(weaponEquipped.getStats());
            }
            getStats().addStats(item.getStats());
            weaponEquipped = item;
        }

        if (item instanceof Shield) {
            if (defenceEquipped != null) {
                getStats().subtractStats(defenceEquipped.getStats());
            }
            getStats().addStats(item.getStats());
            defenceEquipped = item;
        }
    }

    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
