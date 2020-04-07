
/**
 * @author Nathin Wascher
 * @version 1.0.2
 * @since March 31, 2020
 */

// [?] Unexpected 'crashing' when searching; Pressing 'Enter' in the terminal fixes it [?]

import utility.json.JSONReader;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.awt.List;

import java.util.ArrayList;

public class PokedexSearch_Panel extends JPanel implements ActionListener {
    private PS_SearchPokemon pokeSearch;
    private JSONReader jsonReader;
    private List typeCheckList, regionCheckList;

    private JPanel searchBarPanel, checkBoxPanel;
    private JTextField searchTF;
    private JCheckBox firstCB, secondCB, lastCB;
    private JLabel searchBarLabel;
    private JButton enterB;

    private ArrayList<String> typeInputList, regionInputList, typeList, regionList;

    private String input;

    public PokedexSearch_Panel() {
        pokeSearch = new PS_SearchPokemon();
        readPokeInfo();
        setUpPanels();
    }

    private void setUpPanels() {
        checkBoxPanel = new JPanel(new GridLayout(3, 1));
        searchBarPanel = new JPanel(new GridLayout(2, 1));

        firstCB = new JCheckBox("first evolution", true);
        secondCB = new JCheckBox("second evolution", true);
        lastCB = new JCheckBox("last evolution", true);
        searchBarLabel = new JLabel("Pokemon Search!");
        searchTF = new JTextField(10);
        enterB = new JButton("enter");

        firstCB.addItemListener(pokeSearch);
        secondCB.addItemListener(pokeSearch);
        lastCB.addItemListener(pokeSearch);
        searchTF.addActionListener(this);
        enterB.addActionListener(this);

        checkBoxPanel.add(firstCB);
        checkBoxPanel.add(secondCB);
        checkBoxPanel.add(lastCB);

        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchTF);

        add(searchBarPanel);
        add(typeCheckList);
        add(regionCheckList);
        add(checkBoxPanel);
        add(enterB);

        pokeSearch.setJCheckBoxes(firstCB, secondCB, lastCB);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        jsonReader.readJSON("pokeInfo");
        pokeSearch.setJSONReader(jsonReader);

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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != searchTF & e.getSource() != enterB)
            System.out.println("WHAT CAUSED AN ACTION PERFORMED!!!");

        regionInputList = new ArrayList<String>();
        typeInputList = new ArrayList<String>();
        input = searchTF.getText();
        System.out.println("\naction performed:  " + input);

        try {
            Integer.parseInt(input);
            pokeSearch.searchByNumber(input);

        } catch (NumberFormatException n) {
            for (String r : regionCheckList.getSelectedItems()) {
                regionInputList.add(r);
                System.out.println("regionInputList:  " + r);
            }
            // This if statement is never called
            if (regionList.equals(regionInputList)) {
                regionInputList = new ArrayList<String>();
                System.out.println("inputList:  all regions");
            }

            for (String t : typeCheckList.getSelectedItems()) {
                typeInputList.add(t);
                System.out.println("typeInputList:  " + t);
            }
            if (typeList.equals(typeInputList)) {
                typeInputList = new ArrayList<String>();
                System.out.println("inputList:  all types");
            }

            pokeSearch.findPokemon(regionInputList, typeInputList, input);
        }
    }
}