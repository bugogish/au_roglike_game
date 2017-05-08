package ru.spbau.mit.characters;

import ru.spbau.mit.core.Inventory;
import ru.spbau.mit.items.Dagger;
import ru.spbau.mit.items.Heal;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.Shield;

public class Player extends Character {
    private static final char DEFAULT_ICON = '⛹';
    private static final Stats BASE_STATS = new Stats(100, 45, 1);
    private static final double ARMOR_POWER_DECREASE = 0.2;

    private Inventory mInventory = new Inventory();
    private Item weaponEquipped = null;
    private Item defenceEquipped = null;

    public Player() {
        super(DEFAULT_ICON, BASE_STATS);
        draw();
    }

    public Player(char icon, Stats baseStats) {
        super(icon, baseStats);
    }

    public void pickUp(Item item) {
        mInventory.add(item);
    }

    public Inventory getInventory() {
        return mInventory;
    }

    public void unEquipItem(Item item) {
        item.setEquipped(false);

        if (item instanceof Dagger) {
            if (weaponEquipped != null) {
                getStats().subtractStats(weaponEquipped.getStats());
            }
        }

        if (item instanceof Shield) {
            if (defenceEquipped != null) {
                getStats().subtractStats(defenceEquipped.getStats());
            }
        }
    }

    public void equipItem(Item item) {
        if (item instanceof Dagger) {
            unEquipItem(item);
            getStats().addStats(item.getStats());
            weaponEquipped = item;
            weaponEquipped.setEquipped(true);
        }

        if (item instanceof Shield) {
            unEquipItem(item);
            getStats().addStats(item.getStats());
            defenceEquipped = item;
            defenceEquipped.setEquipped(true);
        }

        if (item instanceof Heal) {
            getStats().addStats(item.getStats());
            mInventory.dropItem(item);
        }
    }

    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina() - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
