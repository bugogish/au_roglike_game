package ru.spbau.mit;

import ru.spbau.mit.core.RoguelikeGameEngine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RoguelikeGameEngine game = new RoguelikeGameEngine();
        game.start();
    }
}
