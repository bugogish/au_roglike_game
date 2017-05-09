package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Map;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.Direction;

import java.util.Random;
import java.util.Set;

/**
 * Class that handles mob's moving strategy. Mobs chase the player if they're in
 * @code{CHASE_RADIUS} from him, otherwise they move random.
 */

public class MobsAI {
    private static final int CHASE_RADIUS = 7;
    private GameState gameState;

    public MobsAI(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * @param object - Mob's position
     * @param target - target's position
     * @return - true if mob's position is in @code{CHASE_RADIUS} distance from target
     */
    private boolean isInTargetsRadius(Cell object, Cell target) {
        return Math.abs(object.getRow() - target.getRow()) < CHASE_RADIUS
                && Math.abs(object.getColumn() - target.getColumn()) < CHASE_RADIUS;
    }

    /**
     * handles Mob's random movement
     * @return - new Cell to move mob to
     */
    private Cell moveRandom(Mob mob) {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        Cell position = gameState.getCurrentMap().getValidPosition(mob.maybeMove(direction));

        while (!gameState.getCurrentMap().isCellFree(position)) {
            direction = Direction.values()[random.nextInt(Direction.values().length)];
            position = gameState.getCurrentMap().getValidPosition(mob.maybeMove(direction));
        }

        return position;
    }

    /**
     * handles chasing player strategy
     * @param target - position of mob's chasing target
     * @return - new Cell to move mob to
     */
    private Cell moveToTarget(Mob mob, Cell target) {
        Cell position = mob.getCurrentPosition();
        Cell newPosition = position;
        int rowDist = position.getRow() - target.getRow();
        int columnDist = position.getColumn() - target.getColumn();

        if (Math.abs(rowDist) > Math.abs(columnDist)) {
            if (rowDist < 0) {
                newPosition = mob.maybeMove(Direction.DOWN);
            }
            if (rowDist > 0) {
                newPosition = mob.maybeMove(Direction.UP);
            }
        } else {
            if (columnDist < 0) {
                newPosition = mob.maybeMove(Direction.RIGHT);
            }
            if (columnDist > 0) {
                newPosition = mob.maybeMove(Direction.LEFT);
            }
        }
        newPosition = gameState.getCurrentMap().getValidPosition(newPosition);

        if (gameState.getCurrentMap().isCellFree(newPosition)){
            return newPosition;
        }
        else {
            return position;
        }
    }

    /**
     * move all current mobs according to their distance to player
     */
    public void moveMobs() {
        Cell target = gameState.getPlayer().getCurrentPosition();
        Set<Mob> mobs = gameState.getMobs();
        Map map = gameState.getCurrentMap();

        for (Mob mob : mobs) {
            Cell position;
            if (isInTargetsRadius(mob.getCurrentPosition(), target)) {
                position = moveToTarget(mob, target);
            } else {
                position = moveRandom(mob);
            }

            map.replace(mob.getCurrentPosition(), position);
            mob.redrawTo(position);
        }
    }
}
