package ru.spbau.mit.core.items;

// TODO : Implement - this is a placeholder

public class ItemFactory {
    private ItemFactory(){}

    public static Item createItem(ItemType type) {
        switch (type) {
            case DAGGER: {
                return new Dagger();
            }
            case SHIELD: {
                return new Shield();
            }
            default: {
                return null;
            }
        }
    }
}
