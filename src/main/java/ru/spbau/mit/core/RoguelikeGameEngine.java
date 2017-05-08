package ru.spbau.mit.core;

import ru.spbau.mit.GUI.TerminalGUI;


public class RoguelikeGameEngine {
    private static final String MANUAL_TITLE = "Manual";
    private static final String MANUAL_TEXT = "Move character - arrows\nOpen inventory - i\nSkip turn - Spacebar";

    private GameState gameState;
    private GameplayManager gameplayManager;


    public void start() {
        initialize();
        while (!gameState.isGameOver()) {
            makeTurns();
        }
        endGame();
    }

    private void initialize() {
        TerminalGUI.initialize();
        TerminalGUI.showMessageDialog(MANUAL_TITLE, MANUAL_TEXT);
        gameState = new GameState();
        gameplayManager = new GameplayManager(gameState);
    }

    private void makeTurns() {
        gameplayManager.handlePlayersTurn();
        gameplayManager.handleAIsTurn();
    }

    private void endGame() {
        TerminalGUI.showMessageDialog("", "Game Over");
        TerminalGUI.terminate();
    }
}
