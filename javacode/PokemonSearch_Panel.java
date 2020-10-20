
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.URLReader;
import utility.json.JSONReader;

import utility.SimpleFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
 * @version 1.3.3
 * @since October 17, 2020
 */
public class PokemonSearch_Panel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 6168657140878114472L;
    private final String pokeInfoURL = "https://jsontextfiles.azurewebsites.net/pokeInfo.json";

    private SimpleFrame frame;
    private PokemonSearch_Searcher pokeSearch;
    private URLReader urlReader;
    private JSONReader jsonReader;
    private GridBagConstraints gbc;

    private JPanel searchBarPanel;
    private List typeCL, regionCL, evolutionCL; // CL = Check List
    private JTextField searchTF;
    private JLabel searchBarLabel, regionLabel, typeLabel;
    private JButton enterB;

    private ArrayList<String> typeInput, regionInput, evolutionInput, typeList, regionList;
    private String input;
    private int failSafeNum = 0;

    public PokemonSearch_Panel() {
        frame = new SimpleFrame("PokemonSearch", "Guess That Pokemon!", 800, 600, false);
        frame.setLayout(new GridLayout(1, 2));

        gbc = new GridBagConstraints();
        jsonReader = new JSONReader();
        urlReader = new URLReader();
        pokeSearch = new PokemonSearch_Searcher(jsonReader);

        setLayout(new GridBagLayout());
        setBackground(Color.GREEN);

        readPokeInfo();
        setUpPanels();

        frame.add(pokeSearch.outputList);
        frame.add(this);
        frame.setVisible(true);
    }

    private void readPokeInfo() {
        String pokeInfoJSONVersion = null;

        try {
            jsonReader.readJSON("pokeInfo");
            pokeInfoJSONVersion = jsonReader.get("version").get(0);

            typeList = jsonReader.get("types");
            typeCL = new List(9, true);
            for (int i = 0; i < typeList.size(); i++)
                typeCL.add(typeList.get(i));

            regionList = jsonReader.get("regions");
            regionCL = new List(9, true);
            for (int i = 0; i < regionList.size(); i = i + 2)
                regionCL.add(regionList.get(i));

        } catch (FileNotFoundException e) {
        }

        if (pokeInfoJSONVersion == null) {
            downloadRegionFiles(null);

        } else if (pokeInfoJSONVersion.equals("debug")) {
            frame.setTitle("PokemonSearch: DEBUG MODE");
            JOptionPane.showMessageDialog(this, "PokemonSearch is in debug mode\nand will NOT download any JSON files!",
                    "WARNING: DEBUG MODE ACTIVE", JOptionPane.INFORMATION_MESSAGE);
        } else
            downloadRegionFiles(pokeInfoJSONVersion);
    }

    // TODO: Implement size, weight, abilities, and weakness options
    // TODO: Implement 'clear' button to search bar
    private void setUpPanels() {
        searchBarPanel = new JPanel(new GridLayout(2, 1));

        evolutionCL = new List(4, true);
        evolutionCL.add("The Only Evolution");
        evolutionCL.add("The First Evolution");
        evolutionCL.add("The Middle Evolution");
        evolutionCL.add("The Final Evolution");

        searchBarLabel = new JLabel("   Pokemon Search!");
        regionLabel = new JLabel("Region(s)");
        typeLabel = new JLabel("Type(s)");

        searchTF = new JTextField(12);
        enterB = new JButton("Enter");

        searchTF.addActionListener(this);
        enterB.addActionListener(this);

        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchTF);
        searchBarPanel.setBackground(Color.GREEN);

        setGBC(0, 0);
        add(searchBarPanel, gbc);
        setGBC(0, 1);
        add(typeLabel, gbc);
        setGBC(0, 2);
        add(typeCL, gbc);
        setGBC(0, 3);
        add(regionLabel, gbc);
        setGBC(0, 4);
        add(regionCL, gbc);
        setGBC(0, 5);
        add(evolutionCL, gbc);
        setGBC(0, 6);
        add(enterB, gbc);
    }

    private void setGBC(int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    private void downloadRegionFiles(String pokeInfoJSONVersion) {
        ArrayList<String> tempArray, jsonContents;
        String pokeInfoURLVersion;

        jsonContents = urlReader.readURL(pokeInfoURL);
        urlReader.parseJSON(jsonContents);
        try {
            pokeInfoURLVersion = urlReader.get("version").get(0);
        } catch (NullPointerException n) {
            System.out.println("ERROR: URL NOT FOUND. MAKE SURE YOU HAVE AN INTERNET CONNECTION.");
            pokeInfoURLVersion = null;
        }

        if (pokeInfoURLVersion == null) {
            System.out.println("...Terminating Program (PokemonSearch)");
            System.exit(0);

        } else if (pokeInfoJSONVersion == null || pokeInfoJSONVersion.equals(pokeInfoURLVersion)) {
            downloadFile("pokeInfo", jsonContents);
            tempArray = urlReader.get("regionURLs");

            for (int i = 0; i < tempArray.size(); i = i + 2) {
                jsonContents = urlReader.readURL(tempArray.get(i + 1));
                if (urlReader.isValidURL())
                    downloadFile(tempArray.get(i), jsonContents);
            }

            if (pokeInfoJSONVersion == null) {
                if (failSafeNum >= 1) {
                    System.out.println("ERROR: UNABLE TO FIND \"pokeInfo.json\".");
                    System.out.println("...Terminating Program (PokemonSearch)");
                    System.exit(0);
                } else {
                    System.out.println("VERSION NOT FOUND  FOR \"pokeInfo.json\"");
                    failSafeNum++;
                    readPokeInfo();
                }

            } else {
                System.out.println("POKEINFO VERSION " + pokeInfoURLVersion + " != " + pokeInfoJSONVersion);
            }

        } else {
            System.out.println("POKEINFO VERSION " + pokeInfoURLVersion + " == " + pokeInfoJSONVersion);
            tempArray = urlReader.get("regionURLs");

            for (int i = 0; i < tempArray.size(); i = i + 2) {
                try {
                    Scanner fileScan = new Scanner(new File(tempArray.get(i) + ".json"));
                    if (fileScan.nextLine().equals(""))
                        throw new Exception();
                    fileScan.close();

                } catch (Exception e) {
                    jsonContents = urlReader.readURL(tempArray.get(i + 1));
                    if (urlReader.isValidURL())
                        downloadFile(tempArray.get(i), jsonContents);
                }
            }
        }
    }

    private void downloadFile(String fileName, ArrayList<String> jsonContents) {
        System.out.println("Downloading file: " + fileName + ".json");
        try {
            FileWriter fw = new FileWriter(fileName + ".json");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (int i = 0; i < jsonContents.size() - 1; i++) {
                pw.println(jsonContents.get(i));
            }
            pw.print(jsonContents.get(jsonContents.size() - 1));
            pw.close();

        } catch (Exception e) {
            System.out.println("ERROR: FAILED TO DOWNLOAD " + fileName + ".json.");
        }
    }

    public void actionPerformed(ActionEvent a) {
        regionInput = new ArrayList<String>();
        typeInput = new ArrayList<String>();
        evolutionInput = new ArrayList<String>();
        input = searchTF.getText();
        input = input.replaceAll(" ", "");

        try {
            pokeSearch.searchByNumber(Integer.parseInt(input), regionList);

        } catch (NumberFormatException n) {
            for (String r : regionCL.getSelectedItems())
                regionInput.add(r);
            if (regionInput.size() == 0)
                for (int i = 0; i < regionList.size(); i = i + 2)
                    regionInput.add(regionList.get(i));

            for (String t : typeCL.getSelectedItems())
                typeInput.add(t);
            if (typeList.equals(typeInput))
                typeInput = new ArrayList<String>();

            for (String e : evolutionCL.getSelectedItems()) {
                if (e.contains("Only")) {
                    evolutionInput.add("0");

                } else if (e.contains("First")) {
                    if (!evolutionInput.contains("0"))
                        evolutionInput.add("0");
                    evolutionInput.add("1");

                } else if (e.contains("Middle")) {
                    evolutionInput.add("2");

                } else if (e.contains("Final")) {
                    evolutionInput.add("3");
                }
            }
            pokeSearch.findPokemon(regionInput, typeInput, evolutionInput, input);
        }
    }
}