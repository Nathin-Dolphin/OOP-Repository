
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.JSONReader;

import utility.SimpleFrame;

import java.awt.List;

import java.util.ArrayList;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

/**
 * @author Nathin Wascher
 * @version 1.4.1
 * @since April 2, 2020
 */
public class PokemonSearch_Searcher {
    private SimpleFrame frame;
    private JSONReader jsonReader;
    public List outputList;

    private ArrayList<String> pokedex, tempPokedex;
    private String[] tempArray;

    private String tempString, tempStr2;
    private int tempInt;

    private final int objectLength = 8;

    public PokemonSearch_Searcher(SimpleFrame frame, JSONReader jsonReader) {
        this.frame = frame;
        this.jsonReader = jsonReader;
        outputList = new List(40);
    }

    public void findPokemon(ArrayList<String> regionInput, ArrayList<String> typeInput,
            ArrayList<String> evolutionInput, String input) {
        long startTime = System.nanoTime();
        outputList.removeAll();

        searchByRegion(regionInput);

        if (evolutionInput.size() != 0)
            searchByEvolution(evolutionInput);

        if (typeInput.size() != 0)
            searchByType(typeInput);

        if (!input.equals(""))
            searchByName(input);

        // Prints the pokemon to the screen
        for (int f = 0; f < pokedex.size(); f = f + objectLength) {
            tempString = pokedex.get(f);

            if (tempString.equals("name")) {
                printToScreen(f + 1, foundInRegion(f));
            }
        }
        System.out
                .println("\nElapsed Time: " + (double) ((System.nanoTime() - startTime) / 1000000) / 1000 + " Seconds");
    }

    // TODO: search for evolution sets with the inputted number
    public void searchByNumber(int input, ArrayList<String> regionList) {
        pokedex = new ArrayList<String>();
        outputList.removeAll();

        for (int i = 1; i < regionList.size(); i = i + 2) {
            tempArray = regionList.get(i).split("-");
            tempInt = Integer.parseInt(tempArray[0]);

            if (input <= tempInt) {
                tempStr2 = regionList.get(i - 1);
                try {
                    // 'pokedex' includes brackets here only
                    jsonReader.readJSON(tempStr2 + ".json");
                    pokedex.addAll(jsonReader.get(tempStr2));
                } catch (FileNotFoundException e) {
                    // TODO: replace killProgram with a non 'system.exit' warning
                    killProgram(tempStr2);
                }
                i = regionList.size();
            }
        }

        // TODO: Implement binary sorting
        for (int g = 2; g < pokedex.size(); g = g + objectLength) {
            tempString = pokedex.get(g);

            if (tempString.equals("number")) {
                tempInt = Integer.parseInt(pokedex.get(g + 1));
                if (input == tempInt) {
                    printToScreen(g - 1, tempStr2);
                    g = pokedex.size();
                }
            }
        }
    }

    // TODO: change this method
    public void killProgram(String s) {
        JOptionPane.showMessageDialog(frame, "ERROR: UNABLE TO FIND \"" + s + ".json\".\nTERMINATING PROGRAM!", "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.out.println("...Terminating Program (PokemonSearch)");
        System.exit(0);
    }

    private void searchByRegion(ArrayList<String> regionInput) {
        pokedex = new ArrayList<String>();

        for (String s : regionInput) {
            try {
                // TODO: Implement threading
                jsonReader.readJSON(s + ".json");
                pokedex.addAll(jsonReader.get(s));

            } catch (Exception e) {
                System.out.println("ERROR: FILE NOT FOUND OR IS EMPTY: " + s + ".json");
            }
        }
    }

    private void searchByEvolution(ArrayList<String> evolutionInput) {
        tempPokedex = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 6; g < tempPokedex.size(); g = g + objectLength) {
            tempString = tempPokedex.get(g);
            if (tempString.equals("evolution")) {
                tempArray = tempPokedex.get(g + 1).split("-");

                for (String e : evolutionInput) {
                    if (e.equals(tempArray[1])) {

                        for (int h = 0; h < 8; h++)
                            pokedex.add(tempPokedex.get(h + g - 6));
                    }
                }
            }
        }
    }

    private void searchByType(ArrayList<String> typeInput) {
        tempPokedex = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 4; g < tempPokedex.size(); g = g + objectLength) {
            tempString = tempPokedex.get(g);

            if (tempString.equals("type")) {
                tempArray = tempPokedex.get(g + 1).split("-");

                for (int n = 0; n < tempArray.length; n++) {
                    for (int s = 0; s < typeInput.size(); s++) {
                        if (tempArray[n].equalsIgnoreCase(typeInput.get(s))) {

                            for (int h = 0; h < 8; h++) {
                                pokedex.add(tempPokedex.get(g + h - 4));
                                n = 3;
                                s = typeInput.size();
                            }
                        }
                    }
                }
            }
        }
    }

    private void searchByName(String input) {
        tempPokedex = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 0; g < tempPokedex.size(); g = g + objectLength) {
            tempString = tempPokedex.get(g);

            if (tempString.equals("name")) {
                tempString = tempPokedex.get(g + 1);

                for (int v = 0; v + input.length() <= tempString.length(); v++) {
                    if (tempString.substring(v, v + input.length()).equalsIgnoreCase(input)) {
                        v = tempString.length();

                        for (int h = 0; h < 8; h++)
                            pokedex.add(tempPokedex.get(g + h));
                    }
                }
            }
        }
    }

    private String foundInRegion(int pokeNum) {
        pokeNum = Integer.parseInt(pokedex.get(pokeNum + 3));

        if (pokeNum <= 151)
            return "Kanto  ";
        else if (pokeNum <= 251)
            return "Johto   ";
        else if (pokeNum <= 386)
            return "Hoenn ";
        else if (pokeNum <= 493)
            return "Sinnoh";
        else if (pokeNum <= 649)
            return "Unova ";
        else if (pokeNum <= 721)
            return "Kalos  ";
        else if (pokeNum <= 809)
            return "Alola    ";
        else if (pokeNum <= 890)
            return "Galar   ";
        else
            return "NONE???";
    }

    private void printToScreen(int pos, String region) {
        int pokeNum;
        tempStr2 = "";

        // Pokemon Region and Evolution
        tempStr2 = region + "   Evo:" + pokedex.get(pos + 6) + "   ";

        // Pokemon Number
        pokeNum = Integer.parseInt(pokedex.get(pos + 2));
        // [!] MAY BE REDUNDANT BY POKEDEXWRITER [!]
        if (pokeNum > 999)
            tempStr2 = tempStr2 + "#" + pokeNum;
        else if (pokeNum > 99)
            tempStr2 = tempStr2 + "#0" + pokeNum;
        else if (pokeNum > 9)
            tempStr2 = tempStr2 + "#00" + pokeNum;
        else
            tempStr2 = tempStr2 + "#000" + pokeNum;

        // Pokemon Name
        tempStr2 = tempStr2 + "   " + pokedex.get(pos) + "  ";
        for (int n = pokedex.get(pos).length(); n < 16; n++)
            tempStr2 = tempStr2 + " ";

        // Pokemon Type(s)
        tempArray = pokedex.get(pos + 4).split("-");
        tempStr2 = tempStr2 + tempArray[0];
        if (tempArray.length == 2)
            tempStr2 = tempStr2 + ", " + tempArray[1];

        outputList.add(tempStr2);
    }
}