package ru.spbau.mit.core;

import ru.spbau.mit.characters.Player;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Class that stores and handles main game state objects : Player, Map and Mobs
 */
public class GameState {
    private static final int NUMBER_OF_MOBS = 25;

    private final Player player = new Player();
    private Map currentMap;
    private Set<Mob> mobs = new HashSet<>();
    private boolean playersTurn = false;
    private boolean gameOver = false;


    public GameState(int maxMapRow, int maxMapColumn) {
        currentMap = new Map(maxMapRow, maxMapColumn);
        currentMap.generate();
        generateMobs();
    }

    /**
     * generates @code{NUMBER_OF_MOBS} mobs at random positions
     */
    private void generateMobs() {
        for (int i = 0; i < NUMBER_OF_MOBS; i++) {
            Mob mob = MobsFactory.createRandomMob();
            Cell position = currentMap.getFreeRandomPosition();
            mob.moveTo(position);
            currentMap.occupyCell(mob.getCurrentPosition());
            mobs.add(mob);
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
    }

    /**
     * @return true if player and mob share the same Cell
     */
    public boolean isFightSituation() {
        return getAttackingMob().isPresent();
    }

    /**
     * @return Optional that holds Mob that shares the same Cell as Player, null otherwise
     */
    public Optional<Mob> getAttackingMob() {
        return mobs.stream().filter(mob -> mob.getCurrentPosition().equals(player.getCurrentPosition())).findAny();
    }

    public Player getPlayer() {
        return player;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public Set<Mob> getMobs() {
        return mobs;
    }

    /**
     * removes Mob from a game
     */
    public void removeMob(Mob toBeKilled) {
        mobs.remove(toBeKilled);
        currentMap.freeCell(toBeKilled.getCurrentPosition());
    }
}
