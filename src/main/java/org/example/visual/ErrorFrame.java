package org.example.visual;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends JFrame{
    public static ErrorFrame errorFrame(String message){
            ErrorFrame erFR = new ErrorFrame();
            JPanel mainPanel= new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5, 5, 5, 5);
            constr.anchor = GridBagConstraints.WEST;
            constr.fill = GridBagConstraints.HORIZONTAL;

            constr.gridx = 0;
            constr.gridy = 0;
            JLabel successLabel = new JLabel(message);
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

            erFR.getContentPane().add(mainPanel);
            erFR.setSize(400, 150);
            erFR.setVisible(true);

            Timer timer = new Timer(5000, e -> {
                if (erFR.isVisible()) {
                    erFR.dispose();
                }
            });
            timer.setRepeats(false); // Остановить таймер после первого срабатывания
            timer.start();
        return erFR;
    }
}
