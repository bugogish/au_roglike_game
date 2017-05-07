package ru.spbau.mit.core;


import com.googlecode.lanterna.input.KeyStroke;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.events.*;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;

public class InputHandler {
    private InputHandler() {}

    @NotNull
    public static Action handleInput() throws IOException {
        KeyStroke key = TerminalGUI.readInput();

        if (key != null) {
            System.out.println(key);

            switch (key.getKeyType()) {
                case Escape: {
                    // TODO : THERE SHOULD BE END GAME SCREEN OR SMTH
                    return gameState -> gameState.setGameOver(true);
                }

                case Character: {
                    char pressedChar = key.getCharacter();
                    if (pressedChar == ' ') {
                        return gameState -> gameState.setPlayersTurn(false);
                    } else if (pressedChar == 'i') {
                        return new OpenInventoryAction();
                    }
                }

                case ArrowDown: {
                        return new MoveAction(Direction.DOWN);
                }
                case ArrowLeft: {
                        return new MoveAction(Direction.LEFT);
                }
                case ArrowRight: {
                        return new MoveAction(Direction.RIGHT);
                }
                case ArrowUp: {
                        return new MoveAction(Direction.UP);
                }

                case EOF: {
                    return gameState -> gameState.setGameOver(true);
                }

                default: {
                    return gameState -> {};
                }
            }
        }

        return gameState -> {};
    }

}
