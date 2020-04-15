
/**
 * @author Nathin Wascher
 * @version 1.1.1
 * @since April 2, 2020
 */

import utility.json.JSONReader;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.util.ArrayList;

import javax.swing.JCheckBox;

public class PS_SearchPokemon implements ItemListener {
    private JSONReader jsonReader;
    private JCheckBox firstCB, secondCB, lastCB;

    private ArrayList<String> pokedex, tempArrayList;
    private String[] tempArray;

    private String tempString;
    private boolean evo1, evo2, evo3;

    public PS_SearchPokemon() {
    }

    public void setJSONReader(JSONReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public void setJCheckBoxes(JCheckBox firstCB, JCheckBox secondCB, JCheckBox lastCB) {
        this.firstCB = firstCB;
        this.secondCB = secondCB;
        this.lastCB = lastCB;
        evo1 = evo2 = evo3 = true;
    }

    public void findPokemon(ArrayList<String> regionInputList, ArrayList<String> typeInputList, String input) {
        pokedex = new ArrayList<String>();
        
        // [?] NECESSARY [?]
        jsonReader.readJSON("pokeInfo");

        if (regionInputList.size() != 0) {
            for (String s : regionInputList) {
                pokedex.addAll(jsonReader.get(s));
            }
        } else {
            pokedex = jsonReader.get("fullPokedex");
        }

        if (!input.equals("")) {
            searchByName(input);
        }

        if (typeInputList.size() != 0) {
            searchByType(typeInputList);
        }

        for (int f = 0; f < pokedex.size(); f++) {
            tempString = pokedex.get(f);

            if (tempString.equals("name")) {
                // WORK IN PROGRESS
                System.out.println(pokedex.get(++f));
            }
        }
    }

    private void searchByName(String input) {
        tempArrayList = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 0; g < tempArrayList.size(); g++) {
            tempString = tempArrayList.get(g);

            if (tempString.equals("name")) {
                tempString = tempArrayList.get(++g);
                int iL = input.length(); // (i)nput (L)ength
                int tSL = tempString.length(); // (t)emp(S)tring (L)ength

                for (int v = 0; v + iL <= tSL; v++) {
                    if (tempString.substring(v, v + iL).equalsIgnoreCase(input)) {
                        v = tSL;

                        for (int h = 0; h < 8; h++) {
                            pokedex.add(tempArrayList.get(g + h - 1));
                        }
                    }
                }
            }
        }
    }

    private void searchByType(ArrayList<String> typeInputList) {
        tempArrayList = pokedex;
        pokedex = new ArrayList<String>();

        for (int g = 0; g < tempArrayList.size(); g++) {
            tempString = tempArrayList.get(g);

            if (tempString.equals("type")) {
                tempArray = tempArrayList.get(++g).split("-");

                for (int n = 0; n < tempArray.length; n++) {
                    for (int s = 0; s < typeInputList.size(); s++) {
                        if (tempArray[n].equalsIgnoreCase(typeInputList.get(s))) {

                            for (int h = 0; h < 8; h++) {
                                pokedex.add(tempArrayList.get(g + h - 5));
                                n = 3;
                                s = typeInputList.size();
                            }
                        }
                    }
                }
            }
        }
    }

    public void searchByNumber(String input) {
        for (int g = 0; g < pokedex.size(); g++) {
            tempString = pokedex.get(g);
            System.out.println(tempString);

            if (tempString.equals("number") & pokedex.get(++g).equals(input)) {
                System.out.println(pokedex.get(g - 2));
                g = pokedex.size();
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == firstCB) {
            if (e.getStateChange() == 1) {
                System.out.println("item event succesful 1");
                evo1 = true;
            } else {
                evo1 = false;
            }
        }
        if (e.getSource() == secondCB) {
            if (e.getStateChange() == 1) {
                System.out.println("item event succesful 2");
                evo2 = true;
            } else {
                evo2 = false;
            }
        }
        if (e.getSource() == lastCB) {
            if (e.getStateChange() == 1) {
                System.out.println("item event succesful 3");
                evo3 = true;
            } else {
                evo3 = false;
            }
        }
    }
}