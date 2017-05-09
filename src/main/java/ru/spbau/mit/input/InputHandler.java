package ru.spbau.mit.input;


import com.googlecode.lanterna.input.KeyStroke;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.GUI.TerminalGUI;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Direction;

public final class InputHandler {
    private InputHandler() {}

    @NotNull
    public static KeyboardAction handleInput() {
        KeyStroke key = TerminalGUI.readInput();

        if (key == null) {
            return gameState -> {};
        }

        switch (key.getKeyType()) {
            case Escape:
                return GameState::setGameOver;

            case Character:
                char pressed = key.getCharacter();
                if (pressed == ' ') {
                    return gameState -> gameState.setPlayersTurn(false);
                } else if (pressed == 'i') {
                    return new InventoryAction();
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
                System.exit(0);

            default:
                return gameState -> {};
        }
    }
}
