package ru.spbau.mit.characters;

import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.Direction;

/**
 * Class for an abstract game character (player or NPC)
 */
public abstract class Character extends Drawable {
    private Stats currentStats;

    public Character(char itemIcon, Stats currentStats) {
        super(itemIcon, new Cell(0, 0));
        this.currentStats = currentStats;
    }

    /**
     * @param direction - direction to move Character to
     * @return - new Cell for a position in specified direction
     * if movement is possible (i.e. Character doesn't try to go
     * outside of the map) and current position otherwise
     */
    public Cell maybeMove(Direction direction) {
        Cell newPosition = getCurrentPosition();

        switch (direction) {
            case LEFT:
                newPosition = new Cell(newPosition.getColumn() - 1, newPosition.getRow());
                break;

            case RIGHT:
                newPosition = new Cell(newPosition.getColumn() + 1, newPosition.getRow());
                break;

            case UP:
                newPosition = new Cell(newPosition.getColumn(), newPosition.getRow() - 1);
                break;

            case DOWN:
                newPosition = new Cell(newPosition.getColumn(), newPosition.getRow() + 1);
                break;
        }

        return newPosition;
    }

    /**
     * @return true if Character's health is below zero
     */
    public boolean isDead() {
        return currentStats.getHealth() <= 0;
    }

    public Stats getStats() {
        return currentStats;
    }

    /**
     * @return a number to subtract from another @code{Character} health when fight occurs
     */
    public abstract int getFightPower();

    /**
     * handles fight of this Character with another by subtracting both health by fightPower
     * @param another - opponent of this Character
     */
    public void fight(Character another) {
        int damageToThis = Math.round(another.getFightPower() * (1 - currentStats.getArmor() / 100));
        int damageToAnother = getFightPower() * (1 - another.getStats().getArmor() / 100);

        currentStats.decreaseHealth(damageToThis);
        another.getStats().decreaseHealth(damageToAnother);
    }
}
