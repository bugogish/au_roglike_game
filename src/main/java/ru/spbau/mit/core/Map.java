package ru.spbau.mit.core;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.ItemFactory;
import ru.spbau.mit.items.ItemType;
import ru.spbau.mit.utils.Cell;

import java.util.*;

public class Map {
    private static final char OBSTACLE_SYMBOL = '▣';
    private static final int NUMBER_OF_ITEMS = 7;
    private static final int NUMBER_OF_OBSTACLES = 20;

    private final int maxRow;
    private final int maxColumn;

    private Set<Drawable> obstacles = new HashSet<>();
    private Set<Item> items = new HashSet<>();
    private boolean[][] cells;

    public Map(int maxRow, int maxColumn) {
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;

        cells = new boolean[maxRow][maxColumn];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                cells[i][j] = true;
            }
        }

        generate();
    }

    private void generate() {
        Random rand = new Random();

        while (obstacles.size() < NUMBER_OF_OBSTACLES) {
            Cell position = getFreeRandomPosition();
            obstacles.add(new Drawable(OBSTACLE_SYMBOL, position) {});
            occupyCell(position);
        }

        obstacles.forEach(Drawable::draw);

        while (items.size() < NUMBER_OF_ITEMS) {
            ItemType randomType = ItemType.values()[rand.nextInt(ItemType.values().length)];
            Item item = ItemFactory.createDefaultItem(randomType);
            Cell position = getFreeRandomPosition();

            item.setCurrentPosition(position);
            occupyCell(position);
            items.add(item);
        }

        items.forEach(Drawable::draw);
    }

    public Cell getFreeRandomPosition() {
        Random rand = new Random();
        Cell position = new Cell(rand.nextInt(maxColumn - 1),
                rand.nextInt(maxRow - 1));
        while (!isCellFree(position)) {
            position = new Cell(rand.nextInt(maxColumn - 1),
                    rand.nextInt(maxRow - 1));
        }

        return position;
    }

    // TODO : ???
    public void redrawContents() {
        obstacles.forEach(Drawable::draw);
        items.forEach(Drawable::draw);
    }

    public boolean intersectsWithObstacle(Cell position) {
        return obstacles.stream().anyMatch(obs -> obs.getCurrentPosition().equals(position));
    }

    public boolean intersectsWithItem(Cell position) {
        return items.stream().anyMatch(obs -> obs.getCurrentPosition().equals(position));
    }

    @Nullable
    public Item removeItemByPosition(Cell position) {
        Optional<Item> item = items.stream().filter(i -> i.getCurrentPosition().equals(position)).findAny();
        if (item.isPresent()) {
            items.remove(item.get());
            return item.get();
        }
        return null;
    }

    public boolean isCellFree(Cell position) {
        return cells[position.getRow()][position.getColumn()];
    }

    public void occupyCell(Cell position) {
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
