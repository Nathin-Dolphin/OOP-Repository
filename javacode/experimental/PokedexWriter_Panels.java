
/**
 * @author Nathin Wascher
 * @version 0.3 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.awt.Color;

import java.util.ArrayList;

public class PokedexWriter_Panels extends JPanel implements ActionListener {
    private JPanel textPanel, controlPanel;
    private JPanel regionPanel, enterPanel, pokemonInfoPanel;

    private JButton backspaceJB, enterJB;
    private JLabel regionHighJL, regionLowJL;
    private JTextField regionHighJTF, regionLowJTF, nameTF, type1TF, type2TF;

    private ArrayList<JLabel> textList;
    private ArrayList<String> pokemonList;

    private String tempStr;
    private int min, max, evolutionSetNum, evolutionPos;

    public PokedexWriter_Panels(String fileName) {
        setUpTextPanel();
        setUpControlPanel();
        add(textPanel);
        add(controlPanel);
    }

    private void setUpTextPanel() {
        textPanel = new JPanel();
    }

    private void setUpControlPanel() {
        controlPanel = new JPanel(new GridLayout(3, 2));

        regionPanel = new JPanel(new GridLayout(2,2));
        regionHighJL = new JLabel("region's highest number");
        regionLowJL = new JLabel("region's lowest number");
        regionHighJTF = new JTextField(5);
        regionLowJTF = new JTextField(5);
        regionPanel.add(regionLowJL);
        regionPanel.add(regionLowJTF);
        regionPanel.add(regionHighJL);
        regionPanel.add(regionHighJTF);

        pokemonInfoPanel = new JPanel(new GridLayout(2,2));

        enterPanel = new JPanel(new GridLayout(1, 2));
        enterJB = new JButton("Next Pokemon");
        backspaceJB = new JButton("Previous Entry");
        enterJB.addActionListener(this);
        backspaceJB.addActionListener(this);
        enterPanel.add(enterJB);
        enterPanel.add(backspaceJB);

        controlPanel.add(regionPanel);
        controlPanel.add(enterPanel);
    }

    private void warning() {
        int output = JOptionPane.showConfirmDialog(this, "WIP Do you really want to exit?\nAll progress will be lost!",
                "WARNING WIP", JOptionPane.YES_NO_OPTION);
        if (output == JOptionPane.YES_OPTION) {
        } else if (output == JOptionPane.NO_OPTION) {
        }
    }

    public void actionPerformed(ActionEvent e) {
    }
}
