
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONReader;

import utility.SimpleFrame;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.List;

import java.util.ArrayList;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

/**
 * @author Nathin Wascher
 * @version 1.3
 * @since April 2, 2020
 */

public class PokemonSearch_Searcher implements ItemListener {
    private SimpleFrame frame;
    private JSONReader jsonReader;
    private JCheckBox firstCB, secondCB, lastCB;
    public List outputList;

    private ArrayList<String> pokedex, tempPokedex;
    private String[] tempArray;

    private String tempString;
    private int tempInt;
    private boolean evo1, evo2, evo3;

    public PokemonSearch_Searcher(SimpleFrame frame, JSONReader jsonReader) {
        this.frame = frame;
        this.jsonReader = jsonReader;
        outputList = new List(40);
    }

    public void setJCheckBoxes(JCheckBox firstCB, JCheckBox secondCB, JCheckBox lastCB) {
        this.firstCB = firstCB;
        this.secondCB = secondCB;
        this.lastCB = lastCB;
        evo1 = evo2 = evo3 = true;
    }

    public void findPokemon(ArrayList<String> regionInputList, ArrayList<String> typeInputList, String input) {
        outputList.removeAll();

        searchByRegion(regionInputList);

        // TODO: Create method to search evolutions

        if (!input.equals("")) {
            searchByName(input);
        }

        if (typeInputList.size() != 0) {
            searchByType(typeInputList);
        }

        for (int f = 0; f < pokedex.size(); f++) {
            tempString = pokedex.get(f);

            if (tempString.equals("name")) {
                // WORK IN PROGRES
                outputList.add(pokedex.get(++f));
            }
        }
    }

    // WORK IN PROGRESS
    public void searchByNumber(int input, ArrayList<String> regionList) {
        pokedex = new ArrayList<String>();

        for (int i = 1; i < regionList.size(); i = i + 2) {
            tempString = regionList.get(i);
            tempInt = Integer.parseInt(tempString);

            if (input <= tempInt) {
                try {
                    pokedex = jsonReader.readJSON(regionList.get(--i) + ".json");
                } catch (FileNotFoundException e) {
                    killProgram(regionList.get(--i));
                }
                i = regionList.size();
            }
        }

        for (int g = 0; g < pokedex.size(); g++) {
            tempString = pokedex.get(g);

            // TODO: Add to 'g' so that tempString is always "number"
            // TODO: Implement binary sorting
            if (tempString.equals("number")) {
                tempInt = Integer.parseInt(pokedex.get(++g));
                if (input == tempInt) {
                    System.out.println(pokedex.get(g - 2));
                    outputList.add(pokedex.get(g - 2));
                    g = pokedex.size();
                }
            }
        }
    }

    public void killProgram(String s) {
        JOptionPane.showMessageDialog(frame, "ERROR: UNABLE TO FIND \"" + s + ".json\".\nTERMINATING PROGRAM!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.out.println("...Terminating Program (PokemonSearch)");
        System.exit(0);
    }

    private void searchByRegion(ArrayList<String> regionInputList) {
        pokedex = new ArrayList<String>();

        for (String s : regionInputList) {
            try {
                // TODO: Implement threading
                jsonReader.readJSON(s + ".json");
                pokedex.addAll(jsonReader.get(s));

            } catch (Exception e) {
                System.out.println("ERROR: FILE NOT FOUND OR IS EMPTY: " + s + ".json");
            }
        }
    }

    private void searchByName(String input) {
        tempPokedex = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 0; g < tempPokedex.size(); g++) {
            tempString = tempPokedex.get(g);

            if (tempString.equals("name")) {
                tempString = tempPokedex.get(++g);
                int iL = input.length(); // (i)nput (L)ength
                int tSL = tempString.length(); // (t)emp(S)tring (L)ength

                for (int v = 0; v + iL <= tSL; v++) {
                    if (tempString.substring(v, v + iL).equalsIgnoreCase(input)) {
                        v = tSL;

                        for (int h = 0; h < 8; h++)
                            pokedex.add(tempPokedex.get(g + h - 1));
                    }
                }
            }
        }
    }

    private void searchByType(ArrayList<String> typeInputList) {
        tempPokedex = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 0; g < tempPokedex.size(); g++) {
            tempString = tempPokedex.get(g);

            if (tempString.equals("type")) {
                tempArray = tempPokedex.get(++g).split("-");

                for (int n = 0; n < tempArray.length; n++) {
                    for (int s = 0; s < typeInputList.size(); s++) {
                        if (tempArray[n].equalsIgnoreCase(typeInputList.get(s))) {

                            for (int h = 0; h < 8; h++) {
                                pokedex.add(tempPokedex.get(g + h - 5));
                                n = 3;
                                s = typeInputList.size();
                            }
                        }
                    }
                }
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == firstCB) {
            if (e.getStateChange() == 1)
                evo1 = true;
            else
                evo1 = false;

        } else if (e.getSource() == secondCB) {
            if (e.getStateChange() == 1)
                evo2 = true;
            else
                evo2 = false;

        } else if (e.getSource() == lastCB) {
            if (e.getStateChange() == 1)
                evo3 = true;
            else
                evo3 = false;
        }
    }
}