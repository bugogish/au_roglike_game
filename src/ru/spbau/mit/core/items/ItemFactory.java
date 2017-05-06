package ru.spbau.mit.core.items;

// TODO : Implement - this is a placeholder

import ru.spbau.mit.core.Stats;

public class ItemFactory {
    private ItemFactory(){}

    public static Item createDefaultItem(ItemType type) {
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

    public Item createWithDescriptionAndStats(ItemType type, String description, Stats change) {
        switch (type) {
            case DAGGER: {
                return new Dagger(description, change);
            }
            case SHIELD: {
                return new Shield(description, change);
            }
            default: {
                return null;
            }
        }
    }

    public Item createCustomItem(ItemType type, String name, String description, Stats change, char icon) {
        switch (type) {
            case DAGGER: {
                return new Dagger(name, description, change, icon);
            }
            case SHIELD: {
                return new Shield(name, description, change, icon);
            }
            default: {
                return null;
            }
        }
    }
}
