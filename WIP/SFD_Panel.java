
/**
 * @author Nathin
 * @version 1.0
 * @since Februrary 18, 2020
 */

import javax.swing.JPanel;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class SFD_Panel extends JPanel {
    private Random gen = new Random();
    public int xPos, yPos, width, height, rand;
    private SFD_DrawFace[] faceList;

    SFD_Panel() {
        rand = gen.nextInt(10) + 1;
        faceList = new SFD_DrawFace[rand];

        for (int i = 0; i < faceList.length; i++) {
            newDimensions();
            faceList[i] = new SFD_DrawFace(xPos, yPos, width, height);
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

class SFD_DrawFace extends Oval {
    private Oval leftEye, rightEye;
    private Arc mouth;
    private int tempXPos, tempYPos, tempHeight, tempWidth;
    private boolean wideMouth, isSmile;

    SFD_DrawFace(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height);
        facialFeatures(xPos, yPos, width, height);
    }

    private void facialFeatures(int xPos, int yPos, int width, int height) {
        tempWidth = width / 5;
        tempHeight = height / 5;
        tempYPos = height / 6 + yPos;
        Color newColor = randomColor();

        tempXPos = (int) (width / 2 + (double) (width / 10) + xPos);
        rightEye = new Oval(true, newColor, tempXPos, tempYPos, tempWidth, tempHeight);

        tempXPos = (int) (width / 2 - (double) (width / 10) + xPos - tempWidth);
        leftEye = new Oval(true, newColor, tempXPos, tempYPos, tempWidth, tempHeight);

        drawMouth();
    }

    private void drawMouth() {
        isSmile = randomBoolean();
        wideMouth = randomBoolean();

        if (isSmile) {
            tempYPos = yPos - height / 10;

            if (wideMouth) {
                startAngle = 0;
                arcAngle = -180;

            } else {
                startAngle = gen.nextInt(61) - 60;
                arcAngle = -180 - (2 * startAngle);
            }
        } else {
            if (wideMouth) {
                tempYPos = yPos + height / 3;
                startAngle = 0;
                arcAngle = 180;

            } else {
                tempYPos = yPos + height / 2;
                startAngle = gen.nextInt(41) + 20;
                arcAngle = 180 - (2 * startAngle);
            }
        }

        tempWidth = 2 * width / 3;
        tempHeight = height;
        tempXPos = xPos + width / 6;
        mouth = new Arc(wideMouth, randomColor(), tempXPos, tempYPos, tempWidth, tempHeight, startAngle, arcAngle);
    }

    public void paint(Graphics g) {
        super.paint(g);
        leftEye.paint(g);
        rightEye.paint(g);
        mouth.paint(g);
    }
}
