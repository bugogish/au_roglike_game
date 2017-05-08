package ru.spbau.mit.core;

import ru.spbau.mit.GUI.TerminalGUI;

import java.io.IOException;

public class RoguelikeGameEngine {
    private GameState gameState;
    private GameplayManager gameplayManager;

    public void start() throws IOException {
        initialize();
        while (!gameState.isGameOver()) {
            makeTurns();
        }
        TerminalGUI.terminate();
    }

    private void initialize() throws IOException {
        TerminalGUI.initialize();
        gameState = new GameState();
        gameplayManager = new GameplayManager(gameState);
    }

    private void makeTurns() throws IOException {
        gameplayManager.handlePlayersTurn(gameState);
        gameplayManager.handleAIsTurn(gameState);
    }
}
