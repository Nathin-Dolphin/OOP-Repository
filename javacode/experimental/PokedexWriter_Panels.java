
/**
 * @author Nathin Wascher
 * @version 0.2 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Scrollbar;

import java.util.ArrayList;

public class PokedexWriter_Panels {
    private JFrame frame;
    private JPanel textPanel, controlPanel;

    private JButton previousEntry, enterButton;
    private JLabel label1, label2, warningLabel, textLabel;
    private JTextField nameTF, type1TF, type2TF;

    private GridBagConstraints gbc;

    private Scrollbar scrollbar;

    private String tempStr;
    private int min, max, evolutionSetNum, evolutionPos;

    private ArrayList<JLabel> textList;
    private ArrayList<String> pokemonList;

    public PokedexWriter_Panels(String region, JFrame frame) {
        setUp(frame);
    }

    public void resizeScrollbar() {

    }

    private void dialog() {
        JDialog d = new JDialog(frame, "test");
    }

    private void setUp(JFrame frame) {
        this.frame = frame;
        gbc = new GridBagConstraints();

        setUpTextPanel();
        setUpControlPanel();

        // min = getMinMax("LOWEST");
        // max = getMinMax("HIGHEST");
        System.out.println("min: " + min + "\tmax: " + max);
    }

    private void setUpTextPanel() {
        textPanel = new JPanel();
        textPanel.setBackground(Color.green);

        // scrollbar = new Scrollbar();
        // scrollbar.setBounds(50, 50, 50, 500);
        // textPanel.add(scrollbar);
        // textPanel.setVisible(true);

        frame.add(textPanel, BorderLayout.WEST);
    }

    private void setUpControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(Color.orange);

        enterButton = new JButton("Enter(WIP)");
        previousEntry = new JButton("previousEntry(WIP)");

        warningLabel = new JLabel("");
        warningLabel.setForeground(Color.red);

        label1 = new JLabel("label1(WIP)");
        label2 = new JLabel("label2(WIP)");

        nameTF = new JTextField(5);
        type1TF = new JTextField(5);
        type2TF = new JTextField(5);

        setLayout(0, 0);
        controlPanel.add(label1);
        setLayout(1, 0);
        controlPanel.add(nameTF, gbc);

        setLayout(0, 1);
        controlPanel.add(label2);
        setLayout(1, 1);
        controlPanel.add(type1TF, gbc);

        setLayout(0, 3);
        controlPanel.add(enterButton, gbc);
        setLayout(1, 3);
        controlPanel.add(previousEntry, gbc);

        frame.add(controlPanel, BorderLayout.EAST);
    }

    private void setLayout(int x, int y) {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = x;
        gbc.gridy = y;
    }

    private int getMinMax(String string) {
        int value = 0;
        while (value == 0) {
            try {
                tempStr = (JOptionPane.showInputDialog(frame, "Enter the region's " + string + " pokemon number."));
                try {
                    if (tempStr.equals("")) {
                        System.out.println("ERROR: NO INPUT GIVEN");
                    } else {
                        value = Integer.parseInt(tempStr);
                    }
                } catch (NullPointerException n) {
                    System.out.println("ERROR: NO INPUT GIVEN");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: CANNOT CONVERT STRING TO INTEGER");
            }
        }
        return value;
    }
}
