package ru.spbau.mit.events;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Player;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;

public class MoveAction implements Action {
    private static int stepsLeft = GameState.maxTurnSteps;
    private Direction direction;
    private GameState gameState;

    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    private boolean isStepAllowed(TerminalPosition newPosition) {
        return stepsLeft > 0 &&
                !gameState.getCurrentMap().intersectsWithObstacle(newPosition) &&
                !newPosition.equals(gameState.getPlayer().getCurrentPosition());
    }

    private void refreshSteps() {
        stepsLeft = GameState.maxTurnSteps;
    }

    @Override
    public void doAction(GameState gameState) throws IOException {
        Player player = gameState.getPlayer();
        this.gameState = gameState;
        TerminalPosition newPosition = player.maybeMove(direction);

        if (isStepAllowed(newPosition)) {
            gameState.getCurrentMap().replace(player.getCurrentPosition(), newPosition);
            player.redrawTo(newPosition);
            stepsLeft--;
        }

        if (stepsLeft <= 0) {
            refreshSteps();
            gameState.setPlayersTurn(false);
        }
    }

}
