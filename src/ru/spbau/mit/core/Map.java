package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.GUI.Drawable;
import ru.spbau.mit.core.items.ItemFactory;
import ru.spbau.mit.core.items.ItemType;

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
            // TODO : transform this into condition when obstacle cant be generated at player's position
            TerminalPosition obsPos = new TerminalPosition(rand.nextInt(TerminalGUI.getMaxColumn() - 1) + 1,
                    rand.nextInt(TerminalGUI.getMaxRow() - 1) + 1);
            obstacles.add(new Drawable(obstacleSymbol, obsPos) {});
        }

        obstacles.forEach(Drawable::draw);

        while (items.size() < GameState.numberOfItems) {
            ItemType randomType = ItemType.values()[rand.nextInt(ItemType.values().length)];
            Item item = ItemFactory.createDefaultItem(randomType);
            TerminalPosition itemPos = new TerminalPosition(
                    rand.nextInt(TerminalGUI.getMaxColumn() - 1) + 1,
                    rand.nextInt(TerminalGUI.getMaxRow() - 1) + 1);

            while (intersectsWithObstacle(itemPos)) {
                itemPos = new TerminalPosition(
                        rand.nextInt(TerminalGUI.getMaxColumn() - 1) + 1,
                        rand.nextInt(TerminalGUI.getMaxRow() - 1) + 1);
            }

            item.setCurrentPosition(itemPos);
            items.put(itemPos, item);
        }
        items.values().forEach(Drawable::draw);
    }

    public boolean intersectsWithObstacle(TerminalPosition position) {
        return obstacles.stream().anyMatch(obs -> obs.getCurrentPosition().equals(position));
    }

    public boolean intersectsWithItem(TerminalPosition position) {
        return items.get(position) != null;
    }

    public Item getItemOnPosition(TerminalPosition position) {
        return items.get(position);
    }
}
