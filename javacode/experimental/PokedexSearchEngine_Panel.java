
/**
 * @author Nathin Wascher
 * @version 0.2 CAUTION: EXPERIMENTAL VERSION
 * @since March 31, 2020
 */

import utility.json.JSONReader;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import java.awt.List;

import java.util.ArrayList;

public class PokedexSearchEngine_Panel extends JPanel implements ActionListener {
    private JSONReader jsonReader;
    private List typeList, regionList;

    private JTextField searchTF;
    private JCheckBox firstCB, secondCB, lastCB;
    private JLabel warningLabel;
    private JButton enterB;

    private ArrayList<String> inputList;
    private String[] types, regions, pokedex;
    private String input, tempString;
    private boolean allRegions, evo1, evo2, evo3, nameSearch;

    public PokedexSearchEngine_Panel() {
        evo1 = evo2 = evo3 = true;
        readPokeInfo();

        searchTF = new JTextField(10);
        warningLabel = new JLabel("");
        enterB = new JButton("enter");

        firstCB = new JCheckBox("first evolution", true);
        secondCB = new JCheckBox("second evolution", true);
        lastCB = new JCheckBox("last evolution", true);

        searchTF.addActionListener(this);
        enterB.addActionListener(this);
        firstCB.addItemListener(new PSE_ItemListener());
        secondCB.addItemListener(new PSE_ItemListener());
        lastCB.addItemListener(new PSE_ItemListener());

        add(searchTF);
        add(typeList);
        add(regionList);
        add(firstCB);
        add(secondCB);
        add(lastCB);
        add(enterB);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        jsonReader.readJSON("pokeInfo");

        types = jsonReader.get("type");
        typeList = new List(5, true);
        for (int i = 0; i < types.length; i++) {
            typeList.add(types[i]);
        }

        regions = jsonReader.get("region");
        regionList = new List(5, true);
        for (int i = 0; i < regions.length; i = i + 2) {
            regionList.add(regions[i]);
        }

        pokedex = jsonReader.get("fullPokedex");
        /*
         * {debug} for (String s : pokedex) { System.out.println(s); }
         */
    }

    // [?] unexpected 'crashing' when searching 'a' [?]
    private void searchPokemon() {
        for (int g = 0; g < pokedex.length; g++) {
            tempString = pokedex[g];

            if (tempString.equals("name") & nameSearch) {
                tempString = pokedex[++g];
                int iL = input.length(); // (i)nput (L)ength
                int tSL = tempString.length(); // (t)emp(S)tring (L)ength

                for (int v = iL - 1; v < tSL; v++) {

                    // StringIndexOutOfBoundsException: begin 8, end 10, length 9; searched 'ch'
                    if (tempString.substring(v, v + iL).equals(input)) {
                        System.out.println(tempString);
                        v = tSL;
                    }

                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTF || e.getSource() == enterB) {
            inputList = new ArrayList<String>();
            allRegions = nameSearch = false;
            input = searchTF.getText();
            System.out.println("\naction performed:  ");

            if (!input.equals("")) {
                nameSearch = true;
                inputList.add(input);
                System.out.println("  inputList:  " + input);
            }
            for (String type : typeList.getSelectedItems()) {
                inputList.add(type);
                System.out.println("  inputList:  " + type);
            }
            for (String region : regionList.getSelectedItems()) {
                if (region.equals("all")) {
                    System.out.println("  inputList:  " + region);

                    for (String s : regions) {
                        if (!s.equals("all")) {
                            inputList.add(s);
                        }
                    }
                    allRegions = true;

                } else if (!allRegions) {
                    inputList.add(region);
                    System.out.println("  inputList:  " + region);
                }
            }
            searchPokemon();
        }
    }

    class PSE_ItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == firstCB) {
                if (evo1) {
                    evo1 = false;
                } else {
                    evo1 = true;
                }
            }
            if (e.getSource() == secondCB) {
                if (evo2) {
                    evo2 = false;
                } else {
                    evo2 = true;
                }
            }
            if (e.getSource() == lastCB) {
                if (evo3) {
                    evo3 = false;
                } else {
                    evo3 = true;
                }
            }
        }
    }
}