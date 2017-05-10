package ru.spbau.mit.input;

import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import ru.spbau.mit.GUI.GUI;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.characters.Inventory;
import ru.spbau.mit.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that opens Inventory screen if 'i' character was pressed
 */
public class InventoryAction implements KeyboardAction {
    private GameState gameState;
    private final GUI myGUI;

    InventoryAction(GUI myGUI) {
        this.myGUI = myGUI;
    }

    @Override
    public void doAction(GameState gameState) {
        this.gameState = gameState;
        openInventoryView();
        restoreScreen();
    }

    /**
     * restores screen back to game after inventory is closed
     */
    private void restoreScreen() {
        myGUI.showOnScreen(gameState.getPlayer());
        myGUI.showOnScreen(gameState.getMobs());
        myGUI.showOnScreen(gameState.getCurrentMap().getContents());
    }

    /**
     * opens a screen with inventory view
     */
    private void openInventoryView() {
        Map<String, Runnable> actions = new HashMap<>();

        if (gameState.getPlayer().getInventory().isEmpty()) {
            actions.put("<Empty>", () -> {});
        } else {
            for (Item item : gameState.getPlayer().getInventory().getItems()) {
                String menuOption = String.valueOf(item.getIcon()).concat(item.isEquipped() ? "  [Equipped]" : "");
                actions.put(menuOption, () -> {
                    if (!item.isEquipped()) {
                        gameState.getPlayer().equipItem(item);
                    } else {
                        gameState.getPlayer().unEquipItem(item);
                    }
                });
            }
        }
        myGUI.openInventory(Inventory.TITLE, actions);
    }
}
