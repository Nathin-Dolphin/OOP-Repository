
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONReader;
import utility.json.JSONWriter;

import utility.SimpleFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;

/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 28, 2020
 */
@SuppressWarnings("serial")
public class PokedexWriter_Panel extends PokedexWriter_Writer {
    private SimpleFrame frame;
    private GridBagConstraints gbc;
    private JSONReader jsonReader;

    // TODO: Reorganize JPanels
    private JPanel textPanel, controlPanel;
    private JPanel topPanel, middlePanel, bottomPanel, evoNumPanel, nameBarPanel;

    private JLabel regionHighJL, regionLowJL, nameJL, evolutionJL, evoNumJL, type1JL, type2JL;

    public PokedexWriter_Panel(String fileName, boolean useURL) {
        if (fileName.endsWith(".json"))
            frame = new SimpleFrame("PokedexWriter", "Creating file: \"" + fileName + "\"", 900, 650, true);
        else
            frame = new SimpleFrame("PokedexWriter", "Creating file: \"" + fileName + ".json\"", 900, 650, true);

        gbc = new GridBagConstraints();
        jsonReader = new JSONReader();
        jsonWriter = new JSONWriter(fileName);
        this.fileName = fileName;

        try {
            jsonReader.readJSON("pokeInfo");
            typeList = jsonReader.get("types");
            regionList = jsonReader.get("regions");
        } catch (Exception e) {
            e.printStackTrace();
        }

        initializeRange();
        setLayout(new GridBagLayout());
        setUpTextPanel();
        setUpControlPanel();

        setGBC(0, 0);
        add(textPanel);
        setGBC(1, 0);
        add(controlPanel);

        frame.add(this);
        frame.setVisible(true);

        visualCheckList.remove(0);
        visualCheckList.select(0);

        if (useURL)
            openURL();
    }

    private void initializeRange() {
        min = 0;
        String j = "";
        do {
            do {
                j = JOptionPane.showInputDialog(this, "Input min: ", "title", JOptionPane.INFORMATION_MESSAGE);
                try {
                    min = Integer.parseInt(j);
                } catch (Exception e) {
                    j = "";
                    System.out.println("ERROR: NOT A NUMBER");
                }
            } while (j.equals(""));
        } while (min <= 0);

        pokeNum = min;
        j = "";
        do {
            do {
                j = JOptionPane.showInputDialog(this, "Input max: ", "title", JOptionPane.INFORMATION_MESSAGE);
                try {
                    max = Integer.parseInt(j);
                } catch (Exception e) {
                    j = "";
                    System.out.println("ERROR: NOT A NUMBER");
                }
            } while (j.equals(""));
        } while (max <= min);
    }

    private void setUpTextPanel() {
        textPanel = new JPanel(new GridBagLayout());

        visualCheckList = new List(40);
        visualCheckList.add("pokemon here; number here; type here; evolution here");
        visualCheckList.add("Add New Pokemon!");

        setGBC(0, 0);
        textPanel.add(visualCheckList, gbc);
    }

    private void setUpControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());

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

        regionLowJL = new JLabel("Min Region range: ");
        minJTF = new JTextField(String.valueOf(min), 3);

        setGBC(0, 0);
        topPanel.add(regionLowJL, gbc);
        setGBC(1, 0);
        topPanel.add(minJTF, gbc);

        regionHighJL = new JLabel("Max Region range: ");
        maxJTF = new JTextField(String.valueOf(max), 3);

        setGBC(0, 1);
        topPanel.add(regionHighJL, gbc);
        setGBC(1, 1);
        topPanel.add(maxJTF, gbc);
    }

    private void setUpMiddlePanel() {
        middlePanel = new JPanel(new GridBagLayout());
        nameBarPanel = new JPanel(new GridBagLayout());

        nameJL = new JLabel("Pokemon Name: ");
        nameJTF = new JTextField(10);

        setGBC(0, 0);
        nameBarPanel.add(nameJL, gbc);
        setGBC(1, 0);
        nameBarPanel.add(nameJTF, gbc);

        gbc.gridwidth = 2;
        setGBC(0, 0);
        middlePanel.add(nameBarPanel, gbc);
        gbc.gridwidth = 1;

        type1JL = new JLabel("Primary Type");
        type2JL = new JLabel("Secondary Type");

        type1CheckList = new List(15);
        type2CheckList = new List(15);
        type2CheckList.add("none");
        for (String s : typeList) {
            type1CheckList.add(s);
            type2CheckList.add(s);
        }
        type1CheckList.select(0);
        type2CheckList.select(0);

        setGBC(0, 1);
        middlePanel.add(type1JL, gbc);
        setGBC(0, 2);
        middlePanel.add(type1CheckList, gbc);
        setGBC(1, 1);
        middlePanel.add(type2JL, gbc);
        setGBC(1, 2);
        middlePanel.add(type2CheckList, gbc);
    }

    private void setUpBottomPanel() {
        bottomPanel = new JPanel(new GridBagLayout());
        evoNumPanel = new JPanel(new GridBagLayout());

        evoNumJL = new JLabel(" EvoNumber: ");
        evoNumJTF = new JTextField(3);

        setGBC(0, 0);
        evoNumPanel.add(evoNumJL, gbc);
        setGBC(0, 1);
        evoNumPanel.add(evoNumJTF, gbc);

        evolutionJL = new JLabel("Evolution");
        evolutionCheckList = new List(5);
        for (String s : evolutionStates)
            evolutionCheckList.add(s);
        evolutionCheckList.select(1);

        setGBC(0, 0);
        bottomPanel.add(evolutionJL, gbc);
        setGBC(0, 1);
        bottomPanel.add(evolutionCheckList, gbc);
        setGBC(1, 1);
        bottomPanel.add(evoNumPanel, gbc);

        enterJB = new JButton("Next Pokemon");
        enterJB.addActionListener(this);

        gbc.gridwidth = 2;
        setGBC(0, 2);
        bottomPanel.add(enterJB, gbc);
        gbc.gridwidth = 1;
    }

    private void setGBC(int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }
}
