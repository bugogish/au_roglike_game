package ru.spbau.mit.items;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.characters.Stats;
import ru.spbau.mit.core.Cell;

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
    public static Item createDefaultItem(ItemType type, Cell position) {
        switch (type) {
            case DAGGER:
                return new Dagger(position);
            case SHIELD:
                return new Shield(position);
            case HEAL:
                return new Heal(position);
            default:
                return new Dagger(position);
        }
    }

    /**
     * creates slightly customized Item
     * @param type - type of an Item to create
     * @param change - Change an Item does to player's stats if equipped
     */
    @NotNull
    public Item createWithDescriptionAndStats(ItemType type, Stats change, Cell position) {
        switch (type) {
            case DAGGER:
                return new Dagger(change, position);
            case SHIELD:
                return new Shield(change, position);
            default:
                return new Dagger(change, position);
        }
    }

    /**
     * creates fully customized Item
     * @param type - type of Item to create
     * @param name - Item's name
     * @param change - Change an Item does to player's stats if equipped
     * @param icon - Item's icon for displaying on GUI
     */
    @NotNull
    public Item createCustomItem(ItemType type, String name, Stats change, char icon, Cell position) {
        switch (type) {
            case DAGGER:
                return new Dagger(name, change, icon, position);
            case SHIELD:
                return new Shield(name, change, icon, position);
            default:
                return new Dagger(name, change, icon, position);
        }
    }
}
