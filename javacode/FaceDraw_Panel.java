
/**
 * @author Nathin Wascher
 * @version 1.0.2
 * @since Februrary 18, 2020
 */

import utility.graphics.DrawFace;
import utility.RandomGen;

import javax.swing.JPanel;

import java.awt.Graphics;

public class FaceDraw_Panel extends JPanel {
    private RandomGen gen;
    public int xPos, yPos, width, height, rand;
    private DrawFace[] faceList;

    FaceDraw_Panel() {
        rand = gen.intGen(3, 10);
        faceList = new DrawFace[rand];

        for (int i = 0; i < faceList.length; i++) {
            newDimensions();
            faceList[i] = new DrawFace(xPos, yPos, width, height);
        }
    }

    private void newDimensions() {
        width = gen.intGen(50, 400);
        height = gen.intGen(50, 400);

        do {
            xPos = gen.intGen(0, 1500);
        } while ((1500 - xPos) < (width - 51));

        do {
            yPos = gen.intGen(0, 850);
        } while ((800 - yPos) < (height - 51));
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < faceList.length; i++)
            faceList[i].paint(g);
    }
}