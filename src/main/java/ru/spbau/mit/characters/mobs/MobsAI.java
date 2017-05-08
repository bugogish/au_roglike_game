package ru.spbau.mit.characters.mobs;

import ru.spbau.mit.core.GameState;
import ru.spbau.mit.core.Map;
import ru.spbau.mit.utils.Cell;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class MobsAI {
    private static final int CHASE_RADIUS = 7;
    private Map currentMap;

    public MobsAI(Map map) {
        currentMap = map;
    }

    private boolean isInTargetsRadius(Cell object, Cell target) {
        return Math.abs(object.getRow() - target.getRow()) < CHASE_RADIUS
                && Math.abs(object.getColumn() - target.getColumn()) < CHASE_RADIUS;
    }

    private Cell moveRandom(Mob mob, GameState gameState) throws IOException {
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
    private Cell moveToTarget(Mob mob, GameState gameState, Cell target) throws IOException {
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

    public void moveMobs(GameState gameState) throws IOException {
        Cell target = gameState.getPlayer().getCurrentPosition();
        Set<Mob> mobs = gameState.getMobs();
        Map map = gameState.getCurrentMap();

        for (Mob mob : mobs) {
            Cell position;
            if (isInTargetsRadius(mob.getCurrentPosition(), target)) {
                // TODO : Mob still can eat an obstacle (fix it)
                position = moveToTarget(mob, gameState, target);
            } else {
                position = moveRandom(mob, gameState);
            }

            map.replace(mob.getCurrentPosition(), position);
            mob.redrawTo(position);
        }
    }
}
