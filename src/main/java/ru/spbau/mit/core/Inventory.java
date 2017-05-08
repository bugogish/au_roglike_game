package ru.spbau.mit.core;

import ru.spbau.mit.core.items.Item;

import java.util.ArrayList;

public class Inventory {
    public static final String TITLE = "Inventory";
    // TODO : disallow more than MAX_CAPACITY items in inventory
    private static final int MAX_CAPACITY = 5;
    private ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
