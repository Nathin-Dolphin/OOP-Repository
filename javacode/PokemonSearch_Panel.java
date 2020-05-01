
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.URLReader;
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
import java.awt.Color;
import java.awt.List;

import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nathin Wascher
 * @version 1.2.3
 * @since March 31, 2020
 */
public class PokemonSearch_Panel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 6168657140878114472L;

    private final String pokeInfoURL = "https://jsontextfiles.azurewebsites.net/pokeInfo.json";

    private SimpleFrame frame;
    private PokemonSearch_Searcher pokeSearch;
    private URLReader urlReader;
    private JSONReader jsonReader;
    private GridBagConstraints gbc;

    private JPanel searchBarPanel, checkBoxPanel;
    private List typeCheckList, regionCheckList;
    private JTextField searchTF;
    private JCheckBox firstCB, secondCB, lastCB;
    private JLabel searchBarLabel, regionLabel, typeLabel;
    private JButton enterB;

    private ArrayList<String> typeInputList, regionInputList, typeList, regionList, tempArr2, tempArray;
    private String input, pokeInfoVersion;
    private int failSafeNum = 0;

    public PokemonSearch_Panel() {
        frame = new SimpleFrame("PokemonSearch", "Guess That Pokemon!", 800, 600, false);
        frame.setLayout(new GridLayout(1, 2));

        gbc = new GridBagConstraints();
        jsonReader = new JSONReader();
        urlReader = new URLReader();
        pokeSearch = new PokemonSearch_Searcher(frame, jsonReader);

        setLayout(new GridBagLayout());
        setBackground(Color.GREEN);
        readPokeInfo();
        setUpPanels();

        frame.add(pokeSearch.outputList);
        frame.add(this);
        frame.setVisible(true);
    }

    private void readPokeInfo() {
        tempArray = new ArrayList<String>();
        tempArr2 = new ArrayList<String>();

        try {
            jsonReader.readJSON("pokeInfo");
            tempArray = jsonReader.get("version");
            pokeInfoVersion = tempArray.get(0);

            typeList = jsonReader.get("types");
            typeCheckList = new List(9, true);
            for (int i = 0; i < typeList.size(); i++)
                typeCheckList.add(typeList.get(i));

            regionList = jsonReader.get("regions");
            regionCheckList = new List(9, true);
            for (int i = 0; i < regionList.size(); i = i + 2)
                regionCheckList.add(regionList.get(i));

            downloadRegionFiles();
        } catch (FileNotFoundException e) {
            downloadRegionFiles();
        }
    }

    // TODO: Implement size, weight, abilities, and weakness options
    // TODO: Implement 'clear' button to search bar
    private void setUpPanels() {
        checkBoxPanel = new JPanel(new GridLayout(3, 1));
        searchBarPanel = new JPanel(new GridLayout(2, 1));

        firstCB = new JCheckBox("First Evolution", true);
        secondCB = new JCheckBox("Second Evolution", true);
        lastCB = new JCheckBox("Last Evolution", true);
        firstCB.setBackground(Color.GREEN);
        secondCB.setBackground(Color.GREEN);
        lastCB.setBackground(Color.GREEN);

        searchBarLabel = new JLabel("   Pokemon Search!");
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
        searchBarPanel.setBackground(Color.GREEN);

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

    private void downloadRegionFiles() {
        tempArr2 = urlReader.readURL(pokeInfoURL);
        urlReader.parseJSON(tempArr2);
        tempArray = urlReader.get("version");

        if (pokeInfoVersion == null) {
            System.out.println("POKEINFO VERSION " + tempArray.get(0) + " != " + null);
            downloadFile("pokeInfo");
            tempArray = urlReader.get("regionURLs");

            for (int d = 0; d < tempArray.size(); d = d + 2) {
                tempArr2 = urlReader.readURL(tempArray.get(d + 1));
                downloadFile(tempArray.get(d));
            }

            if (failSafeNum == 2)
                pokeSearch.killProgram("pokeInfo");
            failSafeNum++;
            readPokeInfo();

        } else if (!pokeInfoVersion.equals(tempArray.get(0))) {
            System.out.println("POKEINFO VERSION " + tempArray.get(0) + " != " + pokeInfoVersion);
            downloadFile("pokeInfo");
            tempArray = urlReader.get("regionURLs");

            for (int d = 0; d < tempArray.size(); d = d + 2) {
                tempArr2 = urlReader.readURL(tempArray.get(d + 1));
                downloadFile(tempArray.get(d));
            }

        } else {
            System.out.println("POKEINFO VERSION " + tempArray.get(0) + " == " + pokeInfoVersion);
            tempArray = urlReader.get("regionURLs");

            for (int i = 0; i < tempArray.size(); i = i + 2) {
                try {
                    Scanner fileScan = new Scanner(new File(tempArray.get(i) + ".json"));

                } catch (FileNotFoundException e) {
                    tempArr2 = urlReader.readURL(tempArray.get(i + 1));
                    downloadFile(tempArray.get(i));
                }
            }
        }
    }

    private void downloadFile(String fileName) {
        System.out.println("Downloading file: " + fileName + ".json");
        try {
            if (tempArr2 == null)
                throw new Exception();

            FileWriter fw = new FileWriter(fileName + ".json");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (int i = 0; i < tempArr2.size(); i++) {
                pw.println(tempArr2.get(i));
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("ERROR: FAILED TO DOWNLOAD " + fileName + ".json");
        }
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