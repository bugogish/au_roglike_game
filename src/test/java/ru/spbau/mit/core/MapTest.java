package ru.spbau.mit.core;

import org.junit.Test;
import ru.spbau.mit.GUI.Drawable;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.ItemFactory;
import ru.spbau.mit.items.ItemType;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MapTest {
    private final static int MAP_SIZE_X = 10;
    private final static int MAP_SIZE_Y = 10;

    @Test
    public void intersectsWithObstacle() {
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        map.putObstacleOn(new Cell(0, 0));
        map.putObstacleOn(new Cell(1, 1));
        assertTrue(map.intersectsWithObstacle(new Cell(0,0)));
        assertTrue(map.intersectsWithObstacle(new Cell(1, 1)));
        assertFalse(map.intersectsWithObstacle(new Cell(2, 3)));
    }

    @Test
    public void getItemByPosition() {
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        Item item = ItemFactory.createDefaultItem(ItemType.DAGGER, new Cell(1, 9));
        map.putItemOnMap(item);
        assertFalse(map.isCellFree(item.getCurrentPosition()));
        assertEquals(item, map.getItemByPosition(new Cell(1, 9)));
        assertEquals(null, map.getItemByPosition(new Cell(0, 0)));
    }

    @Test
    public void removeItem() {
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        Item item = ItemFactory.createDefaultItem(ItemType.DAGGER, new Cell(0, 0));
        map.putItemOnMap(item);
        assertEquals(1, ((HashSet)map.getContents()).size());
        map.removeItem(item);
        assertTrue(map.isCellFree(item.getCurrentPosition()));
        assertEquals(0, ((HashSet)map.getContents()).size());
    }

    @Test
    public void getValidPosition() {
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        assertEquals(new Cell(MAP_SIZE_X - 1, MAP_SIZE_Y -1),
                map.getValidPosition(new Cell(MAP_SIZE_X, MAP_SIZE_Y)));
    }

    @Test
    public void putItemTest() {
        Cell position = new Cell(0, 0);
        Set<Drawable> expectedContents = new HashSet<>();
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        Item item = ItemFactory.createDefaultItem(ItemType.DAGGER, position);

        assertTrue(map.isCellFree(position));
        assertEquals(expectedContents, map.getContents());

        map.putItemOnMap(item);
        expectedContents.add(item);

        assertFalse(map.isCellFree(position));
        assertEquals(expectedContents, map.getContents());
    }

    @Test
    public void twicePutOnTheSameCellTest() {
        Cell position = new Cell(0, 0);
        Set<Drawable> expectedContents = new HashSet<>();
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        Item item = ItemFactory.createDefaultItem(ItemType.DAGGER, position);

        assertTrue(map.isCellFree(position));
        assertEquals(expectedContents, map.getContents());

        map.putItemOnMap(item);
        expectedContents.add(item);

        assertFalse(map.isCellFree(position));
        assertEquals(expectedContents, map.getContents());

        map.putItemOnMap(item);
        assertEquals(expectedContents, map.getContents());
    }

    @Test
    public void putObstacleTest() {
        Cell position = new Cell(9, 9);
        Map map = new Map(MAP_SIZE_X, MAP_SIZE_Y);

        assertTrue(map.isCellFree(position));

        map.putObstacleOn(position);

        assertFalse(map.isCellFree(position));
    }
}