package ru.spbau.mit.input;

import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.GUI.TerminalGUI;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Inventory;
import ru.spbau.mit.items.Item;

public class InventoryAction implements KeyboardAction {
    private GameState gameState;

    @Override
    public void doAction(GameState gameState) {
        this.gameState = gameState;
        openInventoryView();
        restoreScreen();
    }

    private void restoreScreen() {
        gameState.getPlayer().draw();
        gameState.getMobs().forEach(Drawable::draw);
        gameState.getCurrentMap().redrawContents();
    }

    private void openInventoryView() {
        ActionListDialogBuilder ab = new ActionListDialogBuilder().setTitle(Inventory.TITLE);

        if (gameState.getPlayer().getInventory().isEmpty()) {
            ab.addAction("<Empty>", () -> {});
        } else {
            for (Item item : gameState.getPlayer().getInventory().getItems()) {
                ab.addAction(
                        String.valueOf(item.getIcon()).concat(item.isEquipped() ? "  [Equipped]" : ""),
                        () -> {
                            if (!item.isEquipped()) gameState.getPlayer().equipItem(item);
                            else {gameState.getPlayer().unEquipItem(item);}
                        }
                );
            }
        }

        ab.build().showDialog(TerminalGUI.openNewScreen());
    }
}
