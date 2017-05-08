package ru.spbau.mit.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsAI;
import ru.spbau.mit.input.InputHandler;
import ru.spbau.mit.items.Item;

import java.io.IOException;

public final class GamePlayManager {
    private static final Logger logger = LogManager.getLogger(GamePlayManager.class);
    private MobsAI mobsAI;

    public GamePlayManager(GameState gameState) {
        mobsAI = new MobsAI(gameState.getCurrentMap());
    }

    private void handleFight(GameState mGameState) throws IOException {
        logger.info("Handling fight");
        Player player = mGameState.getPlayer();
        Mob opponent = mGameState.getAttackingMob().get();

        player.fight(opponent);

        if (player.isDead()) {
            logger.info("Player was killed");
            mGameState.setGameOver();
        }

        if (opponent.isDead()) {
            logger.info("Mob is dead");
            mGameState.removeMob(opponent);
        }
    }

    public void handlePlayersTurn(GameState mGameState) throws IOException {
        mGameState.setPlayersTurn(true);

        while (mGameState.isPlayersTurn()) {
            InputHandler.handleInput().doAction(mGameState);

            Player player = mGameState.getPlayer();

            if (mGameState.isFightSituation()) {
                handleFight(mGameState);
            }

            Item maybeItem = mGameState.getCurrentMap().removeItemByPosition(player.getCurrentPosition());
            if (maybeItem != null) {
                player.pickUp(maybeItem);
            }
        }
    }

    public void handleAIsTurn(GameState gameState) throws IOException {
        mobsAI.moveMobs(gameState);
        gameState.setPlayersTurn(true);
    }
}
