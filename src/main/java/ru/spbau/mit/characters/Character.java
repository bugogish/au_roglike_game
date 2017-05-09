package ru.spbau.mit.characters;

import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.Direction;

public abstract class Character extends Drawable {
    private Stats currentStats;

    public Character(char itemIcon, Stats currentStats) {
        super(itemIcon, new Cell(0, 0));
        this.currentStats = currentStats;
    }

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

    public boolean isDead() {
        return currentStats.getHealth() <= 0;
    }

    public Stats getStats() {
        return currentStats;
    }

    public abstract int getFightPower();

    public void fight(Character another) {
        int damageToThis = Math.round(another.getFightPower() * (1 - currentStats.getArmor() / 100));
        int damageToAnother = getFightPower() * (1 - another.getStats().getArmor() / 100);

        currentStats.decreaseHealth(damageToThis);
        another.getStats().decreaseHealth(damageToAnother);
    }
}
