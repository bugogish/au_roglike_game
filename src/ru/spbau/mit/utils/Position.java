package ru.spbau.mit.utils;

import com.googlecode.lanterna.TerminalPosition;

//public class Position implements TerminalPosition {
//    private int row = 0;
//    private int col = 0;
//
//    public Position(int col, int row) {
//        this.col = col;
//        this.row = row;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (!Position.class.isAssignableFrom(obj.getClass())) {
//            return false;
//        }
//        final Position other = (Position) obj;
//        return this.col == other.col && this.row == other.row;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = row;
//        result = 31 * result + col;
//        return result;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public void setRow(int row) {
//        this.row = row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//
//    public void setCol(int col) {
//        this.col = col;
//    }
//}
