package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.mobs.Mob;
import ru.spbau.mit.utils.Direction;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class TurnManager {
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
            opponent.clear();
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


    // TODO : Don't know where to put this random walk

    private static void moveMobs(GameState gameState) throws IOException {
        Random random = new Random();
        Set<Mob> mobs = gameState.getMobs();
        Map map = gameState.getCurrentMap();

        for (Mob mob : mobs) {
            Direction direction = Direction.values()[random.nextInt(4)];
            TerminalPosition newPosition = mob.maybeMove(direction);

            while (!map.isCellFree(newPosition)) {
                direction = Direction.values()[random.nextInt(4)];
                newPosition = mob.maybeMove(direction);
            }

            map.replace(mob.getCurrentPosition(), newPosition);
            mob.redrawTo(newPosition);
        }
    }

    public static void handleAIsTurn(GameState gameState) throws IOException {
        moveMobs(gameState);
        gameState.setPlayersTurn(true);
    }
}
