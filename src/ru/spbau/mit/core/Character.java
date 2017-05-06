package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.utils.Direction;
import ru.spbau.mit.utils.Drawable;

import java.io.IOException;

abstract class Character extends Drawable {
    private Stats currentStats;

    public Character(char itemIcon) {
        super(itemIcon, new TerminalPosition(0,0));
    }

    public TerminalPosition maybeMove(Direction direction) throws IOException {
        TerminalPosition newPosition = getCurrentPosition();

        switch (direction) {
            case LEFT: {
                if (newPosition.getColumn() > 0) {
                    newPosition = newPosition.withColumn(newPosition.getColumn() - 1);
                }
                break;
            }

            case RIGHT: {
                if (newPosition.getColumn() <= GUI.getMaxColumn()) {
                    newPosition = newPosition.withColumn(newPosition.getColumn() + 1);
                }
                break;
            }

            case UP: {
                if (newPosition.getRow() > 0) {
                    newPosition = newPosition.withRow(newPosition.getRow() - 1);
                }
                break;
            }

            case DOWN: {
                if (newPosition.getRow() <= GUI.getMaxRow()) {
                    newPosition = newPosition.withRow(newPosition.getRow() + 1);
                }
                break;
            }
        }

        return newPosition;
    }

    public boolean isDead() {
        return currentStats.getHealth() == 0;
    }

    public Stats getStats() {
        return currentStats;
    }

    public abstract int getFightPower();

    public void fight(Character another) {
        currentStats.decreaseHealth(another.getFightPower());
        another.getStats().decreaseHealth(getFightPower());
    }

}