package org.example.visual;

import javax.swing.*;
import java.awt.*;

public class SuccessFrame extends JFrame{
    public static final String SUCCESS_MESSAGE = "Операция выполнена успешно";
    public SuccessFrame() {
        JPanel mainPanel= new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;
        constr.fill = GridBagConstraints.HORIZONTAL;

        constr.gridx = 0;
        constr.gridy = 0;
        JLabel successLabel = new JLabel(SUCCESS_MESSAGE);
        constr.anchor = GridBagConstraints.CENTER;
        mainPanel.add(successLabel, constr);

        constr.gridx = 0;
        constr.gridy = 1;
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e ->
            (SwingUtilities.getWindowAncestor(okButton)).dispose()
        );
        constr.anchor = GridBagConstraints.CENTER;
        mainPanel.add(okButton,constr);

        getContentPane().add(mainPanel);
        setSize(400, 150);
        setVisible(true);
    }
}
