package org.example.model;

import java.util.ArrayList;

public interface MyTableModel {
    public int getTableSize ();
    public ArrayList<String> getTitles();
    public Object[] toObject();
}
