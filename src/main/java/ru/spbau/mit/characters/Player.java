package ru.spbau.mit.characters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.items.Dagger;
import ru.spbau.mit.items.Heal;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.Shield;

/**
 * Class that represents the player
 */

public class Player extends Character {
    private static final char DEFAULT_ICON = '⛹';
    private static final Stats BASE_STATS = new Stats(100, 45, 1);
    private static final double ARMOR_POWER_DECREASE = 0.2;
    private static final Logger logger = LogManager.getLogger(Player.class);

    private Inventory mInventory = new Inventory();
    private Item weaponEquipped = null;
    private Item defenceEquipped = null;

    public Player() {
        super(DEFAULT_ICON, BASE_STATS);
    }

    public Player(char icon, Stats baseStats) {
        super(icon, baseStats);
    }

    /**
     * Adds item to inventory
     */
    public void pickUp(Item item) {
        mInventory.add(item);
    }

    public Inventory getInventory() {
        return mInventory;
    }

    /**
     * takes item off the player and subtracts its bonus stats
     */
    public void unEquipItem(@NotNull Item item) {
        item.setEquipped(false);
        getStats().subtractStats(item.getStats());

        if (item instanceof Dagger) {
            weaponEquipped = null;
            logger.info("Weapon unequipped, current stats: {}", getStats());
        }

        if (item instanceof Shield) {
            defenceEquipped = null;
            logger.info("Defence unequipped, current stats: {}", getStats());
        }
    }

    /**
     * equips item on the player by adding its bonus stats to player's
     * if player has already held item of this type, replaces it with a new one
     */
    public void equipItem(@NotNull Item item) {
        item.setEquipped(true);

        if (item instanceof Dagger) {
            if (weaponEquipped != null) {
                unEquipItem(weaponEquipped);
            }
            getStats().addStats(item.getStats());
            weaponEquipped = item;
            logger.info("Weapon equipped, current stats: {}", getStats());
        }

        if (item instanceof Shield) {
            if (defenceEquipped != null) {
                unEquipItem(defenceEquipped);
            }
            getStats().addStats(item.getStats());
            defenceEquipped = item;
            logger.info("Defence equipped, current stats: {}", getStats());
        }

        if (item instanceof Heal) {
            getStats().addStats(item.getStats());
            mInventory.dropItem(item);
            logger.info("Heal used, current stats: {}", getStats());
        }
    }

    /**
     * @return a number to subtract from another @code{Character} health when fight occurs
     */
    @Override
    public int getFightPower() {
        return Math.max(0, (int) Math.round(getStats().getStamina()
                - getStats().getArmor() * ARMOR_POWER_DECREASE));
    }
}
