package ru.spbau.mit.core;

import ru.spbau.mit.core.GUI.TerminalGUI;

import java.io.IOException;

public class RoguelikeGameEngine {
    private GameState mGameState;

    public void start() throws IOException {
        initialize();
        while (!mGameState.isGameOver()) {
            makeTurns();
        }
        TerminalGUI.terminate();
    }

    private void initialize() throws IOException {
        TerminalGUI.initialize();
        mGameState = new GameState();
    }

    public void makeTurns() throws IOException {
        TurnManager.handlePlayersTurn(mGameState);
        TurnManager.handleAIsTurn(mGameState);
    }
}
