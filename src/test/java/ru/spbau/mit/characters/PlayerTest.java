package ru.spbau.mit.characters;

import org.junit.Test;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.items.Dagger;
import ru.spbau.mit.items.Heal;
import ru.spbau.mit.items.Item;
import ru.spbau.mit.items.Shield;

import static org.junit.Assert.*;


public class PlayerTest {
    @Test
    public void sumplePickUpTest() {
        Player player = new Player();
        Item item = new Dagger(new Cell(0, 0));
        player.pickUp(item);
        assert(player.getInventory().getItems().contains(item));
    }

    private void testItemUnequip(Item item) {
        Player player = new Player();
        Stats itemChange = item.getStats();
        player.equipItem(item);
        Stats statsBefore = new Stats(player.getStats());
        player.unEquipItem(item);
        Stats statsAfter = player.getStats();

        assertEquals(-itemChange.getHealth(),
                statsAfter.getHealth()  - statsBefore.getHealth());
        assertEquals(-itemChange.getStamina(),
                statsAfter.getStamina()  - statsBefore.getStamina());
        assertEquals(-itemChange.getArmor(),
                statsAfter.getArmor()  - statsBefore.getArmor());
        assertFalse(item.isEquipped());
    }

    @Test
    public void simpleUnEquipTest() throws Exception {
        testItemUnequip(new Dagger(new Cell(0, 0)));
        testItemUnequip(new Shield(new Cell(0, 0)));
    }

    private void testItemEquip(Item item) {
        Player player = new Player();
        Stats itemChange = item.getStats();
        Stats statsBefore = new Stats(player.getStats());
        player.equipItem(item);
        Stats statsAfter = player.getStats();

        assertEquals(itemChange.getHealth(),
                statsAfter.getHealth()  - statsBefore.getHealth());
        assertEquals(itemChange.getStamina(),
                statsAfter.getStamina()  - statsBefore.getStamina());
        assertEquals(itemChange.getArmor(),
                statsAfter.getArmor()  - statsBefore.getArmor());
        assertTrue(item.isEquipped());
    }

    @Test
    public void simpleEquipTest() throws Exception {
        testItemEquip(new Dagger(new Cell(0, 0)));
        testItemEquip(new Shield(new Cell(0, 0)));
        testItemEquip(new Heal(new Cell(0, 0)));
    }

    @Test
    public void twiceEquipShouldntDoubleStats() throws Exception {
        Player player = new Player();
        Item item = new Dagger(new Cell(0,0));
        player.equipItem(item);
        Stats before = new Stats(player.getStats());
        player.equipItem(item);
        Stats after = player.getStats();
        assertEquals(before, after);
    }
}