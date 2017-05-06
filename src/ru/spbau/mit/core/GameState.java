package ru.spbau.mit.core;
import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI.Drawable;
import ru.spbau.mit.core.mobs.Mob;
import ru.spbau.mit.core.mobs.MobsFactory;

import java.io.IOException;
import java.util.*;

// TODO : weird that I can instantiate as many game states as I want (maybe it should be a singleton after all)

public class GameState {
    private final Player player = new Player();
    private Map currentMap = new Map();
    private Set<Mob> mobs = new HashSet<>();
    private boolean playersTurn = false;
    private boolean gameOver = false;


    // GAME SETTINGS (Maybe initialize a Settings class or function to set parameters for different difficulty levels)
    public final static int maxTurnSteps = 5;
    public final static int numberOfObstacles = 20;
    public final static int numberOfMobs = 10;
    public final static int numberOfItems = 7;


    public GameState() throws IOException {
        currentMap.occupyCell(player.getCurrentPosition());
        player.draw();
        generateMobs();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
    }

    private void generateMobs() throws IOException {
        for (int i = 0; i < numberOfMobs; i++) {
            Mob mob = MobsFactory.createRandomMob();
            TerminalPosition position = currentMap.getFreeRandomPosition();
            mob.setCurrentPosition(position);
            currentMap.occupyCell(mob.getCurrentPosition());
            mobs.add(mob);
        }

        mobs.forEach(Drawable::draw);
    }

    public boolean isFightSituation() {
        return getAttackingMob().isPresent();
    }

    public Optional<Mob> getAttackingMob() {
        return mobs.stream().filter(mob -> mob.getCurrentPosition().equals(player.getCurrentPosition())).findAny();
    }

    public Player getPlayer() {
        return player;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    // TODO : this seems ugly
    public Set<Mob> getMobs() {
        return mobs;
    }

    public void killMob(Mob toBeKilled) {
        System.out.println("Mobs size " + mobs.size());
        mobs.remove(toBeKilled);
        System.out.println("Mobs size " + mobs.size());
    }
}
