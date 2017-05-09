package ru.spbau.mit.input;

import ru.spbau.mit.core.GameState;

/**
 * A class for a user keyboard action that changes gamestate
 */
public interface KeyboardAction {
    void doAction(GameState gameState);
}
