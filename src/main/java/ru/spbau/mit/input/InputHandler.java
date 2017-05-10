package ru.spbau.mit.input;


import com.googlecode.lanterna.input.KeyStroke;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.GUI.GUI;
import ru.spbau.mit.core.Direction;
import ru.spbau.mit.core.GameState;

/**
 * Class that processes user's input into game actions
 */
public final class InputHandler {
    private final GUI myGUI;

    public InputHandler(GUI myGUI) {
        this.myGUI = myGUI;
    }

    /**
     * @return an action for user specified key, default does nothing
     */
    @NotNull
    public KeyboardAction handleInput() {
        KeyStroke key = myGUI.readInput();

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
                    return new InventoryAction(myGUI);
                }

            case ArrowDown:
                    return new MoveAction(myGUI, Direction.DOWN);
            case ArrowLeft:
                    return new MoveAction(myGUI, Direction.LEFT);
            case ArrowRight:
                    return new MoveAction(myGUI, Direction.RIGHT);
            case ArrowUp:
                    return new MoveAction(myGUI, Direction.UP);

            case EOF:
                System.exit(0);

            default:
                return gameState -> {};
        }
    }
}
