package ru.spbau.mit.core;

import org.jetbrains.annotations.Nullable;
import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.ItemFactory;
import ru.spbau.mit.items.ItemType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Class to control game's map.
 */

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
    }

    /**
     * generates new random map contents with @code{NUMBER_OF_OBSTACLES} obstacles
     * and @code{NUMBER_OF_ITEMS} items.
     */

    public void generate() {
        Random rand = new Random();

        while (obstacles.size() < NUMBER_OF_OBSTACLES) {
            putObstacleOn(getFreeRandomPosition());
        }

        while (items.size() < NUMBER_OF_ITEMS) {
            ItemType randomType = ItemType.values()[rand.nextInt(ItemType.values().length)];
            Cell position = getFreeRandomPosition();
            Item item = ItemFactory.createDefaultItem(randomType, position);
            putItemOnMap(item);
        }
    }

    /**
     * @param position - position to place new obstacle on, if the cell has already been occupied
     *                 does nothing
     */
    void putObstacleOn(Cell position) {
        if (isCellFree(position)) {
            obstacles.add(new Drawable(OBSTACLE_SYMBOL, position) {});
            occupyCell(position);
        }
    }

    /**
     * generates default item of random type and puts it on specified position if the cell was free
     * otherwise does nothing
     */
     void putItemOnMap(Item item) {
        if (isCellFree(item.getCurrentPosition())) {
            items.add(item);
            occupyCell(item.getCurrentPosition());
        }
    }

    /**
     * @return not occupied Cell at random position
     */

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

    /**
     * @return map displayable contents (used to restore state if
     * another screen was opened and then closed)
     */
    public Iterable<Drawable> getContents() {
        Set<Drawable> contents = new HashSet<>();
        contents.addAll(obstacles);
        contents.addAll(items);
        return contents;
    }

    /**
     * checks position for intersection with obstacle
     */
    public boolean intersectsWithObstacle(Cell position) {
        return obstacles.stream().anyMatch(obs -> obs.getCurrentPosition().equals(position));
    }

    /**
     * @return item on the specified cell if present
     */

    @Nullable
    public Item getItemByPosition(Cell position) {
        Optional<Item> item = items.stream().filter(i -> i.getCurrentPosition().equals(position)).findAny();
        return item.orElse(null);
    }

    /**
     * removes item from map
     */
    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            freeCell(item.getCurrentPosition());
        }
    }

    /**
     * checks if the given Cell is empty (doesn't contain obstacles or items)
     */
    public boolean isCellFree(Cell position) {
        return cells[position.getRow()][position.getColumn()];
    }

    /**
     * marks given Cell as occupied
     */
    void occupyCell(Cell position) {
        cells[position.getRow()][position.getColumn()] = false;
    }

    /**
     * marks given Cell as free
     */
    void freeCell(Cell position) {
        cells[position.getRow()][position.getColumn()] = true;
    }

    /**
     * @param from - Cell to mark as free
     * @param to - Cell to mark as occupied
     */
    public void replace(Cell from, Cell to) {
        freeCell(from);
        occupyCell(to);
    }

    /**
     * Disallows row's coordinate outside of the map
     * @return row coordinate if it's inside the map and closest row coordinate
     * on border otherwise
     */
    private int getValidRow(int row) {
        return Math.min(Math.max(0, row), maxRow - 1);
    }

    /**
     * Disallows column's coordinate outside of the map
     * @return column coordinate if it's inside the map and closest column coordinate
     * on border otherwise
     */
    private int getValidColumn(int col) {
        return Math.min(Math.max(0, col), maxColumn - 1);
    }

    /**
     * returns a valid position by using getValidRow and
     * getValidColumn from given position
     */
    public Cell getValidPosition(Cell position) {
        return new Cell(getValidColumn(position.getColumn()), getValidRow(position.getRow()));
    }
}
