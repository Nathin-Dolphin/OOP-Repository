
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 2, 2020
 */

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.util.ArrayList;

import javax.swing.JCheckBox;

public class PSE_SearchPokemon implements ItemListener {
    private JCheckBox firstCB, secondCB, lastCB;

    private ArrayList<String> inputList, pokedex, regionList, tempArrayList, narrowedList;
    private String[] tempArray;

    private String tempString;
    private boolean allRegions, allTypes, evo1, evo2, evo3;

    public PSE_SearchPokemon() {
    }

    public void setArrayLists(ArrayList<String> regionList, ArrayList<String> pokedex) {
        this.regionList = regionList;
        this.pokedex = pokedex;

    }

    public void setJCheckBoxes(JCheckBox firstCB, JCheckBox secondCB, JCheckBox lastCB) {
        this.firstCB = firstCB;
        this.secondCB = secondCB;
        this.lastCB = lastCB;
        evo1 = evo2 = evo3 = true;
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

    private boolean findRegions() {
        if (!allRegions) {
            for (int g = 0; g < narrowedList.size(); g++) {
                // WORK IN PROGRESS
            }
            return true;

        } else {
            return false;
        }
    }

    public void findPokemon(ArrayList<String> inputList, String input) {
        this.inputList = inputList;
        if (!findRegions()) {
            narrowedList = pokedex;
        }

        if (!input.equals("")) {
            tempArrayList = narrowedList;
            narrowedList = new ArrayList<String>();

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
                            }
                        }
                    }
                }
            }
        } else {
            narrowedList = pokedex;
            System.out.println("ggg");
        }

        searchByType();

        for (int f = 0; f < narrowedList.size(); f++) {
            tempString = narrowedList.get(f);

            if (tempString.equals("name")) {
                // WORK IN PROGRESS
                System.out.println(narrowedList.get(++f));
            }
        }
    }

    private void searchByType() {
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