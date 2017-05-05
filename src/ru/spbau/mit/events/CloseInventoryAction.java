package ru.spbau.mit.events;

import ru.spbau.mit.core.GameState;

import java.io.IOException;


public class CloseInventoryAction implements Action {
    @Override
    public void doAction(GameState gameState) throws IOException {
        gameState.getPlayer().getmInventory().clear();
    }
}
