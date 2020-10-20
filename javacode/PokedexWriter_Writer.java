
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONWriter;
import utility.json.JSONReader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.List;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.ArrayList;

// TODO: Implement save button
// TODO: Optimize 'evoNum' and 'nextEvoNumJB'
// TODO: Changing the 'min' and'max' is messy and only partly works

/**
 * @author Nathin Wascher
 * @version 1.2.4
 * @since October 20, 2020
 */
public class PokedexWriter_Writer extends JPanel implements ActionListener {
    private static final long serialVersionUID = 2911032048461996161L;
    private final String pokedexSourceURL = "https://pokemondb.net/pokedex/national";
    private final int NAME = 0, NUMBER = 2, TYPE = 4, EVOLUTION = 6, OBJECT_LENGTH = 8;

    private ArrayList<ArrayList<String>> pokedexEntries;
    private ArrayList<String> tempPokedexEntry, urlContents, urlRegionList, jsonContents;
    private String nextEvoNum;
    private int evoNum, urlContentsLine, evolutionPos;

    public JSONWriter jsonWriter;
    public JTextField maxJTF, minJTF, nameJTF, evoNumJTF;
    public List type1CL, type2CL, outputList, evolutionCL; // CL = Check List
    public JButton enterJB, nextEvoNumJB;

    public ArrayList<String> regionList, typeList, evolutionStates;
    public String regionName;
    public int min, max, pokeNum;

    // Initialize a bunch of stuff
    public PokedexWriter_Writer() {
        pokedexEntries = new ArrayList<ArrayList<String>>();
        evolutionStates = new ArrayList<String>();
        urlContents = new ArrayList<String>();
        outputList = new List(40);

        outputList.add("!<<<<<>>>>>!!<<<<<>>>>>!!<<<<<>>>>>!!<<<<<>>>>>!");
        outputList.add("Add New Pokemon!");
        outputList.addActionListener(this);
        outputList.select(1);

        evolutionStates.add("Only");
        evolutionStates.add("First");
        evolutionStates.add("Middle");
        evolutionStates.add("Final");

        urlContentsLine = -1;
        evolutionPos = 1;
        evoNum = 0;
    }

    public void modifyFile() {
        JSONReader pwwJsonReader = new JSONReader();
        jsonContents = new ArrayList<String>();
        String tempString = regionName;
        String[] tempArray;

        getMinMax();

        try {
            pwwJsonReader.readJSON(tempString);
            jsonContents.addAll(pwwJsonReader.get(regionName));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND");
            System.exit(0);
        }

        nameJTF.setText(jsonContents.get(NAME + 1));

        tempArray = jsonContents.get(TYPE + 1).split("-");
        for (int g = 0; g < tempArray.length; g++)
            for (int type = 0; type < typeList.size(); type++)
                if (typeList.get(type).equalsIgnoreCase(tempArray[g]))
                    if (g == 0)
                        type1CL.select(type);
                    else
                        type2CL.select(type + 1);

        // NOTE: Obtaining the evolution set from 'regionList' is temporary
        for (int f = 0; f < regionList.size(); f = f + 2)
            if (regionList.get(f).equals(regionName)) {
                if (--f != -1) {
                    tempString = regionList.get(f).split("-")[1];
                    evoNum = Integer.parseInt(tempString) + 1;
                    evoNumJTF.setText("" + evoNum);
                }
                f = regionList.size();
            }

        // TODO: Set the evolution set entirely from 'jsonContents'
        // tempArray = jsonContents.get(EVOLUTION + 1).split("-");
        // evolutionCL.select(Integer.parseInt(tempArray[1]));
    }

    public void openURL() {
        urlRegionList = new ArrayList<String>();
        String tempString;
        boolean stopSearch = false;

        for (int i = 0; i < 8; i++)
            urlRegionList.add("id=\"gen-" + (i + 1) + "\"");

        int h = getMinMax();

        if (h != -1) {
            try {
                System.out.println("\nREADING URL: " + pokedexSourceURL);
                URL openURL = new URL(pokedexSourceURL);
                BufferedReader br = new BufferedReader(new InputStreamReader(openURL.openStream()));

                while ((tempString = br.readLine()) != null & !stopSearch) {
                    if (tempString.contains(urlRegionList.get(h))) {
                        tempString = br.readLine();

                        while (!tempString.equals("</div>")) {
                            urlContents.add(tempString);
                            tempString = br.readLine();
                        }
                        stopSearch = true;
                    }
                }
                urlContentsLine = 1;
                getPokeInfoFromURL();
                br.close();
            } catch (Exception e) {
                System.out.println("ERROR: FAILED TO LOAD URL " + pokedexSourceURL);
            }
        }
    }

