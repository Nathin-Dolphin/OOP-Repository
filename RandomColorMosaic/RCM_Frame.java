
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since February 26, 2020
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;

public class RCM_Frame extends JFrame implements ActionListener {
    private JPanel bPanel, tilePanel;
    private JButton buttonObject;
    private ArrayList<RCM_Tile> listTiles;
    private RCM_Tile tempTile;
    private int a, b;

    RCM_Frame() {
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

        a = 12;
        b = 12;
        tilePanel.setLayout(new GridLayout(a, b));

        listTiles = new ArrayList<RCM_Tile>();
        for (int i = 0; i < (a * b); i++) {
            tempTile = new RCM_Tile();
            listTiles.add(tempTile);
            tilePanel.add(tempTile);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (RCM_Tile tile : listTiles) {
            tile.randomize();
        }
        repaint();
    }
}

class RCM_Tile extends JPanel {
    private RandomGen random;
    private int width, height, pos, shapeInt, complexity, angle, extraPos;
    private Color color1, color2;
    private String letter;

    RCM_Tile() {
        super();
        random = new RandomGen();
        randomize();
    }

    public void randomize() {
        color1 = random.colorGen(20, 220, 20, 220, 20, 220);
        color2 = random.monoColor(color1);
        shapeInt = random.intGen(1, 4);
        complexity = random.intGen(1, 2);
        letter = String.valueOf(random.letterGen());

        if (complexity == 1)
            angle = 180;
        else
            angle = -180;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color1);

        height = getHeight() - 5;
        width = getWidth() - 5;
        pos = 3;
        if (complexity == 1)
            extraPos = width / 2;
        else
            extraPos = 0;

        if (shapeInt == 1) {
            g.fillArc(pos, pos, width, height, 0, angle);
            g.setColor(color2);
            g.fillArc(pos, pos, width, height, 0, -angle);

        } else if (shapeInt == 2) {
            g.fillRect(pos + extraPos, pos, width / 2, height);
            g.setColor(color2);
            g.fillRect(pos + width / 2 - extraPos, pos, width / 2, height);

        } else if (shapeInt == 3)
            g.fillRect(pos, pos, width, height);
        else if (shapeInt == 4)
            g.fillOval(pos, pos, width, height);

        g.setColor(random.complementColor(color1));
        g.setFont(new Font("Verdana", Font.PLAIN, 40));
        g.drawString(letter, width / 3, 3 * height / 4);
    }
}

// public void mouseClicked(MouseEvent e) {}
// public void mousePressed(MouseEvent e) {}
// public void mouseEntered(MouseEvent e) {}
// public void mouseExited(MouseEvent e) {}
// public void mouseReleased(MouseEvent e) {}
