package ru.spbau.mit.core;

import org.junit.Test;
import ru.spbau.mit.characters.Player;
import ru.spbau.mit.characters.mobs.Mob;

import java.util.Set;

import static org.junit.Assert.*;

public class GameStateTest {
    @Test
    public void isFightSituation() {
        GameState state = new GameState(10, 10);
        Set<Mob> mobs = state.getMobs();
        Player player = state.getPlayer();

        for (Mob mob : mobs) {
            player.moveTo(mob.getCurrentPosition());
            assertTrue(state.isFightSituation());
            assertEquals(mob, state.getAttackingMob().get());
        }
    }

    @Test
    public void removeMob() {
        GameState state = new GameState(10, 10);
        Set<Mob> mobs = state.getMobs();
        int size = mobs.size();
        Map map = state.getCurrentMap();
        Mob mob = mobs.iterator().next();
        state.removeMob(mob);

        assertEquals(size - 1, mobs.size());
        assertFalse(mobs.contains(mob));
        assertTrue(map.isCellFree(mob.getCurrentPosition()));
    }
}