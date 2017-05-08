package ru.spbau.mit.input;

import ru.spbau.mit.core.GameState;

import java.io.IOException;

public interface KeyboardAction {
    void doAction(GameState gameState);
}
