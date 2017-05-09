package ru.spbau.mit.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.GUI.TerminalGUI;

/**
 * Main class for handling the game
 */
public class RoguelikeGameEngine {
    private static final Logger logger = LogManager.getLogger(RoguelikeGameEngine.class);
    private static final String MANUAL_TITLE = "Manual";
    private static final String MANUAL_TEXT = "Move character - arrows\nOpen inventory - i\nSkip turn - Spacebar";

    private GameState gameState;
    private GameplayManager gameplayManager;


    /**
     * main game loop
     */
    public void start() {
        initialize();
        while (!gameState.isGameOver()) {
            makeTurns();
        }
        endGame();
    }

    /**
     * initializes game's GUI and state and shows Welcome Screen
     */
    private void initialize() {
        TerminalGUI.initialize();
        TerminalGUI.showMessageDialog(MANUAL_TITLE, MANUAL_TEXT);
        gameState = new GameState();
        gameplayManager = new GameplayManager(gameState);
    }

    /**
     * makes one player' and one AI's turn
     */
    private void makeTurns() {
        try {
            gameplayManager.handlePlayersTurn();
            gameplayManager.handleAIsTurn();
        } catch (Exception e) {
            logger.fatal(e.getMessage());
            throw new RuntimeException();
        }

    }

    /**
     * shows Game Over screen and terminates GUI
     */
    private void endGame() {
        TerminalGUI.showMessageDialog("", "Game Over");
        TerminalGUI.terminate();
    }
}
