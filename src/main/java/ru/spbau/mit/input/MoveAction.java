package ru.spbau.mit.input;

import ru.spbau.mit.core.GameState;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.utils.Cell;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;

public class MoveAction implements KeyboardAction {
    private static int stepsLeft = GameState.MAX_TURN_STEPS;
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
        stepsLeft = GameState.MAX_TURN_STEPS;
    }

    @Override
    public void doAction(GameState gameState) throws IOException {
        Player player = gameState.getPlayer();
        this.gameState = gameState;
        Cell newPosition = player.maybeMove(direction);

        if (isStepAllowed(newPosition)) {
//            gameState.getCurrentMap().replace(player.getCurrentPosition(), newPosition);
            player.redrawTo(newPosition);
            stepsLeft--;
        }

        if (stepsLeft <= 0) {
            refreshSteps();
            gameState.setPlayersTurn(false);
        }
    }
}
