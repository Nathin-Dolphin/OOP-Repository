
/**
 * @author Nathin
 * @version 0.1
 * @since February 26, 2020
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;

public class RCM_Panel extends JPanel implements ActionListener {
    private Rectangle[][] rectList;
    private JFrame frame;
    private JPanel button;
    private JButton randomize;
    private DrawRectangles drawRect;

    RCM_Panel(JFrame frame) {
        this.frame = frame;

        button = new JPanel();
        frame.add(button, BorderLayout.SOUTH);
        randomize = new JButton("Randomize Screen");
        randomize.addActionListener(this);
        button.add(randomize);

        drawRect = new DrawRectangles();
        frame.addComponentListener(new repaintObjects());
    }

    private class DrawRectangles extends Rectangle {
        DrawRectangles() {
            rectList = new Rectangle[3][3];
            int y, x;
            int frameWidth = frame.getWidth() - 30;
            int frameHeight = frame.getHeight() - 90;
            int width = frameWidth * 3 / 10;
            int height = frameHeight * 3 / 10;

            for (int a = 0; a < 3; a++) {
                y = 10 + a * (height + height / 6);

                for (int b = 0; b < 3; b++) {
                    x = 10 + b * (width + width / 6);
                    rectList[a][b] = new Rectangle(true, x, y, width, height);
                }
            }
        }
    }

    private class repaintObjects extends Rectangle implements ComponentListener {
        public void componentResized(ComponentEvent c) {
            int y, x;
            int frameWidth = frame.getWidth() - 30;
            int frameHeight = frame.getHeight() - 90;
            int width = frameWidth * 3 / 10;
            int height = frameHeight * 3 / 10;

            for (int a = 0; a < 3; a++) {
                y = 10 + a * (height + height / 6);

                for (int b = 0; b < 3; b++) {
                    x = 10 + b * (width + width / 6);
                    rectList[a][b] = new Rectangle(true, getColor(), x, y, width, height);
                }
            }

        }

        public void componentMoved(ComponentEvent c) {
        }

        public void componentShown(ComponentEvent c) {
        }

        public void componentHidden(ComponentEvent c) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        drawRect = new DrawRectangles();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (rectList != null) {
            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    rectList[a][b].paint(g);
                }
            }
        }
    }
}
