
/**
 * @author Nathin Wascher
 * @version 1.0.1
 * @since Februrary 18, 2020
 */

import utility.DrawFace;

import javax.swing.JPanel;

import java.awt.Graphics;

import java.util.Random;

public class FaceDraw_Panel extends JPanel {
    private Random gen = new Random();
    public int xPos, yPos, width, height, rand;
    private DrawFace[] faceList;

    FaceDraw_Panel() {
        rand = gen.nextInt(10) + 1;
        faceList = new DrawFace[rand];

        for (int i = 0; i < faceList.length; i++) {
            newDimensions();
            faceList[i] = new DrawFace(xPos, yPos, width, height);
        }
    }

    private void newDimensions() {
        width = gen.nextInt(350) + 51;
        height = gen.nextInt(350) + 51;

        do {
            xPos = gen.nextInt(1501);
        } while ((1500 - xPos) < (width - 51));

        do {
            yPos = gen.nextInt(851);
        } while ((800 - yPos) < (height - 51));
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < faceList.length; i++)
            faceList[i].paint(g);
    }
}