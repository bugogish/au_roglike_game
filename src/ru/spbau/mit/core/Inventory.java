package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.terminal.Terminal;
import ru.spbau.mit.core.GUI.TerminalGUI;
import ru.spbau.mit.core.items.Item;
import ru.spbau.mit.core.GUI.Drawable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public static final String TITLE = "Inventory";
    private ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
