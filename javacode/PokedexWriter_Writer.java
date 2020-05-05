
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONWriter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.List;

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
// TODO: Have start at '001' instead of '000'

/**
 * @author Nathin Wascher
 * @version 1.2.2
 * @since March 28, 2020
 */
public class PokedexWriter_Writer extends JPanel implements ActionListener {
    private static final long serialVersionUID = 2911032048461996161L;
    private final String pokedexSourceURL = "https://pokemondb.net/pokedex/national";

    private ArrayList<ArrayList<String>> pokedexEntries;
    private ArrayList<String> tempArrayList, urlContents, urlRegionList;
    private String[] tempArray;
    private String tempString, nextEvoNum;
    private int evoNum, urlContentsLine, evolutionPos;

    public JSONWriter jsonWriter;
    public JTextField maxJTF, minJTF, nameJTF, evoNumJTF;
    public List type1CL, type2CL, outputList, evolutionCL; // CL = Check List
    public JButton enterJB, nextEvoNumJB;

    public ArrayList<String> regionList, typeList, evolutionStates;
    public String regionName;
    public int min, max, pokeNum;

    public PokedexWriter_Writer() {
        pokedexEntries = new ArrayList<ArrayList<String>>();
        evolutionStates = new ArrayList<String>();
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

    public void openURL() {
        urlContents = new ArrayList<String>();
        urlRegionList = new ArrayList<String>();
        boolean stopSearch = false;

        for (int i = 0; i < 8; i++)
            urlRegionList.add("id=\"gen-" + (i + 1) + "\"");

        // Finds the min and max for the region
        // 'regionList' is initialized in PokedexWriter_Panel constructor
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
                getPokeNameFromURL();
                br.close();
            } catch (Exception e) {
                System.out.println("ERROR: FAILED TO LOAD URL " + pokedexSourceURL);
            }
        }
    }

    // TODO: make warning() look a bit better
    private void warning(String s) {
        JOptionPane.showConfirmDialog(this, "WARNING: " + s, "WARNING MESSAGE! (WIP)", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    private boolean changeMax() {
        if (!maxJTF.getText().equals(String.valueOf(max))) {
            warning("MAX CHANGED");
            maxJTF.setText(String.valueOf(max));
            return true;
        }
        return false;
    }

    private boolean changeMin() {
        if (!minJTF.getText().equals(String.valueOf(min))) {
            warning("MIN CHANGED");
            minJTF.setText(String.valueOf(min));
            changeMax();
            return true;
        }
        return changeMax();
    }

    private void setEvolution() {
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

    }

    private String capitalize(String input) {
        try {
            tempString = input.substring(0, 1).toUpperCase();
            tempString = tempString.concat(input.substring(1, input.length()));
            return tempString;
        } catch (StringIndexOutOfBoundsException e) {
            return "!<<<<<NULL>>>>>!";
        }
    }

    private void setPokemon() {
        setPokemon(pokeNum++);
    }

    // TODO: Make 'outputList' easier to read
    private void setPokemon(int num) {
        tempArrayList = new ArrayList<String>();

        tempArrayList.add("name");
        tempString = capitalize(nameJTF.getText().replaceAll(" ", ""));
        tempArrayList.add(tempString);

        tempArrayList.add("number");
        if (num > 999)
            tempArrayList.add("" + num);
        else if (num > 99)
            tempArrayList.add("0" + num);
        else if (num > 9)
            tempArrayList.add("00" + num);
        else
            tempArrayList.add("000" + num);

        tempArrayList.add("type");
        if (type2CL.getSelectedItem().equals("none")) {
            tempArrayList.add(capitalize(type1CL.getSelectedItem()));
        } else {
            tempArrayList.add(capitalize(type1CL.getSelectedItem()) + "-" + capitalize(type2CL.getSelectedItem()));
        }

        tempArrayList.add("evolution");
        setEvolution();
        tempArrayList.add(tempString + "-" + evolutionPos);
        evoNumJTF.setText(nextEvoNum);

        if (evolutionPos == 1)
            evolutionCL.select(2);
        else if (evolutionPos == 2)
            evolutionCL.select(3);
        else
            evolutionCL.select(1);

        tempString = "";
        tempString = tempString + "Evo:" + tempArrayList.get(7); // Adds the evolutionPos number
        tempString = tempString + "   #" + tempArrayList.get(3); // Adds the pokemon number
        tempString = tempString + "   " + tempArrayList.get(1); // Adds the name
        tempString = tempString + "     " + tempArrayList.get(5); // Adds the type(s)
    }

    private void getPokeNameFromURL() {
        tempArray = urlContents.get(urlContentsLine++).split("\"");
        boolean type1Set = false;

        for (String s : tempArray) {
            if (s.contains("/pokedex/")) {
                s = s.replace("/pokedex/", "");
                nameJTF.setText(s);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterJB || e.getSource() == nameJTF) {
            if (pokeNum == max + 1) {
                warning("MAX NUMBER REACHED");

                // If the top line or no line is selected
            } else if (outputList.getSelectedIndex() <= 0) {
                outputList.select(outputList.getItemCount() - 1);
                warning("MissingNo");

                // Is the name blank
            } else if (nameJTF.getText().replaceAll(" ", "").equals("")) {
                warning("MISSING NAME");

                // Are both of the selected items in the type Lists the same
            } else if (type1CL.getSelectedItem().equals(type2CL.getSelectedItem())) {
                warning("TYPE 1 CAN NOT EQUAL TYPE 2");

                // Is user creating a new pokemon
            } else if (outputList.getSelectedItem().equals("Add New Pokemon!")) {
                changeMin();
                setPokemon();
                outputList.add(tempString, outputList.getItemCount() - 1);
                outputList.select(outputList.getItemCount() - 1);
                pokedexEntries.add(tempArrayList);

                if (urlContentsLine >= 0 & urlContentsLine < urlContents.size())
                    getPokeNameFromURL();

                if (pokeNum == max + 1) {
                    outputList.remove(outputList.getItemCount() - 1);

                    if (regionName.endsWith(".json"))
                        System.out.println("Creating File: \"" + regionName + "\"");
                    else
                        System.out.println("Creating File: \"" + regionName + ".json\"");

                    jsonWriter.newArray(regionName);
                    for (ArrayList<String> s : pokedexEntries) {
                        jsonWriter.newObject(s);
                    }
                    jsonWriter.endArray();
                    jsonWriter.closeFile();

                    System.out.println("File Successfully Created!");
                }

                // If an already existing pokemon is selected
            } else {
                int tempInt = outputList.getSelectedIndex();
                setPokemon(tempInt + min - 1);

                outputList.remove(tempInt);
                outputList.add(tempString, tempInt);
                outputList.select(outputList.getItemCount() - 1);

                pokedexEntries.remove(tempInt - 1);
                pokedexEntries.add(tempInt - 1, tempArrayList);

                if (urlContentsLine >= 0 & urlContentsLine < urlContents.size()) {
                    urlContentsLine--;
                    getPokeNameFromURL();
                }
            }
        } else if (e.getSource() == nextEvoNumJB) {
            evoNumJTF.setText(" " + (evoNum + 1));

        } else if (e.getSource() == outputList) {
            // WOKR IN PROGRESS
            int tempInt = outputList.getSelectedIndex();
        }
    }
}