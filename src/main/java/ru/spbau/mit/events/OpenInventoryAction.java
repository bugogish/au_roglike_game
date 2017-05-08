package ru.spbau.mit.events;

import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import ru.spbau.mit.core.GUI.Drawable;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Inventory;
import ru.spbau.mit.core.items.Item;

import java.io.IOException;

public class OpenInventoryAction implements Action{
    private GameState gameState;

    @Override
    public void doAction(GameState gameState) throws IOException {
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
                        () -> gameState.getPlayer().equipItem(item)
                );
            }
        }

        ab.build().showDialog(TerminalGUI.createNewScreen());
    }
}
