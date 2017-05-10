package ru.spbau.mit.GUI;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.input.KeyStroke;

public interface GUI {
    void showInfoScreen(String title, String text);

    void terminate();

    int getMaxRow();

    int getMaxColumn();

    void showOnScreen(Drawable item);

    void showOnScreen(Iterable<? extends Drawable> items);

    void removeFromScreen(Drawable item);

    void removeFromScreen(Iterable<? extends Drawable> items);

    KeyStroke readInput();

    WindowBasedTextGUI openNewScreen();
}
