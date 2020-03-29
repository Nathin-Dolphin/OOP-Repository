
/**
 * @author Nathin Wascher
 * @version 1.2.3
 * @since February 26, 2020
 */

import utility.graphics.DrawFace;
import utility.RandomGen;

import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

class Mosaic_Tile extends JPanel implements MouseListener {
    private DrawFace face;
    private RandomGen gen;
    private int width, height, pos, shapeInt, complexity, angle, extraPos;
    private Color color1, color2;
    private String letter;

    Mosaic_Tile() {
        super();
        gen = new RandomGen();
        addMouseListener(this);
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
        if (face != null)
            face.paint(g);

        else if (shapeInt == 1) {
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

        if (face == null) {
            g.setColor(gen.complementColor(color1));
            g.setFont(new Font("Verdana", Font.PLAIN, 40));
            // letters don't get centered correctly
            g.drawString(letter, width / 3, 3 * height / 4);
        }

        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        face = new DrawFace(true, true, false, pos, pos, width, height);
        repaint();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
