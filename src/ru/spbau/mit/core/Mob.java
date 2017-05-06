package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;
import java.util.Random;

public class Mob extends Character {
    public Mob() throws IOException {
        super('T');
        Random rand = new Random();
        TerminalPosition startPos = new TerminalPosition(rand.nextInt(GUI.getMaxColumn() - 1) + 1,
                rand.nextInt(GUI.getMaxRow() - 1) + 1);
        setCurrentPosition(startPos);
    }

    @Override
    public int getFightPower() {
        return 0;
    }
}