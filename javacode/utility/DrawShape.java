
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since March 3, 2020
 */

package utility;

import java.awt.Graphics;
import java.awt.Color;

public class DrawShape extends RandomGen {
    public int xPos, yPos, height, width;
    public boolean fillShape;
    public Color newColor;
    public String shape;

    DrawShape() {
        xPos = yPos = height = width = 100;
        fillShape = booleanGen();
        newColor = colorGen();
        shape = randomShape();
    }

    DrawShape(String shape, boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        this.newColor = newColor;
    }

    DrawShape(String shape, Color newColor, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.newColor = newColor;
        fillShape = booleanGen();
    }

    DrawShape(String shape, boolean fillShape, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        newColor = colorGen();
    }

    DrawShape(String shape, int xPos, int yPos, int width, int height) {
        this.shape = shape;
        sizeAndPos(xPos, yPos, width, height);
        fillShape = booleanGen();
        newColor = colorGen();
    }

    DrawShape(boolean fillShape, Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        this.newColor = newColor;
        shape = randomShape();
    }

    DrawShape(Color newColor, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.newColor = newColor;
        shape = randomShape();
        fillShape = booleanGen();
    }

    DrawShape(boolean fillShape, int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        this.fillShape = fillShape;
        shape = randomShape();
        newColor = colorGen();
    }

    DrawShape(int xPos, int yPos, int width, int height) {
        sizeAndPos(xPos, yPos, width, height);
        shape = randomShape();
        fillShape = booleanGen();
        newColor = colorGen();
    }

    private void sizeAndPos(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public String randomShape() {
        int rand = intGen(0, 2);
        if (rand == 0)
            return shape = "oval";
        else
            return shape = "rect";
    }

    public void paint(Graphics g) {
        g.setColor(newColor);

        if (fillShape) {
            switch (shape) {
                case "oval":
                    g.fillOval(xPos, yPos, width, height);
                    break;
                case "rect":
                    g.fillRect(xPos, yPos, width, height);
                    break;
            }
        } else {
            switch (shape) {
                case "oval":
                    g.drawOval(xPos, yPos, width, height);
                    break;
                case "rect":
                    g.drawRect(xPos, yPos, width, height);
                    break;
            }
        }
    }
}