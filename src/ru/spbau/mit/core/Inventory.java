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

public class Inventory extends Drawable {
    private static final String TITLE = "Inventory";
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory() {
        super('i', new TerminalPosition(0, 0));
    }

    public void add(Item item) {
        items.add(item);
    }

    @Override
    public void draw() {
//        Table<String> table = new Table<>("Items");
//        for (Item item : items) {
//            table.getTableModel().addRow(item.getItemName());
//        }
//
//        table.setSelectAction(() -> {
//            List<String> data = table.getTableModel().getRow(table.getSelectedRow());
//            for (String aData : data) {
//                System.out.println(aData);
//            }
//        });
//        try {
//            table.draw(TerminalGUI.createNewScreen());
//        } catch (IOException e) {
//
//        }

        try {
            ActionListDialogBuilder ab = new ActionListDialogBuilder().setTitle(TITLE);

            if (items.isEmpty()) {
                ab.addAction("<Empty>", () -> {});
            } else {
                for (Item item : items) {
                    ab.addAction(item.getItemName(), () -> {
                        // TODO : here needs to be equip ation
                    });
                }
            }

            ab.build().showDialog(TerminalGUI.createNewScreen());
        } catch (IOException e) {
            // TODO : Exception Handling!
            e.printStackTrace();
        }
    }

    @Override
    public void clear() throws IOException {

    }

    @Override
    public void redrawTo(TerminalPosition position) throws IOException {
        super.redrawTo(position);
    }
}
