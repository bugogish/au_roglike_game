package ru.spbau.mit.core;

import ru.spbau.mit.items.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for Player's Inventory
 */
public class Inventory {
    public static final String TITLE = "Inventory";
    private static final int MAX_CAPACITY = 3;
    private Set<Item> items = new HashSet<>();

    /**
     * adds item to Inventory
     */
    public void add(Item item) {
        items.add(item);
    }

    public Set<Item> getItems() {
        return items;
    }

    /**
     * @return true if Inventory is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * removes item from Inventory
     */
    public void dropItem(Item item) {
        items.remove(item);
    }
}
