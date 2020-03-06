
/**
 * @author Nathin Wascher
 * @version 1.0.1
 * @since Februrary 18, 2020
 */

import java.awt.Graphics;
import java.awt.Color;

class FaceDraw_DrawFace extends Oval {
    private Oval leftEye, rightEye;
    private Arc mouth;
    private int tempXPos, tempYPos, tempHeight, tempWidth;
    private boolean wideMouth, isSmile;

    FaceDraw_DrawFace(int xPos, int yPos, int width, int height) {
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