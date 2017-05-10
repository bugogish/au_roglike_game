package ru.spbau.mit.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.GUI.GUI;
import ru.spbau.mit.GUI.TerminalGUI;

/**
 * Main class for handling the game
 */
public class RoguelikeGameEngine {
    private static final Logger logger = LogManager.getLogger(RoguelikeGameEngine.class);
    private static final String MANUAL_TITLE = "Manual";
    private static final String MANUAL_TEXT = "Move character - arrows\nOpen inventory - i\nSkip turn - Spacebar";
    private static final String GAME_OVER_TEXT = "Game Over";

    private final GUI myGUI = new TerminalGUI();
    private GameState gameState;
    private GameplayManager gameplayManager;


    /**
     * main game loop
     */
    public void play() {
        startGame();
        while (!gameState.isGameOver()) {
            makeTurns();
        }
        endGame();
    }

    /**
     * initializes game's state and shows Welcome Screen
     */
    private void startGame() {
        myGUI.showInfoScreen(MANUAL_TITLE, MANUAL_TEXT);
        gameState = new GameState(myGUI.getMaxRow(), myGUI.getMaxColumn());
        gameplayManager = new GameplayManager(gameState, myGUI);
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
        myGUI.showInfoScreen("", GAME_OVER_TEXT);
        myGUI.terminate();
    }
}
