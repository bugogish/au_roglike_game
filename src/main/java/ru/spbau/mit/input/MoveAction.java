package ru.spbau.mit.input;

import ru.spbau.mit.characters.Player;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.utils.Cell;
import ru.spbau.mit.utils.Direction;

public class MoveAction implements KeyboardAction {
    private final static int MAX_TURN_STEPS = 1;
    private static int stepsLeft = MAX_TURN_STEPS;

    private Direction direction;
    private GameState gameState;

    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    private boolean isStepAllowed(Cell newPosition) {
        return stepsLeft > 0
                && !gameState.getCurrentMap().intersectsWithObstacle(newPosition)
                && !newPosition.equals(gameState.getPlayer().getCurrentPosition());
    }

    private void refreshSteps() {
        stepsLeft = MAX_TURN_STEPS;
    }

    @Override
    public void doAction(GameState gameState) {
        this.gameState = gameState;
        Player player = gameState.getPlayer();
        Cell newPosition = player.maybeMove(direction);

        if (isStepAllowed(newPosition)) {
            player.redrawTo(newPosition);
            stepsLeft--;
        }

        if (stepsLeft <= 0) {
            refreshSteps();
            gameState.setPlayersTurn(false);
        }
    }
}
