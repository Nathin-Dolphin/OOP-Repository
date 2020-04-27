
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.graphics.DrawFace;

import utility.RandomGen;

import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * <b>[!] Known Issues:</b>
 * <p>
 * Faces do not resize when the window resizes.
 * <p>
 * Letters are not always centered on the tile.
 * 
 * @author Nathin Wascher
 * @version 1.2.4
 * @since February 26, 2020
 */

@SuppressWarnings("serial")
class Mosaic_Tile extends JPanel {
    private DrawFace face;
    private RandomGen gen;

    private int width, height, pos, shapeInt, complexity, angle, extraPos;
    private Color color1, color2;
    private String letter;

    Mosaic_Tile() {
        gen = new RandomGen();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent m) {
                face = new DrawFace(true, true, false, pos, pos, width, height);
                repaint();
            }
        });
        randomize();
    }

    public void randomize() {
        color1 = gen.colorGen(20, 220, 20, 220, 20, 220);
        color2 = gen.lighterColor(color1);
        shapeInt = gen.intGen(1, 4);
        complexity = gen.intGen(1, 2);
        letter = String.valueOf(gen.letterGen());

        if (complexity == 1)
            angle = 180;
        else
            angle = -180;

        face = null;
    }

    // WORK IN PROGRESS
    public void repaintTile() {
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

        // determines what shape(s) to output
        if (face != null) {
            face.paint(g);

        } else if (shapeInt == 1) {
            g.fillArc(pos, pos, width, height, 0, angle);
            g.setColor(color2);
            g.fillArc(pos, pos, width, height, 0, -angle);

        } else if (shapeInt == 2) {
            g.fillRect(pos + extraPos, pos, width / 2, height);
            g.setColor(color2);
            g.fillRect(pos + width / 2 - extraPos, pos, width / 2, height);

        } else if (shapeInt == 3) {
            g.fillRect(pos, pos, width, height);
        } else if (shapeInt == 4)
            g.fillOval(pos, pos, width, height);

        if (face == null) {
            g.setColor(gen.complementColor(color1));
            g.setFont(new Font("Verdana", Font.PLAIN, 40));
            // letters don't get centered correctly
            g.drawString(letter, width / 3, 3 * height / 4);
        }

        repaint();
    }
}
