
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONReader;

import utility.SimpleFrame;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;

import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * @author Nathin Wascher
 * @version 1.0.4
 * @since March 31, 2020
 */

@SuppressWarnings("serial")
public class PokemonSearch_Panel extends JPanel implements ActionListener {
    private SimpleFrame frame;
    private PokemonSearch_Searcher pokeSearch;
    private JSONReader jsonReader;
    private GridBagConstraints gbc;
    private JPanel searchBarPanel, checkBoxPanel;

    private List typeCheckList, regionCheckList;
    private JTextField searchTF;
    private JCheckBox firstCB, secondCB, lastCB;
    private JLabel searchBarLabel, regionLabel, typeLabel;
    private JButton enterB;

    private ArrayList<String> typeInputList, regionInputList, typeList, regionList;
    private String input;

    public PokemonSearch_Panel() {
        frame = new SimpleFrame("PokemonSearch", "Guess That Pokemon!", 800, 600, false);
        frame.setLayout(new GridLayout(1, 2));
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        pokeSearch = new PokemonSearch_Searcher(frame);
        readPokeInfo();
        setUpPanels();

        frame.add(this);
        frame.setVisible(true);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        try {
            jsonReader.readJSON("pokeInfo");
        } catch (FileNotFoundException e) {
            pokeSearch.killProgram("pokeInfo");
        }
        pokeSearch.setJSONReader(jsonReader);

        typeList = jsonReader.get("types");
        typeCheckList = new List(5, true);
        for (int i = 0; i < typeList.size(); i++)
            typeCheckList.add(typeList.get(i));

        regionList = jsonReader.get("regions");
        regionCheckList = new List(5, true);
        for (int i = 0; i < regionList.size(); i = i + 2)
            regionCheckList.add(regionList.get(i));
    }

    // TODO: Implement size, weight, abilities, and weakness options
    // TODO: Implement 'clear' button to search bar
    private void setUpPanels() {
        checkBoxPanel = new JPanel(new GridLayout(3, 1));
        searchBarPanel = new JPanel(new GridLayout(2, 1));

        firstCB = new JCheckBox("first evolution", true);
        secondCB = new JCheckBox("second evolution", true);
        lastCB = new JCheckBox("last evolution", true);

        searchBarLabel = new JLabel("Pokemon Search!");
        regionLabel = new JLabel("Region(s)");
        typeLabel = new JLabel("Type(s)");

        searchTF = new JTextField(12);
        enterB = new JButton("enter");

        firstCB.addItemListener(pokeSearch);
        secondCB.addItemListener(pokeSearch);
        lastCB.addItemListener(pokeSearch);
        searchTF.addActionListener(this);
        enterB.addActionListener(this);

        checkBoxPanel.add(firstCB);
        checkBoxPanel.add(secondCB);
        checkBoxPanel.add(lastCB);

        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchTF);

        setGBC(0, 0);
        add(searchBarPanel, gbc);
        setGBC(0, 1);
        add(typeLabel, gbc);
        setGBC(0, 2);
        add(typeCheckList, gbc);
        setGBC(0, 3);
        add(regionLabel, gbc);
        setGBC(0, 4);
        add(regionCheckList, gbc);
        setGBC(0, 5);
        add(checkBoxPanel, gbc);
        setGBC(0, 6);
        add(enterB, gbc);

        pokeSearch.setJCheckBoxes(firstCB, secondCB, lastCB);
    }

    private void setGBC(int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    public void actionPerformed(ActionEvent e) {
        regionInputList = new ArrayList<String>();
        typeInputList = new ArrayList<String>();
        input = searchTF.getText();
        input = input.replaceAll(" ", "");
        System.out.println("\n[#] Input: " + input);

        try {
            pokeSearch.searchByNumber(Integer.parseInt(input), regionList);

        } catch (NumberFormatException n) {
            for (String r : regionCheckList.getSelectedItems())
                regionInputList.add(r);
            if (regionInputList.size() == 0)
                for (int i = 0; i < regionList.size(); i = i + 2)
                    regionInputList.add(regionList.get(i));

            for (String t : typeCheckList.getSelectedItems())
                typeInputList.add(t);
            if (typeList.equals(typeInputList))
                typeInputList = new ArrayList<String>();

            pokeSearch.findPokemon(regionInputList, typeInputList, input);
        }
    }
}