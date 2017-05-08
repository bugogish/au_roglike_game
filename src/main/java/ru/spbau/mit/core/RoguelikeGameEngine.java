package ru.spbau.mit.core;

import ru.spbau.mit.GUI.TerminalGUI;

import java.io.IOException;

public class RoguelikeGameEngine {
    private GameState mGameState;
    private GamePlayManager gamePlayManager;

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
        gamePlayManager = new GamePlayManager(mGameState);
    }

    private void makeTurns() throws IOException {
        gamePlayManager.handlePlayersTurn(mGameState);
        gamePlayManager.handleAIsTurn(mGameState);
    }
}
