package ru.spbau.mit.core;

import java.util.ArrayList;

public class Player extends Character {
    private final Stats baseStats = new Stats();
    private Stats currentStats = baseStats;
    private Inventory mInventory = new Inventory();
//    private ArrayList<Item> mInventory = new ArrayList<>();
    private ArrayList<Item> equippedItems;

    public Player() {
        super('Δ');
    }

    public void pickUp(Item item) {
        mInventory.add(item);
    }

    public Inventory getmInventory() {
        return mInventory;
    }

    @Override
    public int getFightPower() {
        return 0;
    }
}
