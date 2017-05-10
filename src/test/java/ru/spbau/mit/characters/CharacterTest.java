package ru.spbau.mit.characters;

import org.junit.Test;
import ru.spbau.mit.characters.mobs.Boss;
import ru.spbau.mit.characters.mobs.Mob;
import ru.spbau.mit.characters.mobs.MobsFactory;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.Direction;
import ru.spbau.mit.core.GameState;

import javax.print.attribute.standard.Fidelity;

import static org.junit.Assert.*;


public class CharacterTest {
    @Test
    public void fight() {
        Player player = new Player();
        Mob mob = MobsFactory.createRandomMob();
        int playersHealthBefore = player.getStats().getHealth();
        int mobsHealthBefore = mob.getStats().getHealth();
        player.fight(mob);
        assertEquals(playersHealthBefore - mob.getFightPower(), player.getStats().getHealth());
        assertEquals(mobsHealthBefore - player.getFightPower(), mob.getStats().getHealth());
    }

    @Test
    public void isDead() {
        Player player = new Player();
        player.getStats().decreaseHealth(player.getStats().getHealth());
        assertTrue(player.isDead());
    }

}