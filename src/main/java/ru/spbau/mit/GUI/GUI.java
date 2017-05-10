package ru.spbau.mit.GUI;

import com.googlecode.lanterna.input.KeyStroke;

import java.util.Map;

public interface GUI {
    void showInfoScreen(String title, String text);

    void openInventory(String screenTitle, Map<String, Runnable> menuActions);

    void terminate();

    int getMaxRow();

    int getMaxColumn();

    void showOnScreen(Drawable item);

    void showOnScreen(Iterable<? extends Drawable> items);

    void removeFromScreen(Drawable item);

    void removeFromScreen(Iterable<? extends Drawable> items);

    KeyStroke readInput();
}
