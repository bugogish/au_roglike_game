package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.utils.Drawable;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Map {
    private Set<Drawable> obstacles = new HashSet<>();
    private HashMap<TerminalPosition, Item> items = new HashMap<>();
//    private int obstaclesNum = 20;
    private char obstacleSymbol = '▣';

    public void generate() throws IOException {
        Random rand = new Random();

        while (obstacles.size() < GameState.numberOfObstacles) {
            TerminalPosition obsPos = new TerminalPosition(rand.nextInt(GUI.getMaxColumn() - 1) + 1,
                    rand.nextInt(GUI.getMaxRow() - 1) + 1);
            obstacles.add(new Drawable(obstacleSymbol, obsPos) {});
        }

        obstacles.forEach(Drawable::draw);

        while (items.size() < GameState.numberOfItems) {
            Item item = new Item("SWORD", "SWORDEST SWORD", new Stats());
            items.put(item.getCurrentPosition(), item);
        }

        items.values().forEach(Drawable::draw);
    }

    public boolean intersectsWithObstacle(TerminalPosition position) {
        return obstacles.contains(new Drawable(obstacleSymbol, position) {});
    }

    public boolean intersectsWithItem(TerminalPosition position) {
        return items.get(position) != null;
    }

    public Item getItemOnPosition(TerminalPosition position) {
        return items.get(position);
    }
}
