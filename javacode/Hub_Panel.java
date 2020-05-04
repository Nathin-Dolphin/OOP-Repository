
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file  is under the MIT License.
 */

import utility.RandomGen;

import utility.SimpleFrame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.ArrayList;

/**
 * @author Nathin Wascher
 * @version 1.0.1
 * @since May 1, 2020
 */
public class Hub_Panel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 361412748756007463L;

    private RandomGen gen;
    private SimpleFrame frame;
    private GridBagConstraints gbc;

    private JPanel parametersPanel, secondaryPanel;
    private JButton returnButton;
    private JLabel parametersLabel;
    private JTextField parametersJTF;

    private ArrayList<ArrayList<JButton>> buttonList;
    private ArrayList<JButton> buttonArray;

    private String[] stringArray;
    private String parameters;
    private boolean requiresCommand;
    private int program;

    public Hub_Panel() {
        frame = new SimpleFrame("Hub", "A Hub For Programs", 800, 600, true, false);
        gbc = new GridBagConstraints();
        gen = new RandomGen();
        requiresCommand = false;
        parameters = null;

        setUpParametersPanel();
        setUpButtons();
        setUpMainPanel();

        frame.add(this);
        frame.setVisible(true);
    }

    private void setUpMainPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(gen.colorGen());
        gbc.weighty = 1;
        gbc.weightx = 1;

        for (int i = 0; i < buttonList.size(); i++) {
            setGBC(i % 3, i / 3);
            this.add(buttonList.get(i).get(0), gbc);
        }
    }

    private void setUpParametersPanel() {
        parametersPanel = new JPanel(new GridBagLayout());
        parametersLabel = new JLabel("Enter parameters here:");
        parametersJTF = new JTextField(10);
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        setGBC(0, 0);
        parametersPanel.add(parametersLabel, gbc);
        setGBC(0, 1);
        parametersPanel.add(parametersJTF, gbc);
        setGBC(0, 2);
        parametersPanel.add(returnButton, gbc);
    }

    private void setUpSecondaryPanel() {
        secondaryPanel = new JPanel(new GridBagLayout());
        this.setBackground(gen.colorGen());
        secondaryPanel.setBackground(gen.colorGen());
        requiresCommand = true;

        int b = 0;
        for (b = 0; b < buttonList.get(program).size() - 1; b++) {
            setGBC(b % 3, b / 3);
            secondaryPanel.add(buttonList.get(program).get(b + 1), gbc);
        }
        setGBC(1, (b + 3) / 3);
        secondaryPanel.add(parametersPanel, gbc);

        frame.remove(this);
        frame.add(secondaryPanel);
        frame.setVisible(true);
    }

    private void setUpProgram(ActionEvent e) {
        switch (program) {
            case 0:
                new FaceDraw();
                break;

            case 1:
                new Mosaic();
                break;

            case 2:
                if (requiresCommand) {
                    convertToStringArray(e);
                    new PokedexWriter(stringArray);
                } else
                    setUpSecondaryPanel();
                break;

            case 3:
                new PokemonSearch();
                break;

            case 4:
                new ShapeCreator();
                break;

            case 5:
                if (requiresCommand) {
                    convertToStringArray(e);
                    new SwissArmyKnife(stringArray);
                } else
                    setUpSecondaryPanel();
                break;

            default:
                System.out.println("ERROR: PROGRAM NOT SET UP");
                break;
        }
    }

    private void setUpButtons() {
        buttonList = new ArrayList<ArrayList<JButton>>();
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("FaceDraw")); // 0 FaceDraw
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("Mosaic")); // 1 Mosaic
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("PokedexWriter")); // 2 PokedexWriter
        buttonArray.add(new JButton("Help"));
        buttonArray.add(new JButton("WriteNewpokedex"));
        buttonArray.add(new JButton("AddToPokedex"));
        buttonArray.add(new JButton("WriteNewPokedexAssisted"));
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("PokemonSearch")); // 3 PokemonSearch
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("ShapeCreator")); // 4 ShapeCreator
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        buttonArray.add(new JButton("SAK(WIP)")); // 5 SwissArmyknife
        buttonArray.add(new JButton("Help"));
        buttonArray.add(new JButton("HttpRequest [URL]"));
        buttonArray.add(new JButton("HttpRequestIndex [URL]"));
        buttonArray.add(new JButton("JSONValidateIndex"));
        buttonArray.add(new JButton("JSONValidateIndexThreaded"));
        buttonArray.add(new JButton("Sleep"));
        buttonArray.add(new JButton("SleepFast"));
        buttonArray.add(new JButton("SleepFastImplementsRunnable"));
        buttonArray.add(new JButton("AutoRequest"));
        buttonList.add(buttonArray);
        buttonArray = new ArrayList<JButton>();

        for (int i = 0; i < buttonList.size(); i++)
            for (int d = 0; d < buttonList.get(i).size(); d++)
                buttonList.get(i).get(d).addActionListener(this);
    }

    private void convertToStringArray(ActionEvent e) {
        int h = 0;
        for (int v = 1; v < buttonList.get(program).size(); v++) {
            if (e.getSource() == buttonList.get(program).get(v)) {
                h = v;
                v = buttonList.get(program).size();
            }
        }
        if (parameters == null)
            stringArray = new String[] { "-" + buttonList.get(program).get(h).getText() };
        else
            stringArray = new String[] { "-" + buttonList.get(program).get(h).getText(), parameters };
    }

    private void setGBC(int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            parametersJTF.setText(null);
            secondaryPanel.setVisible(false);
            frame.remove(secondaryPanel);
            frame.add(this);
            requiresCommand = false;

        } else if (requiresCommand) {
            parameters = null;
            if (!parametersJTF.getText().replaceAll(" ", "").replaceAll("\t", "").equals(""))
                parameters = parametersJTF.getText();

            for (int i = 1; i < buttonList.get(program).size(); i++) {
                if (e.getSource() == buttonList.get(program).get(i))
                    setUpProgram(e);
            }
        } else {
            for (int i = 0; i < buttonList.size(); i++) {
                if (e.getSource() == buttonList.get(i).get(0)) {
                    program = i;
                    setUpProgram(e);
                }
            }
        }
    }
}