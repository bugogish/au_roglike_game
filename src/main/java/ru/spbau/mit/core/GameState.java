package ru.spbau.mit.core;

import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.GUI.TerminalGUI;
import ru.spbau.mit.characters.Mob;
import ru.spbau.mit.characters.MobsFactory;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.utils.Cell;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class GameState {
    // GAME SETTINGS (Maybe initialize a Settings class or function to set parameters for different difficulty levels)
    public final static int MAX_TURN_STEPS = 1;
    public final static int NUMBER_OF_MOBS = 10;

    private final Player player = new Player();
    private Map currentMap = new Map(TerminalGUI.getMaxRow(), TerminalGUI.getMaxColumn());
    private Set<Mob> mobs = new HashSet<>();
    private boolean playersTurn = false;
    private boolean gameOver = false;


    public GameState() {
//        currentMap.occupyCell(player.getCurrentPosition());
        player.draw();
        generateMobs();
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

    public void removeMob(Mob toBeKilled) {
        mobs.remove(toBeKilled);
        currentMap.freeCell(toBeKilled.getCurrentPosition());
    }
}
