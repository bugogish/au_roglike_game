package ru.spbau.mit.core;

import ru.spbau.mit.core.items.Dagger;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.items.Shield;

import java.util.ArrayList;

public class Player extends Character {
    private static final char DEFAULT_ICON = 'Δ';
    private static final Stats baseStats = new Stats(100, 100, 1);;
    private Inventory mInventory = new Inventory();
    private static final double ARMOR_POWER_DECREASE = 0.2;
    private ArrayList<Item> equippedItems = new ArrayList<>();
    private Item weaponEquipped;
    private Item defenceEquipped;
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
            weaponEquipped = item;
        }
        if (item instanceof Shield) {
            defenceEquipped = item;
        }
    }

    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
