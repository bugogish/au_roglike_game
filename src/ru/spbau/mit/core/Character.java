package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.utils.Direction;
import ru.spbau.mit.core.GUI.Drawable;

import java.io.IOException;

abstract class Character extends Drawable {
    private Stats currentStats;

    public Character(char itemIcon) {
        super(itemIcon, new TerminalPosition(0,0));
    }

    // TODO : Check maybe move because mobs random walks generate OutOfBounds in Map's IsCellFree

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
                if (newPosition.getColumn() <= TerminalGUI.getMaxColumn()) {
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
                if (newPosition.getRow() <= TerminalGUI.getMaxRow()) {
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
