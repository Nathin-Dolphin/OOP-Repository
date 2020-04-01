
/**
 * @author Nathin Wascher
 * @version 0.1 CAUTION: EXPERIMENTAL VERSION
 * @since March 31, 2020
 */

import utility.json.JSONReader;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.List;

public class PokedexSearchEngine_Panel extends JPanel implements ActionListener {
    private JSONReader jsonReader;
    private List typeList, regionList;

    private JTextField searchTF;
    private JLabel warningLabel, label;
    private JButton firstEvoB, secondEvoB, lastEvoB;

    private String[] types, regions, stringList;
    private String input;

    public PokedexSearchEngine_Panel() {
        readPokeInfo();

        searchTF = new JTextField(10);
        warningLabel = new JLabel("");

        firstEvoB = new JButton("first evolution (WIP)");
        secondEvoB = new JButton("second evolution (WIP)");
        lastEvoB = new JButton("last evolution (WIP)");

        searchTF.addActionListener(this);

        add(searchTF);
        add(typeList);
        add(regionList);
    }

    private void readPokeInfo() {
        jsonReader = new JSONReader();
        jsonReader.readJSON("pokeInfo");
        
        types = jsonReader.get("type");
        regions = jsonReader.get("region");

        typeList = new List();
        regionList = new List();

        for (int i = 0; i < types.length; i++) {
            typeList.add(types[i]);
        }
        for (int i = 0; i < regions.length; i++) {
            regionList.add(regions[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTF) {
            input = searchTF.getText();
        }
    }
}