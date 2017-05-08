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

    public void handlePlayersTurn(GameState mGameState) throws IOException {
        mGameState.setPlayersTurn(true);

        while (mGameState.isPlayersTurn()) {
            InputHandler.handleInput().doAction(mGameState);
            handleGameResponse(mGameState);
        }
    }

    public void handleAIsTurn(GameState gameState) throws IOException {
        mobsAI.moveMobs(gameState);
        handleGameResponse(gameState);
        gameState.setPlayersTurn(true);
    }

    private void handleGameResponse(GameState mGameState) throws IOException {
        Player player = mGameState.getPlayer();

        if (mGameState.isFightSituation()) {
            handleFight(mGameState);
        }

        Item maybeItem = mGameState.getCurrentMap().removeItemByPosition(player.getCurrentPosition());
        if (maybeItem != null) {
            player.pickUp(maybeItem);
        }
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
}
