package ru.spbau.mit.core;

import ru.spbau.mit.GUI.TerminalGUI;


public class RoguelikeGameEngine {
    private GameState gameState;
    private GameplayManager gameplayManager;

    public void start() {
        initialize();
        while (!gameState.isGameOver()) {
            makeTurns();
        }
        TerminalGUI.terminate();
    }

    private void initialize() {
        TerminalGUI.initialize();
        TerminalGUI.showMessageDialog("Manual", "Move character - arrows\nOpen inventory - i\nSkip turn - Spacebar");
        gameState = new GameState();
        gameplayManager = new GameplayManager(gameState);
    }

    private void makeTurns() {
        gameplayManager.handlePlayersTurn();
        gameplayManager.handleAIsTurn();
    }
}
