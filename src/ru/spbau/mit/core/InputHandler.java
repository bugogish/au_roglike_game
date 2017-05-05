package ru.spbau.mit.core;


import com.googlecode.lanterna.input.KeyStroke;
import ru.spbau.mit.events.*;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;

public class InputHandler {
    private static boolean inventoryMode = false;

    public static Action handleInput() throws IOException {
        KeyStroke key = GUI.readInput();

        if (key != null) {
            System.out.println(key);

            switch (key.getKeyType()) {
                case Escape: {
                    // TODO : THERE SHOULD BE END GAME SCREEN OR SMTH
                    System.exit(0);
                    // TODO : REPLACE BY BELOW v
                    return gameState -> gameState.setGameOver(true);
                }

                case Character: {
                    char pressedChar = key.getCharacter();
                    if (pressedChar == ' ') {
                        return gameState -> gameState.setPlayersTurn(false);
                    } else if (pressedChar == 'i') {
                        if (inventoryMode) {
                            inventoryMode = false;
                            return new CloseInventoryAction();
                        } else {
                            inventoryMode = true;
                            return new OpenInventoryAction();
                        }
                    }
                }

                case ArrowDown: {
                    if (!inventoryMode) {
                        return new MoveAction(Direction.DOWN);
                    }
                }
                case ArrowLeft: {
                    if (!inventoryMode) {
                        return new MoveAction(Direction.LEFT);
                    }
                }
                case ArrowRight: {
                    if (!inventoryMode) {
                        return new MoveAction(Direction.RIGHT);
                    }
                }
                case ArrowUp: {
                    if (!inventoryMode) {
                        return new MoveAction(Direction.UP);
                    }
                }
                default: {
                    return null;
                }
            }
        }

        return null;
    }

}
