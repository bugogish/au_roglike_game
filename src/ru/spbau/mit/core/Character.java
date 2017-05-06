package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.utils.Direction;
import ru.spbau.mit.core.GUI.Drawable;

import java.io.IOException;

public abstract class Character extends Drawable {
    private Stats currentStats;

    public Character(char itemIcon, Stats currentStats) {
        super(itemIcon, new TerminalPosition(0,0));
        this.currentStats = currentStats;
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
                if (newPosition.getColumn() < TerminalGUI.getMaxColumn() - 1) {
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
                if (newPosition.getRow() < TerminalGUI.getMaxRow() - 1) {
                    newPosition = newPosition.withRow(newPosition.getRow() + 1);
                }
                break;
            }
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

        System.out.println(String.format("Players health %d", currentStats.getHealth()));
        System.out.println(String.format("Mobs health %d", another.currentStats.getHealth()));
    }
}
