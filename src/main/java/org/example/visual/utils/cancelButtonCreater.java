package org.example.visual.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cancelButtonCreater {
    public JButton createCancelButton(){
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor(cancelButton);
                window.dispose();
            }
        });
        return cancelButton;
    }
}
