package ru.spbau.mit.core;

import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.GUI.TerminalGUI;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsFactory;
import ru.spbau.mit.characters.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class GameState {
    private final static int NUMBER_OF_MOBS = 25;

    private final Player player = new Player();
    private Map currentMap = new Map(TerminalGUI.getMaxRow(), TerminalGUI.getMaxColumn());
    private Set<Mob> mobs = new HashSet<>();
    private boolean playersTurn = false;
    private boolean gameOver = false;


    public GameState() {
        generateMobs();
    }

    private void generateMobs() {
        for (int i = 0; i < NUMBER_OF_MOBS; i++) {
            Mob mob = MobsFactory.createRandomMob();
            Cell position = currentMap.getFreeRandomPosition();
            mob.setCurrentPosition(position);
            currentMap.occupyCell(mob.getCurrentPosition());
            mobs.add(mob);
        }

        mobs.forEach(Drawable::draw);
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

    public Set<Mob> getMobs() {
        return mobs;
    }

    public void removeMob(Mob toBeKilled) {
        mobs.remove(toBeKilled);
        currentMap.freeCell(toBeKilled.getCurrentPosition());
    }
}
