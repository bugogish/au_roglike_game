package ru.spbau.mit.input;

import ru.spbau.mit.characters.Player;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.Direction;

/**
 * Class that handles user's movement if one of the arrows was pressed
 */
public class MoveAction implements KeyboardAction {
    private static final int MAX_TURN_STEPS = 2;
    private static int stepsLeft = MAX_TURN_STEPS;

    private Direction direction;
    private GameState gameState;

    MoveAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void doAction(GameState gameState) {
        this.gameState = gameState;
        Player player = gameState.getPlayer();
        Cell newPosition = gameState.getCurrentMap().getValidPosition(player.maybeMove(direction));

        if (isStepAllowed(newPosition)) {
            player.redrawTo(newPosition);
            stepsLeft--;
        }

        if (stepsLeft <= 0) {
            refreshSteps();
            gameState.setPlayersTurn(false);
        }
    }

    /**
     * checks if new position of player is valid (i.e. doesn't intersect with obstacles
     * or player doesnt' try to move if it's AI's turn to move)
     */
    private boolean isStepAllowed(Cell newPosition) {
        return stepsLeft > 0
                && !gameState.getCurrentMap().intersectsWithObstacle(newPosition)
                && !newPosition.equals(gameState.getPlayer().getCurrentPosition());
    }

    /**
     * resets the number of steps left for user to make this turn back to maximum allowed
     */
    private void refreshSteps() {
        stepsLeft = MAX_TURN_STEPS;
    }
}
