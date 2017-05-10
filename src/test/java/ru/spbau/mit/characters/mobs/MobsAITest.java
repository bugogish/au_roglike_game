package ru.spbau.mit.characters.mobs;

import org.junit.Test;
import ru.spbau.mit.core.Cell;
import ru.spbau.mit.core.GameState;

import static org.junit.Assert.*;

public class MobsAITest {
    @Test
    public void moveMobsRandomly() {
        GameState gameState = new GameState(1000, 1000);
        MobsAI mobsAI = new MobsAI(gameState);
        Mob mob = gameState.getMobs().iterator().next();
        Cell mobInitialPosition = mob.getCurrentPosition();
        mobsAI.moveMobs();
        Cell mobNextPosition = mob.getCurrentPosition();
        assertNotEquals(mobInitialPosition, mobNextPosition);
    }

    @Test
    public void moveMobsToTarget() {
        GameState gameState = new GameState(1000, 1000);
        MobsAI mobsAI = new MobsAI(gameState);
        Mob mob = gameState.getMobs().iterator().next();
        mob.moveTo(new Cell(0, 2));
        mobsAI.moveMobs();
        Cell mobPosition = mob.getCurrentPosition();
        assertEquals(new Cell(0, 1), mobPosition);
    }
}