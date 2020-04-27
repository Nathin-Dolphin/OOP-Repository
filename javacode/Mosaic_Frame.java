
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.SimpleFrame;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;

/**
 * @author Nathin Wascher
 * @version 1.2
 * @since February 26, 2020
 */

public class Mosaic_Frame implements ActionListener {
    private SimpleFrame frame;
    private Mosaic_Tile tempTile;
    private ArrayList<Mosaic_Tile> listTiles;

    private JPanel bPanel, tilePanel;
    private JButton buttonObject;
    private int rows, columns;

    public Mosaic_Frame() {
        frame = new SimpleFrame("Mosaic", "A Very Colorful Mosaic", 860, 600, true, false);
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent c) {
                // WORK IN PROGRESS
            }
        });
        rows = columns = 12;

        bPanel = new JPanel();
        buttonObject = new JButton("Randomize Tiles");
        buttonObject.addActionListener(this);
        bPanel.add(buttonObject);
        frame.add(bPanel, BorderLayout.SOUTH);

        tilePanel = new JPanel();
        frame.add(tilePanel, BorderLayout.CENTER);

        tilePanel.setLayout(new GridLayout(rows, columns));

        listTiles = new ArrayList<Mosaic_Tile>();
        for (int i = 0; i < (rows * columns); i++) {
            tempTile = new Mosaic_Tile();
            listTiles.add(tempTile);
            tilePanel.add(tempTile);
        }

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (Mosaic_Tile tile : listTiles) {
            tile.randomize();
        }
        frame.repaint();
    }
}
