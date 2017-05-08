package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Map;
import ru.spbau.mit.utils.Cell;
import ru.spbau.mit.utils.Direction;

import java.util.Random;
import java.util.Set;

public class MobsAI {
    private static final int CHASE_RADIUS = 7;
    private GameState gameState;

    public MobsAI(GameState gameState) {
        this.gameState = gameState;
    }

    private boolean isInTargetsRadius(Cell object, Cell target) {
        return Math.abs(object.getRow() - target.getRow()) < CHASE_RADIUS
                && Math.abs(object.getColumn() - target.getColumn()) < CHASE_RADIUS;
    }

    private Cell moveRandom(Mob mob) {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        Cell position = mob.maybeMove(direction);

        while (!gameState.getCurrentMap().isCellFree(position)) {
            direction = Direction.values()[random.nextInt(Direction.values().length)];
            position = mob.maybeMove(direction);
        }

        return position;
    }

    // TODO : THIS NEEDS TO BE REDONE
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

        if (gameState.getCurrentMap().isCellFree(newPosition)){
            return newPosition;
        }
        else {
            return position;
        }
    }

    public void moveMobs() {
        Cell target = gameState.getPlayer().getCurrentPosition();
        Set<Mob> mobs = gameState.getMobs();
        Map map = gameState.getCurrentMap();

        for (Mob mob : mobs) {
            Cell position;
            if (isInTargetsRadius(mob.getCurrentPosition(), target)) {
                // TODO : Mob still can eat an obstacle (fix it)
                position = moveToTarget(mob, target);
            } else {
                position = moveRandom(mob);
            }

            map.replace(mob.getCurrentPosition(), position);
            mob.redrawTo(position);
        }
    }
}
