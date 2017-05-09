package ru.spbau.mit.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsAI;
import ru.spbau.mit.input.InputHandler;
import ru.spbau.mit.items.Item;

/**
 * Class that manages Player's turn and AI's turn
 */

public final class GameplayManager {
    private static final Logger logger = LogManager.getLogger(GameplayManager.class);
    private final GameState gameState;
    private MobsAI mobsAI;

    public GameplayManager(GameState gameState) {
        this.gameState = gameState;
        mobsAI = new MobsAI(gameState);
    }

    /**
     * processes player's actions and game response on them
     */
    void handlePlayersTurn() {
        gameState.setPlayersTurn(true);

        while (gameState.isPlayersTurn()) {
            InputHandler.handleInput().doAction(gameState);
            handleGameResponse();
        }
    }

    /**
     * processes mobs' actions and game response on them
     */
    void handleAIsTurn() {
        mobsAI.moveMobs();
        handleGameResponse();
        gameState.setPlayersTurn(true);
    }

    /**
     * checks if characters' actions resulted in fight or possibility for picking an item
     * and handles them if so
     */
    private void handleGameResponse() {
        Player player = gameState.getPlayer();

        if (gameState.isFightSituation()) {
            handleFight();
        }

        Item maybeItem = gameState.getCurrentMap().getItemByPosition(player.getCurrentPosition());
        if (maybeItem != null) {
            player.pickUp(maybeItem);
            gameState.getCurrentMap().removeItem(maybeItem);
        }
    }

    /**
     * processes fight situation
     */
    private void handleFight() {
        Player player = gameState.getPlayer();
        Mob opponent = gameState.getAttackingMob().get();

        player.fight(opponent);

        if (player.isDead()) {
            logger.info("Player was killed");
            gameState.setGameOver();
        }

        if (opponent.isDead()) {
            logger.info("Mob is dead");
            gameState.removeMob(opponent);
        }

        logger.info("Players health after fight {}", player.getStats().getHealth());
    }
}
