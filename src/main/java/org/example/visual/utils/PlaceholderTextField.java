package org.example.visual.utils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private final String placeholder;

    public PlaceholderTextField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(placeholder)) {
            this.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            this.setText(placeholder);
        }
    }
}