    // TODO: Rework this to be a more general purpose pop-up box
    private void warning(String s) {
        JOptionPane.showConfirmDialog(this, "WARNING: " + s, "WARNING MESSAGE! (WIP)", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    // Finds the min and max for the region
    private int getMinMax() {
        String[] tempArray;
        int h = -1;

        for (int d = 0; d < regionList.size(); d = d + 2) {
            if (regionList.get(d).equalsIgnoreCase(regionName)) {
                tempArray = regionList.get(d - 1).split("-");
                if (d == 0) {
                    min = 1;
                } else {
                    min = Integer.parseInt(tempArray[0]) + 1;
                    evoNum = Integer.parseInt(tempArray[1]) + 1;
                    if (evoNum > 99)
                        evoNumJTF.setText("" + evoNum);
                    else
                        evoNumJTF.setText("0" + evoNum);
                }

                pokeNum = min;
                max = Integer.parseInt(regionList.get(d + 1).split("-")[0]);
                maxJTF.setText("" + max);
                minJTF.setText("" + min);

                h = d / 2;
                d = regionList.size();
            }
        }
        return h;
    }

    // TODO: Rework changeMax()
    private boolean changeMax() {
        if (!maxJTF.getText().equals(String.valueOf(max))) {
            warning("MAX CHANGED");
            maxJTF.setText(String.valueOf(max));
            return true;
        }
        return false;
    }

    // TODO: Rework changeMin()
    private boolean changeMin() {
        if (!minJTF.getText().equals(String.valueOf(min))) {
            warning("MIN CHANGED");
            minJTF.setText(String.valueOf(min));
            changeMax();
            return true;
        }
        return changeMax();
    }

    //
    private String setEvolution() {
        String tempString;

        for (int i = 0; i < evolutionStates.size(); i++) {
            if (evolutionCL.getSelectedItem().equals(evolutionStates.get(i))) {
                evolutionPos = i;
                i = evolutionStates.size();
            }
        }
        tempString = evoNumJTF.getText().replaceAll(" ", "").replaceAll("\t", "");
        if (tempString.length() > 3) {
            evoNumJTF.setText(String.valueOf(evoNum));
            tempString = evoNumJTF.getText();
        }

        try {
            evoNum = Integer.parseInt(tempString);
        } catch (Exception e) {
        }

        if (evoNum < 10)
            tempString = "00" + evoNum;
        else if (evoNum < 100)
            tempString = "0" + evoNum;
        else
            tempString = "" + evoNum;

        if (evolutionPos == 3 || evolutionPos == 0)
            evoNum++;

        if (evoNum < 10)
            nextEvoNum = "00" + evoNum;
        else if (evoNum < 100)
            nextEvoNum = "0" + evoNum;
        else
            nextEvoNum = "" + evoNum;

        return tempString;
    }

    private String capitalize(String input) {
        String tempString;
        try {
            tempString = input.substring(0, 1).toUpperCase();
            tempString = tempString.concat(input.substring(1, input.length()));
            return tempString;
        } catch (StringIndexOutOfBoundsException e) {
            return "!<<<<<NULL>>>>>!";
        }
    }

    // TODO: Make 'outputList' easier to read
    // Takes the user inputs and
    private String setPokemon(int tempPokeNum) {
        tempPokedexEntry = new ArrayList<String>();
        String tempString;

        // Set the name for the pokemon
        tempPokedexEntry.add("name");
        tempString = capitalize(nameJTF.getText().replaceAll(" ", ""));
        tempPokedexEntry.add(tempString);

        // Set the number for the pokemon
        tempPokedexEntry.add("number");
        if (tempPokeNum > 999)
            tempPokedexEntry.add("" + tempPokeNum);
        else if (tempPokeNum > 99)
            tempPokedexEntry.add("0" + tempPokeNum);
        else if (tempPokeNum > 9)
            tempPokedexEntry.add("00" + tempPokeNum);
        else
            tempPokedexEntry.add("000" + tempPokeNum);

        // Set the type(s) for the pokemon
        tempPokedexEntry.add("type");
        tempString = capitalize(type1CL.getSelectedItem());
        if (!type2CL.getSelectedItem().equals("none"))
            tempString = tempString + "-" + capitalize(type2CL.getSelectedItem());
        tempPokedexEntry.add(tempString);

        // Set the evolution string for the pokemon
        tempPokedexEntry.add("evolution");
        tempString = setEvolution();
        tempPokedexEntry.add(tempString + "-" + evolutionPos);
        evoNumJTF.setText(nextEvoNum);

        // TODO: Put a comment here
        if (evolutionPos == 1)
            evolutionCL.select(2);
        else if (evolutionPos == 2)
            evolutionCL.select(3);
        else
            evolutionCL.select(1);

        tempString = "Evo:" + tempPokedexEntry.get(7); // Adds the evolutionPos number
        tempString = tempString + "   #" + tempPokedexEntry.get(3); // Adds the pokemon number
        tempString = tempString + "   " + tempPokedexEntry.get(1); // Adds the name
        tempString = tempString + "     " + tempPokedexEntry.get(5); // Adds the type(s)
        return tempString;
    }

    // Gather the name and type(s) for a pokemon from the URL's contents
    private void getPokeInfoFromURL() {
        String[] tempArray = urlContents.get(urlContentsLine++).split("\"");
        boolean type1Set = false;

        for (String s : tempArray) {
            // Find the name of the pokemon
            if (s.contains("/pokedex/")) {
                s = s.replace("/pokedex/", "");
                nameJTF.setText(s);

                // Find the type(s) of the pokemon
            } else if (s.contains("/type/")) {
                s = s.replace("/type/", "");
                for (int k = 0; k < typeList.size(); k++) {
                    if (s.equals(typeList.get(k)) & !type1Set) {
                        type1CL.select(k);
                        type1Set = true;
                        type2CL.select(0);

                    } else if (s.equals(typeList.get(k)))
                        type2CL.select(k + 1);
                }
            }
        }
    }

    // private void getPokeInfoFromJSON() {}

    // TODO: createFile()
    private void createFile() {
        outputList.remove(outputList.getItemCount() - 1);

        // TODO: Have this do a pop-up box too
        System.out.println("Creating File: \"" + regionName + ".json\"");

        jsonWriter.newArray(regionName);
        for (ArrayList<String> s : pokedexEntries) {
            jsonWriter.newObject(s);
        }
        jsonWriter.endArray();
        jsonWriter.closeFile();

        System.out.println("File Successfully Created!");
    }

    public void actionPerformed(ActionEvent e) {
        String tempString;

        if (e.getSource() == enterJB || e.getSource() == nameJTF) {
            if (pokeNum == max + 1) {
                warning("MAX NUMBER REACHED");

                // If the top line or no line is selected
            } else if (outputList.getSelectedIndex() <= 0) {
                outputList.select(outputList.getItemCount() - 1);
                warning("MissingNo has Appeared!");

                // Is the name blank
            } else if (nameJTF.getText().replaceAll(" ", "").equals("")) {
                warning("MISSING NAME");

                // Are both of the selected items in the type Lists the same
            } else if (type1CL.getSelectedItem().equals(type2CL.getSelectedItem())) {
                warning("TYPE 1 CAN NOT EQUAL TYPE 2");

                // Is user creating a new pokemon
            } else if (outputList.getSelectedItem().equals("Add New Pokemon!")) {
                changeMin();
                tempString = setPokemon(pokeNum++);
                outputList.add(tempString, outputList.getItemCount() - 1);
                outputList.select(outputList.getItemCount() - 1);
                pokedexEntries.add(tempPokedexEntry);

                if (urlContentsLine >= 0 & urlContentsLine < urlContents.size())
                    getPokeInfoFromURL();

                // TODO: Make it more noticable that a file is created
                if (pokeNum == max + 1) {
                    // String tempString = "Create " + regionName + ".json File";
                    // outputList.add(tempString, outputList.getItemCount() - 1);
                    createFile();
                }

                // If an already existing pokemon is selected, then modify the pokemon in
                // 'outputList' and 'pokedexEntries'
            } else {
                int outputIndex = outputList.getSelectedIndex();
                tempString = setPokemon(outputIndex + min - 1);

                outputList.remove(outputIndex);
                outputList.add(tempString, outputIndex);
                outputList.select(outputList.getItemCount() - 1);

                pokedexEntries.remove(outputIndex - 1);
                pokedexEntries.add(outputIndex - 1, tempPokedexEntry);

                if (urlContentsLine >= 0 & urlContentsLine < urlContents.size()) {
                    urlContentsLine--;
                    getPokeInfoFromURL();
                }
            }
            // When user presses nextEvoNum Button
        } else if (e.getSource() == nextEvoNumJB) {
            evoNumJTF.setText(" " + (evoNum + 1));

            // TODO: Remember what I planned to do here
        } else if (e.getSource() == outputList) {
            // int tempInt = outputList.getSelectedIndex();
        }
    }
}