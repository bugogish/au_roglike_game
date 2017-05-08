package ru.spbau.mit.core;

import ru.spbau.mit.items.Item;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    public static final String TITLE = "Inventory";
    private static final int MAX_CAPACITY = 3;
    private Set<Item> items = new HashSet<>();

    public void add(Item item) {
        items.add(item);
    }

    public Set<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void dropItem(Item item) {
        items.remove(item);
    }
}
