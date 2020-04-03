
/**
 * @author Nathin Wascher
 * @version 0.3 CAUTION: EXPERIMENTAL VERSION
 * @since March 31, 2020
 */

// [?] Unexpected 'crashing' when searching; Pressing 'Enter' in the terminal fixes it [?]

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

import java.awt.GridLayout;
import java.awt.List;

import java.util.ArrayList;

public class PokedexSearchEngine_Panel extends JPanel implements ActionListener {
    private PSE_SearchPokemon pokeSearch;
    private JSONReader jsonReader;
    private List typeCheckList, regionCheckList;

    private JPanel searchBarPanel, checkBoxPanel;
    private JTextField searchTF;
    private JCheckBox firstCB, secondCB, lastCB;
    private JLabel searchBarLabel;
    private JButton enterB;

    private ArrayList<ArrayList<String>> wipRegions;
    private ArrayList<String> inputList, typeList, regionList, pokedex, tempArrayList;
    private String[] tempArray;

    private String input;
    private boolean evo1, evo2, evo3;

    public PokedexSearchEngine_Panel() {
        evo1 = evo2 = evo3 = true;
        setUpPanels();
        readPokeInfo();

        enterB = new JButton("enter");

        searchTF.addActionListener(this);
        enterB.addActionListener(this);

        add(searchBarPanel);
        add(typeCheckList);
        add(regionCheckList);
        add(checkBoxPanel);
        add(enterB);
    }

    private void setUpPanels() {
        checkBoxPanel = new JPanel(new GridLayout(3, 1));

        firstCB = new JCheckBox("first evolution", true);
        secondCB = new JCheckBox("second evolution", true);
        lastCB = new JCheckBox("last evolution", true);

        firstCB.addItemListener(new PSE_ItemListener());
        secondCB.addItemListener(new PSE_ItemListener());
        lastCB.addItemListener(new PSE_ItemListener());

        checkBoxPanel.add(firstCB);
        checkBoxPanel.add(secondCB);
        checkBoxPanel.add(lastCB);

        searchBarPanel = new JPanel(new GridLayout(2, 1));
        searchBarLabel = new JLabel("Pokemon Search!");
        searchTF = new JTextField(10);
        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchTF);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        jsonReader.readJSON("pokeInfo");

        typeList = jsonReader.get("types");
        typeCheckList = new List(5, true);
        for (int i = 0; i < typeList.size(); i++) {
            typeCheckList.add(typeList.get(i));
        }

        regionList = jsonReader.get("regions");
        regionCheckList = new List(5, true);
        for (int i = 0; i < regionList.size(); i = i + 2) {
            regionCheckList.add(regionList.get(i));
        }

        pokedex = jsonReader.get("fullPokedex");
        
        pokeSearch = new PSE_SearchPokemon(regionList, pokedex);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != searchTF & e.getSource() != enterB)
            System.out.println("WHAT CAUSED AN ACTION PERFORMED!!!");

        inputList = new ArrayList<String>();
        pokeSearch.resetBools();
        input = searchTF.getText();
        System.out.println("\naction performed:  " + input);

        for (String type : typeCheckList.getSelectedItems()) {
            inputList.add(type);
            System.out.println("inputList:  " + type);
            pokeSearch.setTypeBool();
        }
        for (String region : regionCheckList.getSelectedItems()) {
            inputList.add(region);
            System.out.println("inputList:  " + region);
            pokeSearch.setRegionBool();
        }
        try {
            Integer.parseInt(input);
            pokeSearch.searchNumber(input);

        } catch (NumberFormatException n) {
            pokeSearch.searchPokemon(inputList, input);
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