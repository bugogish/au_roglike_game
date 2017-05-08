package ru.spbau.mit.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsAI;
import ru.spbau.mit.input.InputHandler;
import ru.spbau.mit.items.Item;

public final class GameplayManager {
    private static final Logger logger = LogManager.getLogger(GameplayManager.class);
    private final GameState gameState;
    private MobsAI mobsAI;

    public GameplayManager(GameState gameState) {
        mobsAI = new MobsAI(gameState);
        this.gameState = gameState;
    }

    void handlePlayersTurn() {
        gameState.setPlayersTurn(true);

        while (gameState.isPlayersTurn()) {
            InputHandler.handleInput().doAction(gameState);
            handleGameResponse();
        }
    }

    void handleAIsTurn() {
        mobsAI.moveMobs();
        handleGameResponse();
        gameState.setPlayersTurn(true);
    }

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
    }
}
