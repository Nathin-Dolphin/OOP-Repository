
/**
 * @author Nathin Wascher
 * @version 0.1 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;

public class PokedexWriter_Panels extends JPanel {
    private JFrame spareFrame;
    private JButton previousEntry, enterButton;
    private JTextField textField1, textField2;
    private JLabel label1, label2, warningLabel;

    private int min, max, evolutionSetNum, evolutionPos;

    public PokedexWriter_Panels(String region, JFrame frame) {
        enterButton = new JButton("Enter");
        rangeInputWindow();
    }

    private void rangeInputWindow() {
        spareFrame = new JFrame("Input the region's min and max values.");
        spareFrame.setBounds(600, 300, 400, 200);
        spareFrame.setLayout(new GridLayout(2, 2));
        spareFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        label1 = new JLabel("Region's LOWEST pokemon number:");
        label2 = new JLabel("Region's HIGHEST pokemon number:");
        textField1 = new JTextField(3);
        textField2 = new JTextField(3);

        // grid layout needs to be reworked
        // warnings need to be updated and remove previous instances
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    min = Integer.parseInt(textField1.getText());
                    max = Integer.parseInt(textField2.getText());

                    if (min >= max) {
                        warningLabel = new JLabel("Error: min value cannot be greater than max value!");
                        spareFrame.add(warningLabel, BorderLayout.NORTH);
                    } else
                        spareFrame.dispose();

                } catch (Exception e) {
                    warningLabel = new JLabel("Error: Both text fields must be filled!");
                    spareFrame.add(warningLabel, BorderLayout.NORTH);
                }
            }
        });

        spareFrame.add(label1);
        spareFrame.add(textField1);
        spareFrame.add(label2);
        spareFrame.add(textField2);
        spareFrame.add(enterButton, BorderLayout.SOUTH);

        spareFrame.setVisible(true);
    }
}
