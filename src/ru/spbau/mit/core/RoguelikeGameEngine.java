package ru.spbau.mit.core;

import java.io.IOException;

public class RoguelikeGameEngine {
    private GameState mGameState;

    public void start() throws IOException{
        initialize();
        while (!mGameState.isGameOver()) {
            makeTurns();
        }
    }

    private void initialize() throws IOException {
        GUI.createTerminal();
        mGameState = new GameState();
    }

    public void makeTurns() throws IOException{
        TurnManager.handlePlayersTurn(mGameState);
        TurnManager.handleAIsTurn(mGameState);
    }
}
