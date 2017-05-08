package ru.spbau.mit.items;

// TODO : Implement - this is a placeholder

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.characters.Stats;

public final class ItemFactory {
    private ItemFactory(){}

    @NotNull
    public static Item createDefaultItem(ItemType type) {
        switch (type) {
            case DAGGER:
                return new Dagger();
            case SHIELD:
                return new Shield();
            default:
                return new Dagger();
        }
    }

    public Item createWithDescriptionAndStats(ItemType type, String description, Stats change) {
        switch (type) {
            case DAGGER:
                return new Dagger(description, change);
            case SHIELD:
                return new Shield(description, change);
            default:
                return null;
        }
    }

    public Item createCustomItem(ItemType type, String name, String description, Stats change, char icon) {
        switch (type) {
            case DAGGER:
                return new Dagger(name, description, change, icon);
            case SHIELD:
                return new Shield(name, description, change, icon);
            default:
                return null;
        }
    }
}
