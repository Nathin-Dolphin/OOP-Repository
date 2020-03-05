
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since February 26, 2020
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;

public class Mosaic_Frame extends JFrame implements ActionListener {
    private JPanel bPanel, tilePanel;
    private JButton buttonObject;
    private ArrayList<Mosaic_Tile> listTiles;
    private Mosaic_Tile tempTile;
    private int a, b;

    Mosaic_Frame() {
        a = 12;
        b = 12;

        setTitle("A Very Colorful Mosaic");
        setBounds(600, 50, 800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        bPanel = new JPanel();
        add(bPanel, BorderLayout.SOUTH);

        buttonObject = new JButton("Randomize Tiles");
        buttonObject.addActionListener(this);
        bPanel.add(buttonObject);

        tilePanel = new JPanel();
        add(tilePanel, BorderLayout.CENTER);

        tilePanel.setLayout(new GridLayout(a, b));

        listTiles = new ArrayList<Mosaic_Tile>();
        for (int i = 0; i < (a * b); i++) {
            tempTile = new Mosaic_Tile();
            listTiles.add(tempTile);
            tilePanel.add(tempTile);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (Mosaic_Tile tile : listTiles) {
            tile.randomize();
        }
        repaint();
    }
}
