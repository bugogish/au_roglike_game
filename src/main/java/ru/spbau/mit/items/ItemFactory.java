package ru.spbau.mit.items;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.characters.Stats;

/**
 * Class that creates Items of specified type
 */
public final class ItemFactory {
    private ItemFactory(){}

    /**
     * creates Item of specified type with default settings,
     * default Item is Dagger
     */
    @NotNull
    public static Item createDefaultItem(ItemType type) {
        switch (type) {
            case DAGGER:
                return new Dagger();
            case SHIELD:
                return new Shield();
            case HEAL:
                return new Heal();
            default:
                return new Dagger();
        }
    }

    /**
     * creates slightly customized Item
     * @param type - type of an Item to create
     * @param description - Item's description
     * @param change - Change an Item does to player's stats if equipped
     */
    @NotNull
    public Item createWithDescriptionAndStats(ItemType type, String description, Stats change) {
        switch (type) {
            case DAGGER:
                return new Dagger(description, change);
            case SHIELD:
                return new Shield(description, change);
            default:
                return new Dagger(description, change);
        }
    }

    /**
     * creates fully customized Item
     * @param type - type of Item to create
     * @param name - Item's name
     * @param description - Item's description
     * @param change - Change an Item does to player's stats if equipped
     * @param icon - Item's icon for displaying on GUI
     */
    @NotNull
    public Item createCustomItem(ItemType type, String name, String description, Stats change, char icon) {
        switch (type) {
            case DAGGER:
                return new Dagger(name, description, change, icon);
            case SHIELD:
                return new Shield(name, description, change, icon);
            default:
                return new Dagger(name, description, change, icon);
        }
    }
}
