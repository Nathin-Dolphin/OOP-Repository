
/**
 * Oval(boolean fillOval, Color newColor, int xPos, int yPos, int width, int height)
 * 
 * fillOval - if true, creates a filled oval; if false, creates an oval outline
 * newColor - the specified color of the oval 
 * xPos, yPos - location of the oval on the screen
 * width, height - vertical and horizontal diameter of the oval
 * 
 * @author Nathin
 * @version 0.3
 * @since Februrary 18, 2020
 */

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Oval {
    private static Random gen = new Random();
    private boolean fillOval;
    private int xPos, yPos, width, height, rand;
    private Color newColor;

    Oval() {
        newSize();
        newPos();
        isOvalFilled();
        randomColor();
    }

    Oval(boolean fillOval, Color newColor, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillOval = fillOval;
        this.newColor = newColor;
    }

    Oval(boolean fillOval, int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fillOval = fillOval;
        randomColor();
    }

    Oval(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        isOvalFilled();
        randomColor();
    }

    private void isOvalFilled() {
        rand = gen.nextInt(2);
        if (rand == 0)
            fillOval = true;
        else
            fillOval = false;
    }

    private void newSize() {
        width = gen.nextInt(450) + 51;
        height = gen.nextInt(450) + 51;
    }

    private void newPos() {
        do {
            xPos = gen.nextInt(1501);
        } while ((1500 - xPos) < (width - 51));

        do {
            yPos = gen.nextInt(851);
        } while ((800 - yPos) < (height - 51));
    }

    private void randomColor() {
        int r, g, b;

        // the do loop is to prevent the color from becoming too white
        do {
            r = gen.nextInt(255) + 1;
            g = gen.nextInt(255) + 1;
            b = gen.nextInt(255) + 1;
        } while (r + g + b > 600);

        newColor = new Color(r, g, b);
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillOval)
            g.fillOval(xPos, yPos, width, height);
        else
            g.drawOval(xPos, yPos, width, height);
    }
}
