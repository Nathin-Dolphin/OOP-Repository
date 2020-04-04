
/**
 * @author Nathin Wascher
 * @version 1.0
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
import java.awt.event.ActionEvent;

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

    private ArrayList<String> inputList, typeList, regionList, pokedex;

    private String input;

    public PokedexSearchEngine_Panel() {
        pokeSearch = new PSE_SearchPokemon();
        setUpPanels();
        readPokeInfo();

        enterB = new JButton("enter");
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

        firstCB.addItemListener(pokeSearch);
        secondCB.addItemListener(pokeSearch);
        lastCB.addItemListener(pokeSearch);

        pokeSearch.setJCheckBoxes(firstCB, secondCB, lastCB);

        checkBoxPanel.add(firstCB);
        checkBoxPanel.add(secondCB);
        checkBoxPanel.add(lastCB);

        searchBarPanel = new JPanel(new GridLayout(2, 1));
        searchBarLabel = new JLabel("Pokemon Search!");
        searchTF = new JTextField(10);
        searchTF.addActionListener(this);
        searchBarPanel.add(searchBarLabel);
        searchBarPanel.add(searchTF);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        jsonReader.readJSON("pokeInfo");
        typeList = regionList = new ArrayList<String>();

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
        pokeSearch.setArrayLists(regionList, pokedex);
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
            pokeSearch.searchByNumber(input);

        } catch (NumberFormatException n) {
            pokeSearch.findPokemon(inputList, input);
        }
    }
}