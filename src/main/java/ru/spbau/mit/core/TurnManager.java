package ru.spbau.mit.core;

import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.mobs.Mob;
import ru.spbau.mit.utils.Cell;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

public final class TurnManager {
    // TODO : This is a field for mob AI
    private static final int CHASE_RADIUS = 7;
    private TurnManager() {}

    private static void handleFight(GameState mGameState) throws IOException {
        System.out.println("Handling fight");
        Player player = mGameState.getPlayer();
        Mob opponent = mGameState.getAttackingMob().get();

        player.fight(opponent);

        if (player.isDead()) {
            mGameState.setGameOver(true);
        }

        if (opponent.isDead()) {
            System.out.println("Mob is dead!");
            mGameState.killMob(opponent);
        }
    }

    public static void handlePlayersTurn(GameState mGameState) throws IOException {
        mGameState.setPlayersTurn(true);

        while (mGameState.isPlayersTurn()) {
            InputHandler.handleInput().doAction(mGameState);
            Player player = mGameState.getPlayer();

            if (mGameState.isFightSituation()) {
                handleFight(mGameState);
            }

            Item maybeItem = mGameState.getCurrentMap().removeItemOnPosition(player.getCurrentPosition());
            if (maybeItem != null) {
                player.pickUp(maybeItem);
            }
        }
    }

    private static boolean isInTargetsRadius(Cell object, Cell target) {
        return Math.abs(object.getRow() - target.getRow()) < CHASE_RADIUS
                && Math.abs(object.getColumn() - target.getColumn()) < CHASE_RADIUS;
    }

    private static Cell moveRandom(Mob mob, GameState gameState) throws IOException {
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
    private static Cell moveToTarget(Mob mob, GameState gameState, Cell target) throws IOException {
        Cell position = mob.getCurrentPosition();
        Cell newPosition = position;
        int rowDist = position.getRow() - target.getRow();
        int columnDist = position.getColumn() - target.getColumn();

        if (Math.abs(rowDist) < Math.abs(columnDist)) {
            if (rowDist < 0) {
                newPosition = mob.maybeMove(Direction.RIGHT);
            } else if (rowDist > 0) {
                newPosition = mob.maybeMove(Direction.LEFT);
            }

            if (!gameState.getCurrentMap().isCellFree(newPosition) || newPosition.equals(position)) {
                if (columnDist < 0) {
                    newPosition = mob.maybeMove(Direction.DOWN);
                } else {
                    newPosition = mob.maybeMove(Direction.UP);
                }
            }
        } else {
            if (columnDist < 0) {
                newPosition = mob.maybeMove(Direction.DOWN);
            } else {
                newPosition = mob.maybeMove(Direction.UP);
            }
            if (!gameState.getCurrentMap().isCellFree(newPosition) || newPosition.equals(position)) {
                if (rowDist < 0) {
                    newPosition = mob.maybeMove(Direction.RIGHT);
                } else if (rowDist > 0) {
                    newPosition = mob.maybeMove(Direction.LEFT);
                }
            }
        }


        return newPosition;
    }

    // TODO : Don't know where to put this random walk

    private static void moveMobs(GameState gameState) throws IOException {
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

    public static void handleAIsTurn(GameState gameState) throws IOException {
        moveMobs(gameState);
        gameState.setPlayersTurn(true);
    }
}
