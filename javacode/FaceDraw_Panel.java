
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.graphics.DrawFace;
import utility.SimpleFrame;
import utility.RandomGen;

import javax.swing.JPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.BorderLayout;
import java.awt.Graphics;

/**
 * Draws random faces.
 * 
 * <p>
 * [!] Known Issues:
 * <p>
 * Faces do not resize to window correctly.
 * <p>
 * Faces can spawn partly offscreen.
 * 
 * @author Nathin Wascher
 * @version 1.1
 * @since Februrary 18, 2020
 */

@SuppressWarnings("serial")
public class FaceDraw_Panel extends JPanel {
    private SimpleFrame frame;
    private RandomGen gen;

    private int[] posList;
    private DrawFace[] faceList;
    private int rand, xPos, yPos, width, height;

    public FaceDraw_Panel(int min, int max) {
        frame = new SimpleFrame("FaceDraw", "So Many Faces!", true, false);
        setBounds(0, 0, frame.getWidth(), frame.getHeight());

        gen = new RandomGen();
        rand = gen.intGen(min, max);
        faceList = new DrawFace[rand];
        posList = new int[rand * 2];

        for (int i = 0; i < faceList.length; i++) {
            setDimensions();
            posList[i * 2] = xPos;
            posList[i * 2 + 1] = yPos;
            faceList[i] = new DrawFace(xPos, yPos, width, height);
        }

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                repaintFaces();
            }
        });

        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // WORK IN PROGRESS
    private void repaintFaces() {
        for (int i = 0; i < faceList.length; i++) {
            faceList[i].repaintFace(xPos, yPos);
        }
        repaint();
    }

    private void setDimensions() {
        width = gen.intGen(50, 300);
        height = gen.intGen(50, 300);
        xPos = gen.intGen(0, getWidth());
        yPos = gen.intGen(0, getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < faceList.length; i++)
            faceList[i].paint(g);
    }
}