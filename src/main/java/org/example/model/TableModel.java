package org.example.model;

import java.util.ArrayList;

public interface TableModel {
    public int getTableSize ();
    public ArrayList<String> getTitles();
    public Object[] toObject();
}
