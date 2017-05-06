package ru.spbau.mit.events;

import ru.spbau.mit.core.GUI.Drawable;
import ru.spbau.mit.core.GameState;

import java.io.IOException;

public class OpenInventoryAction implements Action{
    @Override
    public void doAction(GameState gameState) throws IOException {
        gameState.getPlayer().getmInventory().draw();
        restoreScreen(gameState);
    }

    private void restoreScreen(GameState gameState) {
        gameState.getPlayer().draw();
        gameState.getMobs().forEach(Drawable::draw);
        gameState.getCurrentMap().reDrawContents();
    }
}
