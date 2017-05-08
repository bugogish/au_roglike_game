package ru.spbau.mit.input;


import com.googlecode.lanterna.input.KeyStroke;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.GUI.TerminalGUI;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;

public final class InputHandler {
    private InputHandler() {}

    @NotNull
    public static KeyboardAction handleInput() throws IOException {
        KeyStroke key = TerminalGUI.readInput();

        if (key != null) {
            System.out.println(key);

            switch (key.getKeyType()) {
                case Escape:
                    // TODO : THERE SHOULD BE END GAME SCREEN OR SMTH
                    return GameState::setGameOver;

                case Character:
                    char pressedChar = key.getCharacter();
                    if (pressedChar == ' ') {
//                        return gameState -> gameState.setPlayersTurn(false);
                    } else if (pressedChar == 'i') {
                        return new OpenInventoryAction();
                    }

                case ArrowDown:
                        return new MoveAction(Direction.DOWN);
                case ArrowLeft:
                        return new MoveAction(Direction.LEFT);
                case ArrowRight:
                        return new MoveAction(Direction.RIGHT);
                case ArrowUp:
                        return new MoveAction(Direction.UP);

                case EOF:
                    return GameState::setGameOver;

                default:
                    return gameState -> {};
            }
        }

        return gameState -> {};
    }

}
