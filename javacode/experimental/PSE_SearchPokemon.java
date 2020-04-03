
/**
 * @author Nathin Wascher
 * @version 0.1 CAUTION: EXPERIMENTAL VERSION
 * @since April 2, 2020
 */

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.util.ArrayList;

public class PSE_SearchPokemon implements ItemListener {
    private ArrayList<String> pokedex, regionList, tempArrayList, narrowedList;
    private String[] tempArray;

    private String tempString;
    private boolean allRegions, allTypes;

    public PSE_SearchPokemon(ArrayList<String> regionList, ArrayList<String> pokedex) {
        this.regionList = regionList;
        this.pokedex = pokedex;
    }

    public void resetBools() {
        allRegions = allTypes = true;
    }

    public void setTypeBool() {
        allTypes = false;
    }

    public void setRegionBool() {
        allRegions = false;
    }

    public void searchPokemon(ArrayList<String> inputList, String input) {
        tempArrayList = pokedex;
        narrowedList = new ArrayList<String>();

        if (!allRegions) {
            for (int g = 0; g < narrowedList.size(); g++) {
                // WORK IN PROGRESS
            }
        }

        if (!input.equals("")) {
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
                                narrowedList.add(tempArrayList.get(g + h - 1));
                                // {debug} System.out.println("input " + pokedex.get(g + h));
                            }
                        }
                    }
                }
            }
        } else {
            narrowedList = tempArrayList;
        }

        if (!allTypes) {
            tempArrayList = narrowedList;
            narrowedList = new ArrayList<String>();

            for (int g = 0; g < tempArrayList.size(); g++) {
                tempString = tempArrayList.get(g);

                if (tempString.equals("type")) {
                    tempArray = tempArrayList.get(++g).split("-");

                    for (int n = 0; n < tempArray.length; n++) {
                        for (int s = 0; s < inputList.size(); s++) {
                            if (tempArray[n].equalsIgnoreCase(inputList.get(s))) {

                                for (int h = 0; h < 8; h++) {
                                    narrowedList.add(tempArrayList.get(g + h - 5));
                                    n = 3;
                                    s = inputList.size();
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int f = 0; f < narrowedList.size(); f++) {
            tempString = narrowedList.get(f);

            if (tempString.equals("name")) {
                // WORK IN PROGRESS
                System.out.println(narrowedList.get(++f));
            }
        }
    }

    public void searchNumber(String input) {
        for (int g = 0; g < pokedex.size(); g++) {
            tempString = pokedex.get(g);

            if (tempString.equals("number")) {
                tempString = pokedex.get(++g);

                if (tempString.equals(input)) {
                    System.out.println(pokedex.get(g - 2));
                    g = pokedex.size();
                }
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
    }
}