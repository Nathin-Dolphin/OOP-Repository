
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

/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 28, 2020
 */
@SuppressWarnings("serial")
public class PokedexWriter_Writer extends JPanel implements ActionListener {
    private final String pokedexSourceURL = "https://pokemondb.net/pokedex/national";
    public JSONWriter jsonWriter;

    public JTextField maxJTF, minJTF, nameJTF, evoNumJTF;
    public List type1CheckList, type2CheckList, visualCheckList, evolutionCheckList;
    public JButton enterJB;

    private ArrayList<ArrayList<String>> pokedexEntries;
    public ArrayList<String> tempArrayList, regionList, typeList, evolutionStates, urlContents, urlRegionList;
    private String[] tempArray;

    public String fileName, tempString;
    public int min, max, pokeNum, evolution, evoSet, urlLine;

    public PokedexWriter_Writer() {
        pokedexEntries = new ArrayList<ArrayList<String>>();
        urlContents = new ArrayList<String>();
        urlRegionList = new ArrayList<String>();

        evolutionStates = new ArrayList<String>();
        evolutionStates.add("Only");
        evolutionStates.add("First");
        evolutionStates.add("Second");
        evolutionStates.add("Last");
        evolutionStates.add("Second and Last");

        urlLine = -1;
        evolution = 1;
        evoSet = 0;
    }

    public void openURL() {
        boolean stopSearch = false;
        for (int i = 0; i < 8; i++) {
            urlRegionList.add("id=\"gen-" + (i + 1) + "\"");
        }

        int h = -1;
        for (int r = 0; r < regionList.size(); r++) {
            if (regionList.get(r).equalsIgnoreCase(fileName)) {
                h = r;
                r = regionList.size();
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
                urlLine = 1;
                test();
                br.close();
            } catch (Exception e) {
                System.out.println("ERROR: FAILED TO LOAD URL " + pokedexSourceURL);
            }
        }
    }

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

    // TODO: Implement way of changing evolution manually
    private void setEvolution() {
        for (int i = 0; i < evolutionStates.size(); i++) {
            if (evolutionCheckList.getSelectedItem().equals(evolutionStates.get(i))) {
                evolution = i;
                i = evolutionStates.size();
            }
        }
    }

    private String capitalize(String input) {
        try {
            tempString = input.substring(0, 1).toUpperCase();
            tempString = tempString.concat(input.substring(1, input.length()).toLowerCase());
            return tempString;
        } catch (StringIndexOutOfBoundsException e) {
            return "NULL>>>>>>>>>>>>>>>>>>>>>>";
        }
    }

    private void setPokemon() {
        setPokemon(pokeNum++, null);
    }

    // TODO: Make 'visualCheckList' easier to read
    private void setPokemon(int num, String newEvo) {
        tempArrayList = new ArrayList<String>();

        tempArrayList.add("name");
        tempString = capitalize(nameJTF.getText().replaceAll(" ", ""));
        tempArrayList.add(tempString);

        tempArrayList.add("number");
        tempArrayList.add(String.valueOf(num));

        tempArrayList.add("type");
        if (type2CheckList.getSelectedItem().equals("none")) {
            tempArrayList.add(capitalize(type1CheckList.getSelectedItem()));
        } else {
            tempArrayList.add(
                    capitalize(type1CheckList.getSelectedItem()) + "-" + capitalize(type2CheckList.getSelectedItem()));
        }

        setEvolution();
        tempArrayList.add("evolution");
        if (newEvo == null) {
            tempArrayList.add(String.valueOf(evoSet) + "-" + String.valueOf(evolution));
            if (evolution >= 3)
                evoSet++;
        } else
            tempArrayList.add(newEvo);

        tempString = "#" + tempArrayList.get(3) + ": "; // Adds the pokemon number
        tempString = tempString + tempArrayList.get(1) + ";  "; // Adds the name
        tempString = tempString + "Type(s): " + tempArrayList.get(5) + ";  "; // Adds the type(s)
        tempString = tempString + "evo: " + tempArrayList.get(7); // Adds the evolution number
    }

    private void test() {
        tempArray = urlContents.get(urlLine++).split("\"");
        boolean type1Set = false;

        for (String s : tempArray) {
            if (s.contains("/pokedex/")) {
                s = s.replace("/pokedex/", "");
                nameJTF.setText(s);

            } else if (s.contains("/type/")) {
                s = s.replace("/type/", "");
                for (int k = 0; k < typeList.size(); k++) {
                    if (s.equals(typeList.get(k)) & !type1Set) {
                        type1CheckList.select(k);
                        type1Set = true;
                        type2CheckList.select(0);

                    } else if (s.equals(typeList.get(k)))
                        type2CheckList.select(k + 1);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterJB) {
            if (pokeNum == max + 1) {
                warning("MAX NUMBER REACHED");

                // Is the name blank
            } else if (nameJTF.getText().replaceAll(" ", "").equals("")) {
                warning("MISSING NAME");

                // Are both of the selected items in the type Lists the same
            } else if (type1CheckList.getSelectedItem().equals(type2CheckList.getSelectedItem())) {
                warning("TYPE 1 CAN NOT EQUAL TYPE 2");

                // Is user creating a new pokemon
            } else if (visualCheckList.getSelectedItem().equals("Add New Pokemon!")) {
                changeMin();
                setPokemon();
                visualCheckList.add(tempString, visualCheckList.getItemCount() - 1);
                pokedexEntries.add(tempArrayList);

                if (urlLine >= 0 & urlLine < urlContents.size())
                    test();

                if (pokeNum == max + 1) {
                    visualCheckList.remove(visualCheckList.getItemCount() - 1);

                    if (fileName.endsWith(".json"))
                        System.out.println("Creating File: \"" + fileName + "\"");
                    else
                        System.out.println("Creating File: \"" + fileName + ".json\"");

                    jsonWriter.newArray(fileName);
                    for (ArrayList<String> s : pokedexEntries) {
                        jsonWriter.newObject(s);
                    }
                    jsonWriter.endArray();
                    jsonWriter.closeFile();
                    
                    System.out.println("File Successfully Created!");
                }

                // Is the 'almost useless' line seleceted in 'visualCheckList'
            } else if (visualCheckList.getSelectedItem().equals(visualCheckList.getItem(0))) {
                warning("INVALID SELECTED LINE");
                visualCheckList.select(visualCheckList.getItemCount() - 1);

                // If an already existing pokemon is selected
            } else {
                int tempInt = visualCheckList.getSelectedIndex();
                setPokemon(tempInt, "NULL>>>>>>>>>>>>>>>>>>>>>>");

                visualCheckList.remove(tempInt);
                visualCheckList.add(tempString, tempInt);
                visualCheckList.select(visualCheckList.getItemCount() - 1);

                pokedexEntries.remove(tempInt - 1);
                pokedexEntries.add(tempInt - 1, tempArrayList);
            }
        }
    }
}