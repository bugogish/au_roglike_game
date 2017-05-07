package ru.spbau.mit.events;

import ru.spbau.mit.core.GameState;

import java.io.IOException;

public interface Action {
    void doAction(GameState gameState) throws IOException;
}
