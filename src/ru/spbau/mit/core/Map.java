package ru.spbau.mit.core;

import ru.spbau.mit.core.GUI.Drawable;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.items.ItemFactory;
import ru.spbau.mit.core.items.ItemType;
import ru.spbau.mit.utils.Cell;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Map {
    private Set<Drawable> obstacles = new HashSet<>();
    //    private int obstaclesNum = 20;
    private static final char obstacleSymbol = '▣';
    private HashMap<Cell, Item> items = new HashMap<>();
    private boolean [][] cells = new boolean[TerminalGUI.getMaxRow()][TerminalGUI.getMaxColumn()];

    public Map() {
        for (int i = 0; i < TerminalGUI.getMaxRow(); i++) {
            for (int j = 0; j < TerminalGUI.getMaxColumn(); j++) {
                cells[i][j] = true;
            }
        }

        generate();
    }

    private void generate() {
        Random rand = new Random();

        while (obstacles.size() < GameState.numberOfObstacles) {
            Cell position = getFreeRandomPosition();
            obstacles.add(new Drawable(obstacleSymbol, position) {});
            occupyCell(position);
        }

        obstacles.forEach(Drawable::draw);

        while (items.size() < GameState.numberOfItems) {
            ItemType randomType = ItemType.values()[rand.nextInt(ItemType.values().length)];
            Item item = ItemFactory.createDefaultItem(randomType);
            Cell position = getFreeRandomPosition();

            item.setCurrentPosition(position);
            occupyCell(position);
            items.put(position, item);
        }

        items.values().forEach(Drawable::draw);
    }

    public Cell getFreeRandomPosition() {
        Random rand = new Random();
        Cell position = new Cell(rand.nextInt(TerminalGUI.getMaxColumn() - 1) + 1,
                rand.nextInt(TerminalGUI.getMaxRow() - 1) + 1);
        while(!isCellFree(position)) {
            position = new Cell(rand.nextInt(TerminalGUI.getMaxColumn() - 1) + 1,
                    rand.nextInt(TerminalGUI.getMaxRow() - 1) + 1);
        }

        return position;
    }

    public void reDrawContents() {
        obstacles.forEach(Drawable::draw);
        items.values().forEach(Drawable::draw);
    }

    public boolean intersectsWithObstacle(Cell position) {
        return obstacles.stream().anyMatch(obs -> obs.getCurrentPosition().equals(position));
    }

    public boolean intersectsWithItem(Cell position) {
        return items.get(position) != null;
    }

    public Item removeItemOnPosition(Cell position) {
        Item item = items.get(position);
        items.remove(position);
        return item;
    }

    public boolean isCellFree(Cell position) {
        return cells[position.getRow()][position.getColumn()];
    }

    // TODO : this being public looks ugly
    public void occupyCell(Cell position) {
        if (!cells[position.getRow()][position.getColumn()]) {
            System.out.println("Cell has been already occupied");
//            throw new Exception("Trying to occupy an occupied cell");
//            throw new SOMEGAMEEXCEPTION;
        }

        cells[position.getRow()][position.getColumn()] = false;
    }

    // TODO : NEEDS REFACTORING !!
    public void freeCell(Cell position) {
        cells[position.getRow()][position.getColumn()] = true;
    }

    public void replace(Cell from, Cell to) {
        freeCell(from);
        occupyCell(to);
    }
}
