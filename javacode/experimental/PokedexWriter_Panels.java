
/**
 * @author Nathin Wascher
 * @version 0.4 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import utility.json.JSONReader;
import utility.JSONWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;

import java.util.ArrayList;

public class PokedexWriter_Panels extends JPanel implements ActionListener {
    private PW_ItemListener itemListen;
    private JSONReader jsonReader;
    private JSONWriter jsonWriter;
    private GridBagConstraints gbc;

    private JPanel textPanel, controlPanel;
    private JPanel topPanel, middlePanel, bottomPanel;

    private JTextField regionHighJTF, regionLowJTF, nameJTF;
    private JCheckBox firstJCB, secondJCB, lastJCB;
    private JButton backspaceJB, enterJB;
    private JLabel fileLabel, regionHighJL, regionLowJL, nameJL;
    private List type1CheckList, type2CheckList;

    private ArrayList<String> typeList;
    private String fileName;
    private int min, max, evolution;
    private boolean evo1, evo2, evo3;

    public PokedexWriter_Panels(String fileName) {
        this.fileName = fileName;
        itemListen = new PW_ItemListener();
        jsonWriter = new JSONWriter(fileName);
        jsonReader = new JSONReader("pokeInfo");
        typeList = jsonReader.get("types");

        setUpTextPanel();
        setUpControlPanel();
        add(textPanel);
        add(controlPanel);
    }

    private void setUpTextPanel() {
        textPanel = new JPanel();
    }

    private void setUpControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        setUpTopPanel();
        setUpMiddlePanel();
        setUpBottomPanel();

        setGBC(0, 0);
        controlPanel.add(topPanel, gbc);
        setGBC(0, 1);
        controlPanel.add(middlePanel, gbc);
        setGBC(0, 2);
        controlPanel.add(bottomPanel, gbc);
    }

    private void setUpTopPanel() {
        topPanel = new JPanel(new GridBagLayout());

        if (!fileName.endsWith(".json"))
            fileLabel = new JLabel("File name: " + fileName);
        else
            fileLabel = new JLabel("File name: " + fileName + ".json");

        gbc.gridwidth = 2;
        setGBC(0, 0);
        topPanel.add(fileLabel, gbc);

        regionLowJL = new JLabel("Min number range: ");
        regionLowJTF = new JTextField(3);

        gbc.gridwidth = 1;
        setGBC(0, 1);
        topPanel.add(regionLowJL, gbc);
        setGBC(1, 1);
        topPanel.add(regionLowJTF, gbc);

        regionHighJL = new JLabel("Max number range: ");
        regionHighJTF = new JTextField(3);

        setGBC(0, 2);
        topPanel.add(regionHighJL, gbc);
        setGBC(1, 2);
        topPanel.add(regionHighJTF, gbc);

    }

    private void setUpMiddlePanel() {
        middlePanel = new JPanel(new GridBagLayout());
        evo1 = true;
        evo2 = evo3 = false;

        nameJL = new JLabel("Pokemon Name:");
        nameJTF = new JTextField(10);

        setGBC(0, 0);
        middlePanel.add(nameJL, gbc);
        setGBC(1, 0);
        middlePanel.add(nameJTF, gbc);

        type1CheckList = new List(5);
        type2CheckList = new List(5);

        type2CheckList.add("none");
        for (String s : typeList) {
            type1CheckList.add(s);
            type2CheckList.add(s);
        }
        type1CheckList.select(0);
        type2CheckList.select(0);

        setGBC(0, 1);
        middlePanel.add(type1CheckList, gbc);
        setGBC(1, 1);
        middlePanel.add(type2CheckList, gbc);
    }

    private void setUpBottomPanel() {
        bottomPanel = new JPanel(new GridBagLayout());

        firstJCB = new JCheckBox("first evolution", true);
        secondJCB = new JCheckBox("second evolution", false);
        lastJCB = new JCheckBox("last evolution", false);

        firstJCB.addItemListener(itemListen);
        secondJCB.addItemListener(itemListen);
        lastJCB.addItemListener(itemListen);

        gbc.gridwidth = 2;
        setGBC(0, 0);
        bottomPanel.add(firstJCB, gbc);
        setGBC(0, 1);
        bottomPanel.add(secondJCB, gbc);
        setGBC(0, 2);
        bottomPanel.add(lastJCB, gbc);

        enterJB = new JButton("Next Pokemon");
        backspaceJB = new JButton("Previous Entry");

        enterJB.addActionListener(this);
        backspaceJB.addActionListener(this);

        gbc.gridwidth = 1;
        setGBC(0, 3);
        bottomPanel.add(backspaceJB, gbc);
        setGBC(1, 3);
        bottomPanel.add(enterJB, gbc);
    }

    private void setGBC(int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    private void warning() {
        JOptionPane.showConfirmDialog(this, "WIP!", "WARNING", JOptionPane.OK_OPTION);
    }

    private void setEvolution() {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterJB) {

        }
    }

    class PW_ItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == firstJCB) {
                if (e.getStateChange() == 1)
                    evo1 = true;
                else
                    evo1 = false;

            } else if (e.getSource() == secondJCB) {
                if (e.getStateChange() == 1)
                    evo2 = true;
                else
                    evo2 = false;

            } else if (e.getSource() == lastJCB) {
                if (e.getStateChange() == 1)
                    evo3 = true;
                else
                    evo3 = false;
            }
            setEvolution();
        }
    }
}
